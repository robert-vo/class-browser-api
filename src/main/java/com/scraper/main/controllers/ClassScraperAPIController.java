package com.scraper.main.controllers;

import com.scraper.main.tasks.ScheduleScraper;
import com.scraper.main.commons.exception.InvalidArgumentException;
import com.scraper.main.dao.ClassInformationDAOImpl;
import com.scraper.main.dao.CoreClassInformationDAOImpl;
import com.scraper.main.pojo.ClassInformation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.scraper.main.commons.util.ResponseEntityUtility.attemptDatabaseOperation;
import static com.scraper.main.commons.util.ResponseEntityUtility.generateErrorMessageResponseEntity;

@RestController
@RequestMapping("/api")
public class ClassScraperAPIController {

    @RequestMapping(value = "/core={core}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCoreCourses(@PathVariable(value = "core") String core) throws Exception {
        try {
            if(ClassInformation.isNotValidCore(core)) {
                throw new InvalidArgumentException("Core");
            }
        }
        catch (NumberFormatException | InvalidArgumentException e) {
            return generateErrorMessageResponseEntity("Core");
        }

        CoreClassInformationDAOImpl coreClassInformationDAOImpl = new CoreClassInformationDAOImpl();
        Map<String, String> params = new HashMap<>();
        params.put("Core", core);

        return attemptDatabaseOperation(coreClassInformationDAOImpl, params);
    }

    @RequestMapping(value = "/term={term}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllClassesFromTerm(@PathVariable(value = "term") String term,
                                                @RequestParam Map<String, String> params) throws Exception {

        try {
            if (term.isEmpty()) {
                throw new Exception("Term is required.");
            }
            else if (ClassInformation.isNotValidTerm(term)) {
                throw new InvalidArgumentException("Term");
            }
        }
        catch (Exception e) {
            return generateErrorMessageResponseEntity("Term");
        }

        ClassInformationDAOImpl classInformationDAOImpl = new ClassInformationDAOImpl();
        params.put("Term", term);

        return attemptDatabaseOperation(classInformationDAOImpl, params);
    }

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
