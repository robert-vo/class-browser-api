package com.scraper.main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Robert on 8/14/16.
 */
public interface InterfaceDAO {

    void setDatabaseInformation() throws IOException;
    Object retrieveFromResultSet(ResultSet rs) throws SQLException;
    ResultSet processStringQuery(String sqlQuery, String param) throws SQLException;
    void handleJavaLangClassDriver();
}
