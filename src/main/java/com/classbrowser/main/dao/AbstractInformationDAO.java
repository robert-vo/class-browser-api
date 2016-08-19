package com.classbrowser.main.dao;

import java.util.Optional;

public abstract class AbstractInformationDAO {
    String jdbcDriver;
    String databaseURL;
    String databaseTable;
    String userName;
    String passWord;

    AbstractInformationDAO() {
        setDatabaseInformation();
        handleJavaLangClassDriver();
    }

    public void setDatabaseInformation() {
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

    public void handleJavaLangClassDriver() {
        try {
            java.lang.Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
