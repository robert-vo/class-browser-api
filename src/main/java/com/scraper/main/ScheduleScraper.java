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
        DatabaseOperations.setPropertiesFileLocation("config/config.properties");
        DatabaseOperations.performDatabaseActions(classScraper.getAllClasses());
    }

}
