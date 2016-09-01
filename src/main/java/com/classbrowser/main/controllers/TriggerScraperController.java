package com.classbrowser.main.controllers;

import com.classbrowser.main.tasks.ScheduleScraper;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.classbrowser.main.commons.util.ResponseEntityUtility.generateErrorMessageResponseEntity;

/**
 * RESTful API for triggering the class scraper on demand.
 *
 * @author Robert Vo
 */
@RestController
@RequestMapping("/")
public class TriggerScraperController {

    private static Logger log = Logger.getLogger(TriggerScraperController.class);

    /**
     * Triggers the class scraper.
     *
     * @param trigger The keyword necessary to run the scraper.
     *                The keyword is stored in the system properties under "triggerKeyword".
     * @return A ResponseEntity indicating if the scraping is successful or not.
     */
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
