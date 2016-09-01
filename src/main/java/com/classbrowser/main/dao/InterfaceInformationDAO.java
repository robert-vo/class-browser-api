package com.classbrowser.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object interface with the expectation that all concrete POJO DAOs will implement these methods.
 *
 * @author Robert Vo
 */
public interface InterfaceInformationDAO<T> {
    /**
     * Constructs a prepared statement from the sql query, sets a parameter in the prepared statement
     * using parameter, param, executes the sql query and returns the result.
     * @param sqlQuery
     * @param param
     * @return
     * @throws SQLException
     */
    T processStringQuery(String sqlQuery, String... param) throws SQLException;

    /**
     * Iterates through the result of the SQL query and stores each row into an object T.
     *
     * @param rs - The result of the sql query.
     * @return A list of object T where each entry in the list represents a row in the ResultSet.
     * @throws SQLException
     */
    T retrieveFromResultSet(ResultSet rs) throws SQLException;

}