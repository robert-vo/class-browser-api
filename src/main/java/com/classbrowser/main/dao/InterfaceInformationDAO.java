package com.classbrowser.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface InterfaceInformationDAO<T> {
    T retrieveFromResultSet(ResultSet rs) throws SQLException;

    T processStringQuery(String sqlQuery, String param) throws SQLException;

    T getFromDatabaseAndResponseInfo(Map allParams) throws Exception;
}