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

import static com.scraper.main.ClassInformation.getClassEntryFromResultSet;

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

}
