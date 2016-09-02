package com.classbrowser.main.dao;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Optional;

/**
 * AbstractInformationDAO implements two methods to set the database credentials and the jdbc driver.
 * AbstractInformationDAO also has an abstract method which will be implemented to retrieve data from the database
 * by the classes that extend it.
 *
 * @param <T> The type of the rows returned from the database (usually a ResponseEntity with a List of a POJO).
 */
public abstract class AbstractInformationDAO<T> {

    String jdbcDriver = "com.mysql.jdbc.Driver";
    String databaseURL = "jdbc:mysql://localhost/class";
    String databaseTable = "class";
    String userName = "root";
    String passWord = "password";

    private static Logger log = Logger.getLogger(AbstractInformationDAO.class);

    /**
     * Default constructor to ensure that the database credentials are set.
     */
    AbstractInformationDAO() {
        setDatabaseInformation();
        handleJavaLangClassDriver();
    }

    /**
     * Sets up the jdbc driver, database URL, database table, username, and password
     * needed to establish a connection with a database.
     */
    private void setDatabaseInformation() {
        log.info("Setting up database information.");
        Optional<String> jdbcDriverProperty = Optional.ofNullable(System.getProperty("jdbcDriver"));
        Optional<String> databaseTableProperty = Optional.ofNullable(System.getProperty("databaseTable"));
        Optional<String> databaseURLProperty = Optional.ofNullable(System.getProperty("databaseURL"));
        Optional<String> userNameProperty = Optional.ofNullable(System.getProperty("databaseUserName"));
        Optional<String> passWordProperty = Optional.ofNullable(System.getProperty("databasePassWord"));

        if(jdbcDriverProperty.isPresent()) {
            jdbcDriver = jdbcDriverProperty.get();
        }

        if(databaseTableProperty.isPresent()) {
            databaseTable = databaseTableProperty.get();
        }

        if (databaseURLProperty.isPresent()) {
            databaseURL = databaseURLProperty.get() + "/" + databaseTable;
        }

        if (userNameProperty.isPresent()) {
            userName = userNameProperty.get();
        }

        if (passWordProperty.isPresent()) {
            passWord = passWordProperty.get();
        }

        log.info("Set up database information with the following credentials. \n\t\t\t" +
            "jdbcDriver: " + jdbcDriver + "\n\t\t\t" +
            "database URL: " + databaseURL + "\n\t\t\t" +
            "username: " + userName + "\n\t\t\t" +
            "password: " + passWord);
    }

    /**
     * Attempts to handle the jdbcDriver.
     * @throws ClassNotFoundException When the JDBC Driver is incorrect or invalid.
     */
    private void handleJavaLangClassDriver() {
        log.info("Attempting to handle the java jdbcDriver.");
        try {
            java.lang.Class.forName(jdbcDriver);
            log.info("Handled jdbcDriver of driver: " + jdbcDriver);
        } catch (ClassNotFoundException e) {
            log.error(e);
            e.printStackTrace();
        }
    }

    /**
     * Retrieves data from the database and returns it as a ResponseInformation holding a List of a given POJO.
     *
     * @param params - Parameters passed through the URL, used to filter out unwanted data.
     * @return A ResponseInformation holding a List of T, {@code ResponseInformation<List<T>>}
     *         to be returned to the URL request.
     * @throws Exception When an error occurs with retrieving or converting the data from the database queries.
     */
    public abstract T getFromDatabaseAndResponseInfo(Map params) throws Exception;

}
