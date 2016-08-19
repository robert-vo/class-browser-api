package com.scraper.main.controllers;

import com.scraper.main.tasks.ScheduleScraper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.scraper.main.commons.util.ResponseEntityUtility.generateErrorMessageResponseEntity;

@RestController
@RequestMapping("/")
public class TriggerScraperController {
    @RequestMapping(value = "/trigger={trigger}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity triggerClassScraping(@PathVariable(value = "trigger") String trigger) {
        if (trigger.equals(System.getProperty("triggerKeyword"))) {
            try {
                ScheduleScraper.updateClasses();
                return new ResponseEntity<>("Scheduler complete.", HttpStatus.OK);
            }
            catch (Exception e) {
                return generateErrorMessageResponseEntity(e);
            }
        }
        else {
            return generateErrorMessageResponseEntity("Error");
        }
    }
}
