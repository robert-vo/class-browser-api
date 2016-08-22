package com.classbrowser.main.controllers;

import com.classbrowser.main.commons.exception.InvalidArgumentException;
import com.classbrowser.main.dao.OfferedClassInformationDAOImpl;
import com.classbrowser.main.dao.CoreClassInformationDAOImpl;
import com.classbrowser.main.dao.DepartmentInformationDaoImpl;
import com.classbrowser.main.pojo.OfferedClassInformation;
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
    private final String REQUEST_MAPPING_URL_DEPARTMENT     = "/department";
    private final String REQUEST_MAPPING_URL_CORE           = "/core={core}";
    private final String REQUEST_MAPPING_URL_TERM           = "/classes/term={term}";
    private final String REQUEST_MAPPING_URL_INFORMATION    = "/information?department={department}&credit_hours={credit_hours}&core={core}";

    @RequestMapping(value = REQUEST_MAPPING_URL_CORE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCoreCourses(@PathVariable(value = "core") String core) throws Exception {
        log.info("User accessing /api/core=" + core);
        try {
            if(OfferedClassInformation.isNotValidCore(core)) {
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
            else if (OfferedClassInformation.isNotValidTerm(term)) {
                throw new InvalidArgumentException("Term");
            }
        }
        catch (Exception e) {
            return generateErrorMessageResponseEntity("Term");
        }

        OfferedClassInformationDAOImpl offeredClassInformationDAOImpl = new OfferedClassInformationDAOImpl();
        params.put("Term", term);

        return attemptDatabaseOperation(offeredClassInformationDAOImpl, params);
    }

    @RequestMapping(value = REQUEST_MAPPING_URL_DEPARTMENT, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllDepartments() throws Exception {
        log.info("User accessing /api/department");

        DepartmentInformationDaoImpl departmentInformationDao = new DepartmentInformationDaoImpl();
        Map<String, String> params = new HashMap<>();

        return attemptDatabaseOperation(departmentInformationDao, params);
    }

    @RequestMapping(value = REQUEST_MAPPING_URL_INFORMATION, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getClassInformation() throws Exception {
        return null;
    }
    ///api/information?department={department}&credit_hours={credit_hours}&core={core}

}
