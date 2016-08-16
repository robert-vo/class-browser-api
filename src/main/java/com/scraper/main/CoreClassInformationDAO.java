package com.scraper.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class CoreClassInformationDAO implements InterfaceDAO {

    static Properties properties = new Properties();
    static String jdbcDriver;
    static String databaseURL;
    static String databaseTable;
    static String userName;
    static String passWord;

    CoreClassInformationDAO() throws IOException {
        setDatabaseInformation();
        handleJavaLangClassDriver();
    }

    @Override
    public void setDatabaseInformation() throws IOException{
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
    public List<CoreClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<CoreClassInformation> allCoreClassInformation = new LinkedList<>();
        while(rs.next()) {
            CoreClassInformation c = CoreClassInformation.getCoreClassFromResultSet(rs);
            allCoreClassInformation.add(c);
        }
        return allCoreClassInformation;
    }

    @Override
    public List<CoreClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param + ",%");
            preparedStatement.setString(3, "%, " + param);
            preparedStatement.setString(4, param);
            return retrieveFromResultSet(preparedStatement.executeQuery());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseInformation<List<CoreClassInformation>> getFromDatabaseAndResponseInfo(Map allParams) throws SQLException {
        List<CoreClassInformation> allCoreClasses = selectAllCoreClass((String) allParams.get("Core"));
        int numberOfRows = allCoreClasses.size();
        return new ResponseInformation<>(numberOfRows, allParams, allCoreClasses);
    }

    public List<CoreClassInformation> selectAllCoreClass(String core) throws SQLException {
        final String SQL_QUERY_CORE_CLASSES = "SELECT * FROM class.class_information, class.core " +
                "where (core = ? or core like ? or core like ?) " +
                "and ? = core.core_id";
        return processStringQuery(SQL_QUERY_CORE_CLASSES, core);
    }


}
