package com.scraper.main;

import com.scraper.main.util.StringSQLQueryUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ClassInformationDAO implements InterfaceDAO {

    static Properties properties = new Properties();
    static String jdbcDriver;
    static String databaseURL;
    static String databaseTable;
    static String userName;
    static String passWord;

    ClassInformationDAO() throws IOException {
        setDatabaseInformation();
        handleJavaLangClassDriver();
    }

    @Override
    public void setDatabaseInformation() throws IOException {
        InputStream input = new FileInputStream("config/config.properties");
        properties.load(input);
        jdbcDriver      = properties.getProperty("jdbcDriver");
        databaseTable   = properties.getProperty("databaseTable");
        databaseURL     = properties.getProperty("databaseURL") +  "/" + databaseTable;
        userName        = properties.getProperty("userName");
        passWord        = properties.getProperty("passWord");
    }

    @Override
    public void handleJavaLangClassDriver() {
        try {
            java.lang.Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<ClassInformation> allClassInformation = new LinkedList<>();
        while(rs.next()) {
            ClassInformation c = ClassInformation.getClassEntryFromResultSet(rs);
            allClassInformation.add(c);
        }
        return allClassInformation;
    }

    @Override
    public List<ClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, param);
            System.out.println(preparedStatement);
            return retrieveFromResultSet(preparedStatement.executeQuery());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseInformation<List<ClassInformation>> getFromDatabaseAndResponseInfo(Map allParams) throws Exception {
        List<ClassInformation> allClasses = selectAllClasses(allParams);
        int numberOfRows = allClasses.size();
        return new ResponseInformation<>(numberOfRows, allParams, allClasses);
    }

    public List<ClassInformation> selectAllClasses(Map<String, String> allParams) throws Exception {
        final String SQL_QUERY_ALL_CLASSES = StringSQLQueryUtility.buildSqlQuery(allParams);
        return processStringQuery(SQL_QUERY_ALL_CLASSES, allParams.get("Term"));
    }
}
