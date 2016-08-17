package com.scraper.main.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface InterfaceDAO<T> {
    void setDatabaseInformation() throws IOException;

    void handleJavaLangClassDriver();

    T retrieveFromResultSet(ResultSet rs) throws SQLException;

    T processStringQuery(String sqlQuery, String param) throws SQLException;

    T getFromDatabaseAndResponseInfo(Map allParams) throws Exception;
}