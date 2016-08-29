package com.classbrowser.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object interface with the expectation that all concrete POJO DAOs will implement these methods.
 */
public interface InterfaceInformationDAO<T> {
    T retrieveFromResultSet(ResultSet rs) throws SQLException;

    T processStringQuery(String sqlQuery, String param) throws SQLException;

}