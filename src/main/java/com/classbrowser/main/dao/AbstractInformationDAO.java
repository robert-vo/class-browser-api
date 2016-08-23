package com.classbrowser.main.dao;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Optional;

public abstract class AbstractInformationDAO<T> {
    String jdbcDriver;
    String databaseURL;
    String databaseTable;
    String userName;
    String passWord;
    private static Logger log = Logger.getLogger(AbstractInformationDAO.class);

    AbstractInformationDAO() {
        setDatabaseInformation();
        handleJavaLangClassDriver();
    }

    private void setDatabaseInformation() {
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

    private void handleJavaLangClassDriver() {
        try {
            java.lang.Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            log.error(e);
            e.printStackTrace();
        }
    }

    public abstract T getFromDatabaseAndResponseInfo(Map params) throws Exception;

}
