package com.classbrowser.main.tasks;

import com.scraper.main.ClassScraper;
import com.scraper.main.DatabaseOperations;
import com.scraper.main.pojo.Class;
import com.scraper.main.pojo.Session;
import com.scraper.main.pojo.Term;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

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
    final static    String     defaultDatabaseURL = "jdbc:mysql://localhost/class";
    final static    String     defaultUserName    = "root";
    final static    String     defaultPassWord    = "password";
    static String jdbcDriver;
    static String databaseURL;
    static String userName;
    static String passWord;

    /**
     * Runs Scraper for the given terms at midnight, CST.
     * The scraper will read system properties to determine what to scrape.
     */
    @Scheduled(cron = "0 0 0 * * *", zone="America/Chicago")
    public static void updateCurrentClasses() {
        setDatabaseCredentials();

        if(isValidDatabaseCredentials()) {
            List<Term> allTermsToScrape = new LinkedList<>();
            List<Session> allSessionsToScrape = new LinkedList<>();
            int pageLimit;
            Optional<String> allTermsAsString = Optional.ofNullable(System.getProperty("termsToScrape"));
            Optional<String> allSessionsAsString = Optional.ofNullable(System.getProperty("sessionsToBeScraped"));

            if (!allTermsAsString.isPresent()) {
                throw new IllegalStateException("termsToScrape system property is missing. Aborting scheduled scraper.");
            }
            if(!allSessionsAsString.isPresent()) {
                throw new IllegalStateException("sessionToBeScraped system property is missing. Aborting scheduled scraper.");
            }

            for (String term : allTermsAsString.get().split(",")) {
                allTermsToScrape.add(Term.returnTermFromString(term.trim()));
            }

            for (String session : allSessionsAsString.get().split(",")) {
                allSessionsToScrape.add(Session.returnSessionFromString(session.trim()));
            }

            pageLimit = Integer.parseInt(Optional.ofNullable(System.getProperty("pageLimit"))
                    .orElse("0"));

            List<Class> allClasses = new LinkedList<>();

            allTermsToScrape
                    .stream()
                    .forEach(term ->
                            allSessionsToScrape
                                    .stream()
                                    .forEach(session -> {
                                        log.info("Starting scraper for term: " + term + " and session: " + session);
                                        ClassScraper classScraper = new ClassScraper(term);
                                        classScraper.setSessionOnScraper(session);
                                        classScraper.setPageLimit(pageLimit);
                                        classScraper.startScraper();
                                        allClasses.addAll(classScraper.getAllClasses());
                                    }));

            printMessageForAllClassForTerms(allTermsToScrape, allClasses);
            printMessageForSessionAndTerms(allTermsToScrape, allSessionsToScrape, allClasses);
            performDatabaseActionsForAllClasses(allClasses);
        }
        else {
            log.error("Unable to start the scraper due to invalid database credentials.");
        }
    }

    private static void printMessageForAllClassForTerms(List<Term> terms, List<Class> classes) {
        terms
            .stream()
            .forEach(term ->
                    log.info("Number of classes found for the term, " + term + " is: " +
                            classes
                                .stream()
                                .filter(aClass -> aClass.getTerm().equals(term))
                                .toArray().length));
    }

    private static void printMessageForSessionAndTerms(List<Term> terms, List<Session> sessions, List<Class> classes) {
        terms
            .stream()
            .forEach(term ->
                    sessions
                        .stream()
                        .forEach(session -> log.info("Number of classes for term " + term + " and session " + session +
                                " is:" + classes
                                            .stream()
                                            .filter(aClassTerm -> aClassTerm.getTerm().equals(term))
                                            .filter(aClassSession ->
                                                    aClassSession.getSession().equals("Regular Academic Session") ?
                                                            session.getSessionName().equals("1") :
                                                            aClassSession.getSession().equals(session.getSessionName()))
                                            .toArray().length)));
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

            performDatabaseActionsForAllClasses(classScraper.getAllClasses());

            log.info("Scheduled task is complete!");
        }
        else {
            log.error("Unable to start the scraper due to invalid database credentials.");
        }
    }

    /**
     * Adds or updates class entries in the database.
     *
     * @param allClasses - The List of Classes retrieved from the scraping.
     */
    private static void performDatabaseActionsForAllClasses(List<Class> allClasses) {
        log.info("Starting database actions for scraper with size of: " + allClasses.size());

        log.debug("Here at the credentials to be used for the database operations.");
        log.debug("JDBC Driver: " + jdbcDriver);
        log.debug("Database URL: " + databaseURL);
        log.debug("UserName: " + userName);
        log.debug("PassWord: " + passWord);

        try(DatabaseOperations databaseOperations = new DatabaseOperations(jdbcDriver, databaseURL, userName, passWord)) {
            log.info("Updating the database...");
            databaseOperations.performUpdateOrInsertForAllClass(allClasses);
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
