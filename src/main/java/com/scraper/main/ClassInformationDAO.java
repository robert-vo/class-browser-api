package com.scraper.main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassInformationDAO implements InterfaceDAO {

    @Override
    public void setDatabaseInformation() throws IOException {

    }

    @Override
    public Object retrieveFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Object processStringQuery(String sqlQuery, String param) throws SQLException {
        return null;
    }

    @Override
    public void handleJavaLangClassDriver() {

    }
}
