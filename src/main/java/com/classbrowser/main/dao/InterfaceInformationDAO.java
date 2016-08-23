package com.classbrowser.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface InterfaceInformationDAO<T> {
    T retrieveFromResultSet(ResultSet rs) throws SQLException;

    T processStringQuery(String sqlQuery, String param) throws SQLException;

}