package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.DepartmentInformation;
import com.classbrowser.main.pojo.ResponseInformation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object implementation for the DepartmentInformation POJO.
 *
 * @author Robert Vo
 */
public class DepartmentInformationDAOImpl extends AbstractInformationDAO implements DepartmentInformationDAO {

    private static Logger log = Logger.getLogger(DepartmentInformationDAOImpl.class);

    /**
     * Retrieves data from the database and returns it as a ResponseInformation holding a List of DepartmentInformation.
     *
     * @param params - Parameters passed through the URL, used to filter out unwanted data.
     * @return A ResponseInformation holding a List of DepartmentInformation to be returned to the URL request.
     * @throws Exception
     */
    @Override
    public ResponseInformation<List<DepartmentInformation>> getFromDatabaseAndResponseInfo(Map params) throws Exception{
        List<DepartmentInformation> allCoreClasses = selectAllDepartments();
        int numberOfRows = allCoreClasses.size();
        log.info("Retrieved " + numberOfRows + " items.");
        return new ResponseInformation<>(numberOfRows, params, allCoreClasses);
    }

    /**
     * Gets all departments from the database.
     *
     * @return A List of DepartmentInformation where each entry in the List represents a department in the database.
     * @throws SQLException
     */
    @Override
    public List<DepartmentInformation> selectAllDepartments() throws SQLException {
        final String SQL_QUERY_CORE_CLASSES = "select * from department;";
        return processStringQuery(SQL_QUERY_CORE_CLASSES);
    }

    /**
     * Completes and executes the SQL query and returns the result as a List of DepartmentInformation.
     *
     * @param sqlQuery The base SQL query to be used for the prepared statement and to be executed.
     * @param param The parameter for the prepared statement.
     * @return A List of DepartmentInformation which represents each row in the result of the SQL query.
     * @throws SQLException
     */
    @Override
    public List<DepartmentInformation> processStringQuery(String sqlQuery, String... param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            log.info("Executing SQL Query: " + preparedStatement.toString());
            return retrieveFromResultSet(preparedStatement.executeQuery());
        }
        catch (Exception e) {
            log.error("Error processing SQL Query.");
            log.error(e);
        }
        return null;
    }

    /**
     * Iterates through the result of the sql query and stores each row into a List of DepartmentInformation.
     *
     * @param rs - The result of the sql query.
     * @return A List of DepartmentInformation where each entry in the list represents a row in the ResultSet.
     * @throws SQLException
     */
    @Override
    public List<DepartmentInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<DepartmentInformation> allDepartmentInformation = new LinkedList<>();
        while(rs.next()) {
            DepartmentInformation d = DepartmentInformation.getPojoFromResultSet(rs);
            allDepartmentInformation.add(d);
        }
        return allDepartmentInformation;
    }

}
