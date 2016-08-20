package com.classbrowser.main.controllers;

import com.classbrowser.main.tasks.ScheduleScraper;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.classbrowser.main.commons.util.ResponseEntityUtility.generateErrorMessageResponseEntity;

@RestController
@RequestMapping("/")
public class TriggerScraperController {

    private static Logger log = Logger.getLogger(TriggerScraperController.class);

    @RequestMapping(value = "/trigger={trigger}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity triggerClassScraping(@PathVariable(value = "trigger") String trigger) {
        log.info("Attempting to start class scraping...");

        if (trigger.equals(System.getProperty("triggerKeyword"))) {
            try {
                ScheduleScraper.updateClasses();
                log.info("Class Scraping complete!");
                return new ResponseEntity<>("Class Scraping complete!", HttpStatus.OK);
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
