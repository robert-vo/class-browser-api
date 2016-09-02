package com.classbrowser.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object interface with the expectation that all concrete POJO DAOs will implement these methods.
 *
 * @author Robert Vo
 */
public interface InterfaceInformationDAO<T> {
    /**
     * Constructs a prepared statement from the sql query, sets a parameter in the prepared statement
     * using parameter, param, executes the sql query and returns the result.
     * @param baseSQLQuery The base SQL query to be executed.
     * @param param Additional parameters to be applied to the base SQL query.
     * @return A List of object T where each entry in the list represents a row in the ResultSet.
     * @throws SQLException When a problem occurs with setting up the database or performing the SQL query.
     */
    List<T> processStringQuery(String baseSQLQuery, String... param) throws SQLException;

    /**
     * Iterates through the result of the SQL query and stores each row into an object T.
     *
     * This serves as a helper method for processStringQuery.
     *
     * @param rs - The result of the sql query.
     * @return A List of object T where each entry in the list represents a row in the ResultSet.
     * @throws SQLException When a problem occurs with setting up the database or performing the SQL query.
     */
    List<T> retrieveFromResultSet(ResultSet rs) throws SQLException;

}