package com.scraper.main;

import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.scraper.main.util.PredicateClassUtility.*;

@RestController
@RequestMapping("/api")
public class ClassScraperAPIController {

    private ClassScraper classScraper;
    private HashSet<ClassInformation> allClassInformations = new HashSet<>();
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
                updateTimeStamp();
            }
        };

//        timer.schedule (hourlyTask, 60*60*24, 60*60*24*100);

    }

    @RequestMapping(value = "/a1pi", method = RequestMethod.GET)
    @ResponseBody
    public List<Class> getAllClasses(@RequestParam(value = "term", required = true)             String              term,
                                                @RequestParam(value = "title", required = false)           Optional<String>    title,
                                                @RequestParam(value = "subject", required = false)         Optional<String>    subject,
                                                @RequestParam(value = "instructorName", required = false)  Optional<String>    instructorName,
                                                @RequestParam(value = "location", required = false)        Optional<String>    location,
                                                @RequestParam(value = "building", required = false)        Optional<String>    building,
                                                @RequestParam(value = "format", required = false)          Optional<String>    format,
                                                @RequestParam(value = "duration", required = false)        Optional<String>    duration,
                                                @RequestParam(value = "session", required = false)         Optional<String>    session,
                                                @RequestParam(value = "component", required = false)       Optional<String>    component,
                                                @RequestParam(value = "hours", required = false)           Optional<Integer>   hours,
                                                @RequestParam(value = "status", required = false)          Optional<Boolean>   status,
                                                @RequestParam(value = "online", required = false)          Optional<Boolean>   online,
                                                @RequestParam(value = "core", required = false)            Optional<Boolean>   core,
                                                @RequestParam(value = "monday", required = false)          Optional<Boolean>   isMonday,
                                                @RequestParam(value = "tuesday", required = false)         Optional<Boolean>   isTuesday,
                                                @RequestParam(value = "wednesday", required = false)       Optional<Boolean>   isWednesday,
                                                @RequestParam(value = "thursday", required = false)        Optional<Boolean>   isThursday,
                                                @RequestParam(value = "friday", required = false)          Optional<Boolean>   isFriday,
                                                @RequestParam(value = "saturday", required = false)        Optional<Boolean>   isSaturday,
                                                @RequestParam(value = "sunday", required = false)          Optional<Boolean>   isSunday,
                                                @RequestParam(value = "syllabus", required = false)        Optional<Boolean>   syllabus) {

        return new ArrayList<>(allClasses
                .parallelStream()
                .filter(getPredicateToFilterByTerm(term))
                .filter(getPredicateToFilterByTitle(title))
                .filter(getPredicateToFilterBySubject(subject))
                .filter(getPredicateToFilterByHours(hours))
                .filter(getPredicateToFilterByStatus(status))
                .filter(getPredicateToFilterByAttribute(core, "CORE"))
                .filter(getPredicateToFilterByAttribute(online, "ONLINE"))
                .filter(getPredicateToFilterByMonday(isMonday))
                .filter(getPredicateToFilterByTuesday(isTuesday))
                .filter(getPredicateToFilterByWednesday(isWednesday))
                .filter(getPredicateToFilterByThursday(isThursday))
                .filter(getPredicateToFilterByFriday(isFriday))
                .filter(getPredicateToFilterBySaturday(isSaturday))
                .filter(getPredicateToFilterBySunday(isSunday))
                .filter(getPredicateToFilterByInstructorName(instructorName))
                .filter(getPredicateToFilterByLocation(location))
                .filter(getPredicateToFilterByBuilding(building))
                .filter(getPredicateToFilterByFormat(format))
                .filter(getPredicateToFilterByDuration(duration))
                .filter(getPredicateToFilterBySession(session))
                .filter(getPredicateToFilterByComponent(component))
                .filter(getPredicateToFilterBySyllabus(syllabus))
//                .map(this::injectCurrentTime)
                .collect(Collectors.toList()));
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
