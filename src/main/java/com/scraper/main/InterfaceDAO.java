package com.scraper.main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface InterfaceDAO<T> {
    void setDatabaseInformation() throws IOException;
    T retrieveFromResultSet(ResultSet rs) throws SQLException;
    T processStringQuery(String sqlQuery, String param) throws SQLException;
    void handleJavaLangClassDriver();
}

