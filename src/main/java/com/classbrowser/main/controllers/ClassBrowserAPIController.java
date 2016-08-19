package com.classbrowser.main.controllers;

import com.classbrowser.main.commons.exception.InvalidArgumentException;
import com.classbrowser.main.dao.ClassInformationDAOImpl;
import com.classbrowser.main.dao.CoreClassInformationDAOImpl;
import com.classbrowser.main.pojo.ClassInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.classbrowser.main.commons.util.ResponseEntityUtility.*;

@RestController
@RequestMapping("/api")
public class ClassBrowserAPIController {

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

    @RequestMapping(value = "/class/term={term}", method = RequestMethod.GET)
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

}
