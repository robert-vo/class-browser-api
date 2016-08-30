package com.classbrowser.main.dao;

import com.classbrowser.main.commons.util.StringSQLQueryUtility;
import com.classbrowser.main.pojo.ClassInformation;
import com.classbrowser.main.pojo.ResponseInformation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object implementation for the ClassInformation POJO.
 *
 * @author Robert Vo
 */
public class ClassInformationDAOImpl extends AbstractInformationDAO implements ClassInformationDAO {

    private static Logger log = Logger.getLogger(ClassInformationDAOImpl.class);

    /**
     * Iterates through the result of the sql query and stores each row into a List of ClassInformation.
     *
     * @param rs - The result of the sql query.
     * @return A List of ClassInformation where each entry in the list represents a row in the ResultSet.
     * @throws SQLException
     */
    @Override
    public List<ClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<ClassInformation> allClassInformation = new LinkedList<>();
        while(rs.next()) {
            ClassInformation c = ClassInformation.getPojoFromResultSet(rs);
            allClassInformation.add(c);
        }
        return allClassInformation;
    }

    /**
     * Completes and executes the SQL query and returns the result as a List of ClassInformation.
     *
     * @param sqlQuery The base SQL query to be used for the prepared statement and to be executed.
     * @param param The parameter for the prepared statement.
     * @return A List of ClassInformation which represents each row in the result of the SQL query.
     * @throws SQLException
     */
    @Override
    public List<ClassInformation> processStringQuery(String sqlQuery, String... param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
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
     * Retrieves data from the database and returns it as a ResponseInformation holding a List of ClassInformation.
     *
     * @param params - Parameters passed through the URL, used to filter out unwanted data.
     * @return A ResponseInformation holding a List of ClassInformation to be returned to the URL request.
     * @throws Exception
     */
    @Override
    public ResponseInformation<List<ClassInformation>> getFromDatabaseAndResponseInfo(Map params) throws Exception{
        List<ClassInformation> allClasses = selectAllClassInformation(params);
        int numberOfRows = allClasses.size();
        log.info("Retrieved " + numberOfRows + ".");
        return new ResponseInformation<>(numberOfRows, params, allClasses);
    }

    /**
     * Gets all class information from the database with respect to the parameters.
     *
     * @param allParams Parameters used to specify which data will be retrieved.
     * @return A List of ClassInformation where each entry in the List represents a row in the database query.
     * @throws Exception
     */
    @Override
    public List<ClassInformation> selectAllClassInformation(Map allParams) throws Exception {
        final String SQL_QUERY_ALL_CLASSES = StringSQLQueryUtility.buildSqlQueryForInformation(allParams);
        return processStringQuery(SQL_QUERY_ALL_CLASSES);
    }

}
