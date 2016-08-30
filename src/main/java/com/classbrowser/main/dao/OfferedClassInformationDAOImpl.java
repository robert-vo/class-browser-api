package com.classbrowser.main.dao;

import com.classbrowser.main.commons.util.StringSQLQueryUtility;
import com.classbrowser.main.pojo.OfferedClassInformation;
import com.classbrowser.main.pojo.ResponseInformation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object implementation for the OfferedClassInformation POJO.
 *
 * @author Robert Vo
 */
public class OfferedClassInformationDAOImpl extends AbstractInformationDAO implements OfferedClassInformationDAO {

    private static Logger log = Logger.getLogger(OfferedClassInformationDAOImpl.class);

    /**
     * Iterates through the result of the sql query and stores each row into a List of OfferedClassInformation.
     *
     * @param rs - The result of the sql query.
     * @return A List of OfferedClassInformation where each entry in the list represents a row in the ResultSet.
     * @throws SQLException
     */
    @Override
    public List<OfferedClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<OfferedClassInformation> allOfferedClassInformation = new LinkedList<>();
        while(rs.next()) {
            OfferedClassInformation c = OfferedClassInformation.getPojoFromResultSet(rs);
            allOfferedClassInformation.add(c);
        }
        return allOfferedClassInformation;
    }

    /**
     * Completes and executes the SQL query and returns the result as a List of OfferedClassInformation.
     *
     * @param sqlQuery The base SQL query to be used for the prepared statement and to be executed.
     * @param param The parameter for the prepared statement.
     * @return A List of OfferedClassInformation which represents each row in the result of the SQL query.
     * @throws SQLException
     */
    @Override
    public List<OfferedClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, param);
            log.info("Executing SQL Query: " + preparedStatement.toString());
            return retrieveFromResultSet(preparedStatement.executeQuery());
        }
        catch (Exception e) {
            log.error("Error processing SQL Query");
            log.error(e);
        }
        return null;
    }

    /**
     * Retrieves data from the database and returns it as a ResponseInformation holding a List of OfferedClassInformation.
     *
     * @param params - Parameters passed through the URL, used to filter out unwanted data.
     * @return A ResponseInformation holding a List of OfferedClassInformation to be returned to the URL request.
     * @throws Exception
     */
    @Override
    public ResponseInformation<List<OfferedClassInformation>> getFromDatabaseAndResponseInfo(Map params) throws Exception{
        List<OfferedClassInformation> allClasses = selectAllClasses(params);
        int numberOfRows = allClasses.size();
        log.info("Retrieved " + numberOfRows + " items.");
        return new ResponseInformation<>(numberOfRows, params, allClasses);
    }

    @Override
    public List<OfferedClassInformation> selectAllClasses(Map<String, String> allParams) throws Exception {
        final String SQL_QUERY = "SELECT * FROM class, building, department, terms, class_information " +
                " WHERE class.TERM_ID = ? AND" +
                " class.department = class_information.department AND" +
                " building.building_abbreviation = class.building_abbv AND" +
                " department.department_abbreviation = class.department AND" +
                " terms.term_id = class.term_id AND" +
                " class.department_crn = class_information.department_crn ";
        final String SQL_QUERY_ALL_CLASSES = StringSQLQueryUtility.buildSqlQuery(allParams, SQL_QUERY);
        return processStringQuery(SQL_QUERY_ALL_CLASSES, allParams.get("Term"));
    }

}
