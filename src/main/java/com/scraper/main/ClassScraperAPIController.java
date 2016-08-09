package com.scraper.main;

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
    public List<ClassInformation> getAllClassesFromTerm(@PathVariable(value = "term") String term,
                                                        @RequestParam Map<String, String> params) {
        List<ClassInformation> allClassInformation = new LinkedList<>();
        final String SQL_QUERY_ALL_CLASSES = buildSqlQuery(term, params);

        handleJavaLangClassDriver();

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

    private String buildSqlQuery(String term, Map<String, String> params) {
        final String SQL_QUERY_FOR_ALL_TERMS = "SELECT * FROM class, building, department, terms, class_information " +
                "WHERE class.TERM_ID = ? AND " +
                "building.building_abbreviation = class.building_abbv AND " +
                "department.department_abbreviation = class.department AND " +
                "terms.term_id = class.term_id AND " +
                "class.department_crn = class_information.department_crn ";
        StringBuilder sqlQuery = new StringBuilder(SQL_QUERY_FOR_ALL_TERMS);

        for (String s : params.keySet()) {
            if (s.equalsIgnoreCase("online")) {
                if (params.get(s).equals("1")) {
                    sqlQuery.append("AND class.format = 'Online' ");
                } else if (params.get(s).equals("0")) {
                    sqlQuery.append("AND class.format <> 'Online' ");
                }
            }
            else if (s.equalsIgnoreCase("hybrid")) {
                if (params.get(s).equals("1")) {
                    sqlQuery.append("AND class.format = 'Hybrid' ");
                } else if (params.get(s).equals("0")) {
                    sqlQuery.append("AND class.format <> 'Hybrid' ");
                }
            }
            else if (s.equalsIgnoreCase("facetoface")) {
                if (params.get(s).equals("1")) {
                    sqlQuery.append("AND class.format = 'Face to Face' ");
                } else if (params.get(s).equals("0")) {
                    sqlQuery.append("AND class.format <> 'Face to Face' ");
                }
            }
            else if (s.equalsIgnoreCase("status")) {
                if (params.get(s).equalsIgnoreCase("open") || params.get(s).equalsIgnoreCase("1")) {
                    sqlQuery.append("AND class.status = 'Open' ");
                }
                else if (params.get(s).equalsIgnoreCase("closed") || params.get(s).equalsIgnoreCase("0")) {
                    sqlQuery.append("AND class.status = 'Closed' ");
                }
            }
            else if (s.equalsIgnoreCase("session")) {
                if (params.get(s).equals("1")) {
                    sqlQuery.append("AND class.session = 'Regular Academic Session' ");
                }
                else if (params.get(s).equals("MIN")) {
                    sqlQuery.append("AND class.session = 'MIN' ");
                }
                else {
                    sqlQuery.append("AND class.session = ").append(params.get(s)).append(" ");
                }
            }
            else if (s.equalsIgnoreCase("department")) {
                sqlQuery.append("AND class.department = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("department_crn")) {
                sqlQuery.append("AND class.department_crn = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("location")) {
                sqlQuery.append("AND class.location = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("component")) {
                sqlQuery.append("AND class.component = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("building")) {
                sqlQuery.append("AND building_abbreviation = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("credit_hours")) {
                sqlQuery.append("AND credit_hours = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("core")) {
                sqlQuery.append("AND (core = ")
                        .append(params.get(s))
                        .append(" or core like '")
                        .append(params.get(s))
                        .append(",%' or core like '%,")
                        .append(params.get(s))
                        .append("') ");
            }
        }
        return sqlQuery.toString();
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
