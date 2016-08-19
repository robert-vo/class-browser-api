package com.scraper.main;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduleScraper {

    final static List<Term> terms = new ArrayList<>(Arrays.asList(Term.SUMMER_2016, Term.FALL_2016, Term.SPRING_2017));

    /**
     * Runs Scraper for Summer 2016 and Fall 2016 classes at midnight.
     */
    @Scheduled(cron = "0 0 0 * * *", zone="America/Chicago")
    public static void updateClasses() {
        System.out.println("Updating classes...");

        ClassScraper classScraper = new ClassScraper(terms);
        Optional<Integer> pageLimit = Optional.ofNullable(Integer.parseInt(System.getProperty("pageLimit")));

        if (pageLimit.isPresent() && pageLimit.get() != 0) {
            classScraper.setPageLimit(pageLimit.get());
        }

        classScraper.startScraperForMultipleTerms();
        performDatabaseActionsFromScraper(classScraper);
    }

    private static void performDatabaseActionsFromScraper(ClassScraper classScraper) {
        DatabaseOperations.setJdbcDriver    (System.getProperty("jdbcDriver"));
        DatabaseOperations.setDatabaseTable (System.getProperty("databaseTable"));
        DatabaseOperations.setDatabaseURL   (System.getProperty("databaseURL") + "/" + DatabaseOperations.databaseTable);
        DatabaseOperations.setUserName      (System.getProperty("databaseUserName"));
        DatabaseOperations.setPassWord      (System.getProperty("databasePassWord"));

        System.out.println("Driver used is " + DatabaseOperations.jdbcDriver);
        System.out.println("Table used is " + DatabaseOperations.databaseTable);
        System.out.println("URL used is " + DatabaseOperations.databaseURL);
        System.out.println("Username used is " + DatabaseOperations.userName);

        DatabaseOperations.performDatabaseActions(classScraper.getAllClasses());
        System.out.println("Scheduled task is complete!");
    }

}
