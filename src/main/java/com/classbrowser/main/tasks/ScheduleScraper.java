package com.classbrowser.main.tasks;

import com.scraper.main.ClassScraper;
import com.scraper.main.DatabaseOperations;
import com.scraper.main.Term;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A component of the class browser api that updates the database on a schedule.
 */
@Component
public class ScheduleScraper {

    final static List<Term> terms = new ArrayList<>(Arrays.asList(Term.SUMMER_2016, Term.FALL_2016, Term.SPRING_2017));
    private static Logger log = Logger.getLogger(ScheduleScraper.class);

    /**
     * Runs Scraper for the given terms at midnight, CST.
     */
    @Scheduled(cron = "0 0 0 * * *", zone="America/Chicago")
    public static void updateClasses() {
        log.info("Starting scraper...");

        ClassScraper classScraper = new ClassScraper(terms);

        int pageLimit = 0;
        if(!System.getProperty("pageLimit").isEmpty()) {
            try {
                pageLimit = Integer.parseInt(System.getProperty("pageLimit"));
            }
            catch (NumberFormatException ex) {
                log.error("Error for page limit of value: " + System.getProperty("pageLimit"));
                log.error(ex);
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

    /**
     * Adds or updates class entries in the database.
     *
     * @param classScraper - The result from the scraping that stores a List of Classes.
     */
    private static void performDatabaseActionsFromScraper(ClassScraper classScraper) {
        log.info("Starting database actions for scraper with size of: " + classScraper.getAllClasses().size());
        DatabaseOperations.setJdbcDriver    (System.getProperty("jdbcDriver"));
        DatabaseOperations.setDatabaseTable (System.getProperty("databaseTable"));
        DatabaseOperations.setDatabaseURL   (System.getProperty("databaseURL") + "/" + System.getProperty("databaseTable"));
        DatabaseOperations.setUserName      (System.getProperty("databaseUserName"));
        DatabaseOperations.setPassWord      (System.getProperty("databasePassWord"));

        log.info("Updating the database...");
        DatabaseOperations.performDatabaseActions(classScraper.getAllClasses());
        log.info("Database operations complete.");
    }

}
