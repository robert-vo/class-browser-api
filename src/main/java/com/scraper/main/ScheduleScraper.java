package com.scraper.main;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleScraper {

    @Scheduled(cron = "0 0 * * * 0,1,2,3,4,5,6")
    public void updateClasses() {
        //Scrape Classes
        //Update database
    }

}
