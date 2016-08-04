package com.scraper.main;

import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.scraper.main.util.PredicateClassUtility.*;

@RestController
@RequestMapping("/api")
public class ClassScraperAPIController {

    private ClassScraper classScraper;
    private HashSet<Class> allClasses = new HashSet<>();
    static Properties properties = new Properties();
    static String jdbcDriver;
    static String databaseURL;
    static String databaseTable;
    static String userName;
    static String passWord;
    static String useSSL;

    ClassScraperAPIController() throws IOException {
        loadAllClassesFromScraping();
        setDatabaseConfigurations();
    }

    private void setDatabaseConfigurations() throws IOException {
        InputStream input = new FileInputStream("config/config.properties");
        properties.load(input);
        jdbcDriver      = properties.getProperty("jdbcDriver");
        databaseTable   = properties.getProperty("databaseTable");
        databaseURL     = properties.getProperty("databaseURL") +  "/" + databaseTable;
        userName        = properties.getProperty("userName");
        passWord        = properties.getProperty("passWord");
    }

    private void loadAllClassesFromScraping() {
        Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run () {
                classScraper = new ClassScraper(2016, "Summer");

                Optional<String> envVariable = Optional.ofNullable(System.getenv("pageLimit"));
                if(envVariable.isPresent()) {
                    classScraper.setPageLimit(Integer.parseInt(envVariable.get()));
                }
                else {
                    classScraper.setPageLimit(1);
                }
                classScraper.startScraper();
                allClasses.addAll(classScraper.getAllClasses());
                classScraper = new ClassScraper(2016, "Fall");

                if(envVariable.isPresent()) {
                    classScraper.setPageLimit(Integer.parseInt(envVariable.get()));
                }
                else {
                    classScraper.setPageLimit(1);
                }

                classScraper.startScraper();
                allClasses.addAll(classScraper.getAllClasses());
            }
        };

//        timer.schedule (hourlyTask, 60*60*24, 60*60*24*100);

    }

    @RequestMapping(value = "/term={term}", method = RequestMethod.GET)
    @ResponseBody
    public List<ClassInformation> getAllClassesFromTerm(@PathVariable(value = "term") String term) {
        List<ClassInformation> allClassInformation = new LinkedList<>();

        final String SQL_QUERY_ALL_CLASSES = "SELECT * FROM class, building, department, terms WHERE class.TERM_ID = ? AND " +
                "building.building_abbreviation = class.building_abbv AND " +
                "department.department_abbreviation = class.department AND " +
                "terms.term_id = class.term_id";

        try {
            java.lang.Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_ALL_CLASSES);
            preparedStatement.setString(1, term);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClassInformation c = getClassEntryFromResultSet(resultSet);
                allClassInformation.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allClassInformation;
    }

    private ClassInformation getClassEntryFromResultSet(ResultSet rs) throws SQLException {

        HashMap<String, String> termInformation = new LinkedHashMap<>();
        termInformation.put("term_id", rs.getString("term_id"));
        termInformation.put("year",    rs.getString("year"));
        termInformation.put("semester", rs.getString("semester"));

        HashMap<String, Integer> seatInformation = new LinkedHashMap<>();
        seatInformation.put("seatsTaken", rs.getInt("seats_taken"));
        seatInformation.put("seatsAvailable", rs.getInt("seats_available"));
        seatInformation.put("seatsTotal", rs.getInt("seats_total"));

        HashMap<String, String> dateTimeInformation = new LinkedHashMap<>();
        dateTimeInformation.put("startDate", rs.getString("start_date"));
        dateTimeInformation.put("endDate", rs.getString("end_date"));
        dateTimeInformation.put("startTime", rs.getString("start_time"));
        dateTimeInformation.put("endTime", rs.getString("end_time"));

        HashMap<String, Boolean> classDays = new LinkedHashMap<>();
        classDays.put("monday",     rs.getBoolean("monday"));
        classDays.put("tuesday",    rs.getBoolean("tuesday"));
        classDays.put("wednesday",  rs.getBoolean("wednesday"));
        classDays.put("thursday",   rs.getBoolean("thursday"));
        classDays.put("friday",     rs.getBoolean("friday"));
        classDays.put("saturday",   rs.getBoolean("saturday"));
        classDays.put("sunday",     rs.getBoolean("sunday"));

        HashMap<String, String> departmentInformation = new LinkedHashMap<>();
        departmentInformation.put("department",         rs.getString("department"));
        departmentInformation.put("department name",    rs.getString("department_name"));

        HashMap<String, String> locationInformation = new LinkedHashMap<>();
        locationInformation.put("location",            rs.getString("location"));
        locationInformation.put("building_id",            rs.getString("building_id"));
        locationInformation.put("building_abbreviation",  rs.getString("building_abbreviation"));
        locationInformation.put("building_name",          rs.getString("building_name"));

        HashMap<String, String> instructorInformation = new LinkedHashMap<>();
        instructorInformation.put("instructor",            rs.getString("instructor"));
        instructorInformation.put("instructor_email",      rs.getString("instructor_email"));

        ClassInformation c = new ClassInformation(
                termInformation,
                rs.getString("Title"),
                departmentInformation,
                rs.getString("Status"),
                rs.getString("crn"),
                seatInformation,
                dateTimeInformation,
                rs.getString("attributes"),
                classDays,
                instructorInformation,
                locationInformation,
                rs.getString("format"),
                rs.getString("description"),
                rs.getString("duration"),
                rs.getString("session"),
                rs.getString("component"),
                rs.getString("syllabus"),
                rs.getString("last_updated_at"));
        return c;
    }
}
