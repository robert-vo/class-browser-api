package com.scraper.main;

import com.scraper.main.util.StringSQLQueryUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ClassScraperAPIController {

    private HashSet<Class> allClasses = new HashSet<>();
    static Properties properties = new Properties();
    static String jdbcDriver;
    static String databaseURL;
    static String databaseTable;
    static String userName;
    static String passWord;
    static String useSSL;

    ClassScraperAPIController() throws IOException {
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

    @RequestMapping(value = "/core={core}", method = RequestMethod.GET)
    @ResponseBody
    public List<CoreClassInformation> getAllCoreCourses(@PathVariable(value = "core") String core) {
        List<CoreClassInformation> allClassInformation = new LinkedList<>();
        final String SQL_QUERY_CORE_CLASSES = "SELECT * FROM class.class_information, class.core " +
                "where (core = ? or core like '?,%' or core like '%, ?') " +
                "and ? = core.core_id";
        handleJavaLangClassDriver();
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_CORE_CLASSES);
            preparedStatement.setString(1, core);
            preparedStatement.setString(2, core);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CoreClassInformation c;
                c = new CoreClassInformation(resultSet.getString("Department"),
                        resultSet.getString("Department_crn"),
                        resultSet.getString("class_description"),
                        resultSet.getString("class_title"),
                        resultSet.getInt("credit_hours"),
                        resultSet.getString("core"),
                        resultSet.getInt("core_id"),
                        resultSet.getString("core_title"),
                        resultSet.getInt("hours_required"));
                allClassInformation.add(c);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return allClassInformation;
    }

    @RequestMapping(value = "/term={term}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllClassesFromTerm(@PathVariable(value = "term") String term,
                                                @RequestParam Map<String, String> params) throws Exception {

        List<ClassInformation> allClassInformation = new LinkedList<>();
        final String SQL_QUERY_ALL_CLASSES = StringSQLQueryUtility.buildSqlQuery(params);

        if(term.length() != 4 || term.matches("[a-z]|[A-Z]")) {
            try {
                throw new InvalidArgumentException("Term");
            }
            catch (InvalidArgumentException e) {
                ErrorMessage errorMessage = new ErrorMessage("Invalid Term", "Terms must be an integer starting from 1970 and incrementing by 10.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
        }
        handleJavaLangClassDriver();
        try (Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY_ALL_CLASSES);
            preparedStatement.setString(1, term);
            System.out.println("Running query - " + preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClassInformation c = getClassEntryFromResultSet(resultSet);
                allClassInformation.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(allClassInformation, HttpStatus.OK);
    }

    private void handleJavaLangClassDriver() {
        try {
            java.lang.Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ClassInformation getClassEntryFromResultSet(ResultSet rs) throws SQLException {

        HashMap<String, String> termInformation = new LinkedHashMap<>();
        termInformation.put("termID",  rs.getString("term_id"));
        termInformation.put("year",     rs.getString("year"));
        termInformation.put("semester", rs.getString("semester"));

        HashMap<String, Integer> seatInformation = new LinkedHashMap<>();
        seatInformation.put("seatsTaken",       rs.getInt("seats_taken"));
        seatInformation.put("seatsAvailable",   rs.getInt("seats_available"));
        seatInformation.put("seatsTotal",       rs.getInt("seats_total"));

        HashMap<String, String> dateTimeInformation = new LinkedHashMap<>();
        dateTimeInformation.put("startDate",    rs.getString("start_date"));
        dateTimeInformation.put("endDate",      rs.getString("end_date"));
        dateTimeInformation.put("startTime",    rs.getString("start_time"));
        dateTimeInformation.put("endTime",      rs.getString("end_time"));

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
        departmentInformation.put("departmentName",     rs.getString("department_name"));

        HashMap<String, String> locationInformation = new LinkedHashMap<>();
        locationInformation.put("location",               rs.getString("location"));
        locationInformation.put("buildingID",             rs.getString("building_id"));
        locationInformation.put("buildingAbbreviation",   rs.getString("building_abbreviation"));
        locationInformation.put("buildingName",           rs.getString("building_name"));

        HashMap<String, String> instructorInformation = new LinkedHashMap<>();
        instructorInformation.put("instructor",         rs.getString("instructor"));
        instructorInformation.put("instructorEmail",    rs.getString("instructor_email"));

        Optional<String> possibleCoreClasses = Optional.ofNullable(rs.getString("CORE"));
        String[] core = null;
        if(possibleCoreClasses.isPresent()) {
            core = possibleCoreClasses.get().split(",");
        }

        ClassInformation c = new ClassInformation(termInformation,
                rs.getString("CLASS_TITLE"), departmentInformation,
                rs.getString("Status"), rs.getString("crn"),
                seatInformation, dateTimeInformation, rs.getString("attributes"),
                classDays, instructorInformation, locationInformation,
                rs.getString("format"), rs.getString("CLASS_DESCRIPTION"),
                rs.getString("duration"), rs.getString("session"),
                rs.getString("component"), rs.getString("syllabus"),
                core,
                rs.getString("last_updated_at"));
        return c;
    }

}
