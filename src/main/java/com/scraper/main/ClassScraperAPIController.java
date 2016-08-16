package com.scraper.main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            return generateErrorMessage("Core");
        }

        CoreClassInformationDAO coreClassInformationDAO = new CoreClassInformationDAO();
        Map<String, String> params = new HashMap<>();
        params.put("Core", core);

        return attemptDatabaseOperation(coreClassInformationDAO, params);
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
            return generateErrorMessage(e);
        }

        ClassInformationDAO classInformationDAO = new ClassInformationDAO();
        params.put("Term", term);

        return attemptDatabaseOperation(classInformationDAO, params);
    }

    private ResponseEntity attemptDatabaseOperation(Object DAO, Map params) {
        try {
            if (DAO instanceof ClassInformationDAO) {
                final ClassInformationDAO classInformationDAO = (ClassInformationDAO) DAO;
                return new ResponseEntity<>(classInformationDAO.getFromDatabaseAndResponseInfo(params), HttpStatus.OK);
            }
            else if (DAO instanceof CoreClassInformationDAO) {
                final CoreClassInformationDAO coreClassInformationDAO = (CoreClassInformationDAO) DAO;
                return new ResponseEntity<>(coreClassInformationDAO.getFromDatabaseAndResponseInfo(params), HttpStatus.OK);
            }
            else {
                throw new Exception("Invalid Operation on DAO " + DAO.getClass());
            }
        }
        catch (Exception e) {
            return generateErrorMessage(e);
        }
    }

    private ResponseEntity generateErrorMessage(Object param) {
        ErrorMessage errorMessage;
        if(param instanceof Exception) {
            errorMessage = new ErrorMessage((Exception) param);
        }
        else if (param instanceof String) {
            errorMessage = new ErrorMessage((String) param);
        }
        else {
            errorMessage = new ErrorMessage(new Exception("Invalid Object"));
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
