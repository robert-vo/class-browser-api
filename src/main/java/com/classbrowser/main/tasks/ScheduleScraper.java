package com.classbrowser.main.tasks;

import com.scraper.main.ClassScraper;
import com.scraper.main.DatabaseOperations;
import com.scraper.main.pojo.Term;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * A component of the class browser api that updates the database on a schedule.
 *
 * @author Robert Vo
 */
@Component
public class ScheduleScraper {

    final static    List<Term> terms              = new ArrayList<>(Arrays.asList(Term.SUMMER_2016,
                                                                    Term.FALL_2016, Term.SPRING_2017));
    private static  Logger     log                = Logger.getLogger(ScheduleScraper.class);
    final static    String     defaultJdbcDriver  = "com.mysql.jdbc.Driver";
    final static    String     defaultDatabaseURL = "jdbc:mysql://localhost/clfsass";
    final static    String     defaultUserName    = "root";
    final static    String     defaultPassWord    = "password";
    static String jdbcDriver;
    static String databaseURL;
    static String userName;
    static String passWord;

    /**
     * Runs Scraper for the given terms at midnight, CST.
     * The scraper will read system properties to infer what to scrape.
     */
    @Scheduled(cron = "0 0 0 * * *", zone="America/Chicago")
    public static void updateCurrentClasses() {

    }

    /**
     * Updates all classes for Summer 2016, Fall 2016, and Spring 2017, on demand.
     */
    public static void updateAllClasses() {
        log.info("Starting scraper...");

        ClassScraper classScraper = new ClassScraper(terms);

        int pageLimit = 0;

        setDatabaseCredentials();

        if(isValidDatabaseCredentials()) {
            Optional<String> pageLimitFromSystemProperties = Optional.ofNullable(System.getProperty("pageLimit"));

            if (pageLimitFromSystemProperties.isPresent()) {
                try {
                    log.info("Page limit constraint found in the system properties with value: " +
                            pageLimitFromSystemProperties.get());
                    pageLimit = Integer.parseInt(pageLimitFromSystemProperties.get());
                } catch (NumberFormatException ex) {
                    log.error("Error for page limit of value: " + System.getProperty("pageLimit"));
                    log.error(ex);
                    log.warn("Continuing class scraper with no page limit.");
                }
            }
            if (pageLimit > 0) {
                log.info("Found page limit. Setting page limit of " + pageLimit);
                classScraper.setPageLimit(pageLimit);
            }

            log.info("Scraper started!");

            classScraper.startScraperForMultipleTerms();

            log.info("Scraper finished!");

            performDatabaseActionsFromScraper(classScraper);

            log.info("Scheduled task is complete!");
        }
        else {
            log.error("Unable to start the scraper due to invalid database credentials.");
        }
    }



    /**
     * Adds or updates class entries in the database.
     *
     * @param classScraper - The result from the scraping that stores a List of Classes.
     */
    private static void performDatabaseActionsFromScraper(ClassScraper classScraper) {
        log.info("Starting database actions for scraper with size of: " + classScraper.getAllClasses().size());

        log.debug("Here at the credentials to be used for the database operations.");
        log.debug("JDBC Driver: " + jdbcDriver);
        log.debug("Database URL: " + databaseURL);
        log.debug("UserName: " + userName);
        log.debug("PassWord: " + passWord);

        try(DatabaseOperations databaseOperations = new DatabaseOperations(jdbcDriver, databaseURL, userName, passWord)) {
            log.info("Updating the database...");
            databaseOperations.performUpdateOrInsertForAllClass(classScraper.getAllClasses());
        }
        catch(SQLException sqle) {
            log.error("An error has occurred with the sql query. Please see the following error: " + sqle);
        }
        catch(ClassNotFoundException cnfe) {
            log.error("An error has occurred with setting the jdbc driver. Please see the following error: " + cnfe);
        }
        finally {
            log.info("Database operations complete.");
        }
    }

    /**
     * Attempts to establish a connection with the database and returns whether the connection is valid or not.
     *
     * @return Whether the connection was established or not.
     */
    private static boolean isValidDatabaseCredentials() {
        String connectionString = databaseURL + "?user=" + userName + "&password=" + passWord;
        log.info("Checking if the connection string is valid.");
        log.debug("Given the connection string: " + connectionString);
        try(Connection conn = DriverManager.getConnection(connectionString)) {
            return conn.isValid(5);
        } catch (SQLException ex) {
            log.error("Unable to establish a SQL connection. See errors below.");
            log.error("SQLException: " + ex.getMessage());
            log.error("SQLState: " + ex.getSQLState());
            log.error("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    /**
     * Sets the database credentials to be used for the scraper.
     */
    private static void setDatabaseCredentials() {
        log.info("Setting database credentials.");
        jdbcDriver    = Optional.ofNullable(System.getProperty("jcbcDriver")).orElse(defaultJdbcDriver);
        databaseURL   = Optional.ofNullable(System.getProperty("databaseURL")).orElse(defaultDatabaseURL);
        userName      = Optional.ofNullable(System.getProperty("databaseUserName")).orElse(defaultUserName);
        passWord      = Optional.ofNullable(System.getProperty("databasePassWord")).orElse(defaultPassWord);
        log.info("Database credentials set!");
    }

}
