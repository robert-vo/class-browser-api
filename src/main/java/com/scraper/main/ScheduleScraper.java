package com.scraper.main;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ScheduleScraper {

    /**
     * Runs Scraper for Summer 2016 and Fall 2016 classes at midnight.
     */
    @Scheduled(cron = "0 0 0 * * *", zone="America/Chicago")
    public void updateClasses() {

        System.out.println("Updating classes...");

        List<Term> terms = new ArrayList<>(Arrays.asList(Term.SUMMER_2016, Term.FALL_2016, Term.SPRING_2017));
        ClassScraper classScraper = new ClassScraper(terms);
        classScraper.startScraperForMultipleTerms();

        DatabaseOperations.setJdbcDriver    (System.getProperty("jdbcDriver"));
        DatabaseOperations.setDatabaseTable (System.getProperty("databaseTable"));
        DatabaseOperations.setDatabaseURL   (System.getProperty("databaseURL") + "/" + DatabaseOperations.databaseTable);
        DatabaseOperations.setUserName      (System.getProperty("databaseUserName"));
        DatabaseOperations.setPassWord      (System.getProperty("databasePassWord"));

        DatabaseOperations.performDatabaseActions(classScraper.getAllClasses());
    }

}
