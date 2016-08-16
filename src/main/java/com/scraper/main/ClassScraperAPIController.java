package com.scraper.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.scraper.main.util.ResponseEntityUtility.*;

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
            return generateErrorMessageResponseEntity(e);
        }

        ClassInformationDAO classInformationDAO = new ClassInformationDAO();
        params.put("Term", term);

        return attemptDatabaseOperation(classInformationDAO, params);
    }

}
