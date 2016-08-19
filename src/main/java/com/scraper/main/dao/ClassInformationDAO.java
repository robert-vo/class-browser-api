package com.scraper.main.dao;

import com.scraper.main.pojo.ClassInformation;
import com.scraper.main.pojo.ResponseInformation;
import com.scraper.main.util.StringSQLQueryUtility;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ClassInformationDAO implements InterfaceDAO {

    static Properties properties = new Properties();
    static String jdbcDriver;
    static String databaseURL;
    static String databaseTable;
    static String userName;
    static String passWord;

    public ClassInformationDAO() throws IOException {
        setDatabaseInformation();
        handleJavaLangClassDriver();
    }

    @Override
    public void setDatabaseInformation() throws IOException {
        Optional<String> jdbcDriverProperty = Optional.ofNullable(System.getProperty("jdbcDriver"));
        Optional<String> databaseTableProperty = Optional.ofNullable(System.getProperty("databaseTable"));
        Optional<String> databaseURLProperty = Optional.ofNullable(System.getProperty("databaseURL"));
        Optional<String> userNameProperty = Optional.ofNullable(System.getProperty("databaseUserName"));
        Optional<String> passWordProperty = Optional.ofNullable(System.getProperty("databasePassWord"));

        jdbcDriver      = jdbcDriverProperty.isPresent() ? System.getProperty("jdbcDriver") : "com.mysql.jdbc.Driver";
        databaseTable   = databaseTableProperty.isPresent() ? System.getProperty("databaseTable") : "class";
        databaseURL     = databaseURLProperty.isPresent() ? System.getProperty("databaseURL") + "/" + databaseTable : "jdbc:mysql://localhost" + "/" + databaseTable;
        userName        = userNameProperty.isPresent() ? System.getProperty("databaseUserName") : "root";
        passWord        = passWordProperty.isPresent() ? System.getProperty("databasePassWord") : "password";
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
