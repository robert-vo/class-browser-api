package com.classbrowser.main.controllers;

import com.classbrowser.main.commons.exception.InvalidArgumentException;
import com.classbrowser.main.dao.ClassInformationDAOImpl;
import com.classbrowser.main.dao.CoreClassInformationDAOImpl;
import com.classbrowser.main.pojo.ClassInformation;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.classbrowser.main.commons.util.ResponseEntityUtility.attemptDatabaseOperation;
import static com.classbrowser.main.commons.util.ResponseEntityUtility.generateErrorMessageResponseEntity;

@RestController
@RequestMapping("/api")
public class ClassBrowserAPIController {

    private static Logger log = Logger.getLogger(ClassBrowserAPIController.class);
    private final String REQUEST_MAPPING_URL_CORE = "/core={core}";
    private final String REQUEST_MAPPING_URL_TERM = "/classes/term={term}";

    @RequestMapping(value = REQUEST_MAPPING_URL_CORE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCoreCourses(@PathVariable(value = "core") String core) throws Exception {
        log.info("User accessing /api/core=" + core);
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

    @RequestMapping(value = REQUEST_MAPPING_URL_TERM, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllClassesFromTerm(@PathVariable(value = "term") String term,
                                                @RequestParam Map<String, String> params) throws Exception {
        log.info("User accessing /api/classes/term=" + term);

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

}
