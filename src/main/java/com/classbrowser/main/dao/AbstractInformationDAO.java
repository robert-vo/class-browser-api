package com.classbrowser.main.dao;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Optional;

/**
 *
 * @param <T>
 */
public abstract class AbstractInformationDAO<T> {

    String jdbcDriver = "com.mysql.jdbc.Driver";
    String databaseURL = "jdbc:mysql://localhost/class";
    String databaseTable = "class";
    String userName = "root";
    String passWord = "password";

    private static Logger log = Logger.getLogger(AbstractInformationDAO.class);

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

        log.info("Set up database information with the following credentials: ");
    }

    /**
     * Attempts to handle the jdbcDriver.
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
     * Retrieves data from the database and returns it as a ResponseEntity holding a List of a given POJO.
     *
     * @param params - Parameters passed through the URL, used to filter out unwanted data.
     * @return A type, T, which is implemented as a ResponseEntity<List<POJO>>.
     * @throws Exception
     */
    public abstract T getFromDatabaseAndResponseInfo(Map params) throws Exception;

}
