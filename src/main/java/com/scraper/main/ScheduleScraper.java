package com.scraper.main;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduleScraper {

    /**
     * Runs Scraper for Summer 2016 and Fall 2016 classes at midnight.
     */
    @Scheduled(cron = "0 0 0 * * *", zone="America/Chicago")
    public void updateClasses() {

        System.out.println("Updating classes...");

        List<Term> terms = new ArrayList<>(Arrays.asList(Term.SUMMER_2016, Term.FALL_2016));
        ClassScraper classScraper = new ClassScraper(terms);
        Optional<String> envVariable = Optional.ofNullable(System.getenv("pageLimit"));
        classScraper.setPageLimit(envVariable.isPresent() ? Integer.parseInt(envVariable.get()) : 1);
        classScraper.startScraperForMultipleTerms();

        DatabaseOperations.jdbcDriver = System.getProperty("jdbcDriver");
        DatabaseOperations.databaseTable = System.getProperty("databaseTable");
        DatabaseOperations.databaseURL = System.getProperty("databaseURL") + DatabaseOperations.databaseTable;
        DatabaseOperations.userName = System.getProperty("databaseUserName");
        DatabaseOperations.passWord = System.getProperty("databasePassWord");

//        DatabaseOperations.jdbcDriver      = "com.mysql.jdbc.Driver";
//        DatabaseOperations.databaseTable   = "class";
//        DatabaseOperations.databaseURL     = "jdbc:mysql://mydbinstance.cnotb9fanxgq.us-west-2.rds.amazonaws.com" +  "/" + DatabaseOperations.databaseTable;
//        DatabaseOperations.userName        = "awsuser";
//        DatabaseOperations.passWord        = "tJFsKo2SSR8V";

        DatabaseOperations.performDatabaseActions(classScraper.getAllClasses());
    }

}
