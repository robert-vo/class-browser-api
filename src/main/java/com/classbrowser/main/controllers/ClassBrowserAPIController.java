package com.classbrowser.main.controllers;

import com.classbrowser.main.commons.exception.InvalidArgumentException;
import com.classbrowser.main.dao.ClassInformationDAOImpl;
import com.classbrowser.main.dao.CoreClassInformationDAOImpl;
import com.classbrowser.main.dao.DepartmentInformationDAOImpl;
import com.classbrowser.main.dao.OfferedClassInformationDAOImpl;
import com.classbrowser.main.pojo.OfferedClassInformation;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.classbrowser.main.commons.util.ResponseEntityUtility.attemptDatabaseOperation;
import static com.classbrowser.main.commons.util.ResponseEntityUtility.generateErrorMessageResponseEntity;

/**
 * RESTful API for viewing core class information, general class information,
 * department information and information about offered classes at the University of Houston, Main Campus.
 *
 * @author Robert Vo
 */
@RestController
@RequestMapping("/api")
public class ClassBrowserAPIController {

    private static Logger log = Logger.getLogger(ClassBrowserAPIController.class);
    private final String REQUEST_MAPPING_URL_DEPARTMENT     = "/department";
    private final String REQUEST_MAPPING_URL_CORE           = "/core={coreID}";
    private final String REQUEST_MAPPING_URL_TERM           = "/classes/term={term}";
    private final String REQUEST_MAPPING_URL_INFORMATION    = "/information";

    /**
     * Endpoint for viewing information about core classes.
     *
     * @param coreID - The core ID, an integer from 1 to 10, that corresponds to the core class category.
     * @return A ResponseEntity with information about core classes, or an error message if anything went wrong.
     * Visit the following link for more information.
     * https://github.com/robert-vo/class-browser-api/blob/master/endpoints/CORE.md
     * @throws Exception
     */
    @RequestMapping(value = REQUEST_MAPPING_URL_CORE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCoreCourses(@PathVariable(value = "coreID") String coreID) throws Exception {
        log.info("User accessing /api/core=" + coreID);
        try {
            if(OfferedClassInformation.isNotValidCore(coreID)) {
                throw new InvalidArgumentException("Core");
            }
        }
        catch (NumberFormatException | InvalidArgumentException e) {
            return generateErrorMessageResponseEntity("Core");
        }

        CoreClassInformationDAOImpl coreClassInformationDAOImpl = new CoreClassInformationDAOImpl();
        Map<String, String> params = new HashMap<>();
        params.put("Core", coreID);

        return attemptDatabaseOperation(coreClassInformationDAOImpl, params);
    }

    /**
     * Endpoint for viewing information about offered classes for a given term.
     *
     * @param term - The term number that corresponds to the year/semester.
     * @param params - Other parameters to filter out data from the result.
     * @return A ResponseEntity with information about offered classes for a given term, or an error message if anything went wrong.
     * Visit the following link for more information.
     * https://github.com/robert-vo/class-browser-api/blob/master/endpoints/TERM.md
     * @throws Exception
     */
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

    /**
     * Endpoint for viewing information about departments.
     *
     * @return A ResponseEntity with information about all of the departments.
     * Visit the following link for more information.
     * https://github.com/robert-vo/class-browser-api/blob/master/endpoints/DEPARTMENT.md
     * @throws Exception
     */
    @RequestMapping(value = REQUEST_MAPPING_URL_DEPARTMENT, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllDepartments() throws Exception {
        log.info("User accessing /api/department");

        DepartmentInformationDAOImpl departmentInformationDao = new DepartmentInformationDAOImpl();
        Map<String, String> params = new HashMap<>();

        return attemptDatabaseOperation(departmentInformationDao, params);
    }

    /**
     * Endpoint for viewing information about all classes, regardless if they are offered for the current term.
     *
     * @param department - The department where the class resides in.
     * @param creditHours - The number of credit hours the class fulfills, usually a number between 1 and 5, inclusive.
     * @param core - The core ID, an integer from 1 to 10, that corresponds to the core class category.
     * @return
     * Visit the following link for more information.
     * https://github.com/robert-vo/class-browser-api/blob/master/endpoints/CLASS_INFORMATION.md
     * @throws Exception
     */
    @RequestMapping(value = REQUEST_MAPPING_URL_INFORMATION, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getClassInformation(@RequestParam(value = "department", required = false)     Optional<String> department,
                                              @RequestParam(value = "credit_hours", required = false)   Optional<String> creditHours,
                                              @RequestParam(value = "core", required = false)           Optional<String> core) throws Exception {
        log.info("User accessing /api/information with parameters: ");
        if(department.isPresent()) {
            log.info("department = " + department.get());
        }
        if(creditHours.isPresent()) {
            log.info("credit_hours = " + creditHours.get());
        }
        if(core.isPresent()) {
            log.info("core = " + core.get());
        }

        try {
            if (creditHours.isPresent() && OfferedClassInformation.isNotValidCreditHours(creditHours.get())) {
                log.error("The credit_hour parameter is invalid with value: " + creditHours.get());
                throw new InvalidArgumentException("CREDIT_HOUR");
            }
            else if (core.isPresent() && OfferedClassInformation.isNotValidCore(core.get())) {
                log.error("The core parameter is invalid with value: " + core.get());
                throw new InvalidArgumentException("Core");
            }
        }
        catch (Exception e) {
            if(e.getMessage().contains("CREDIT_HOUR")) {
                return generateErrorMessageResponseEntity("CREDIT_HOUR");
            }
            else {
                return generateErrorMessageResponseEntity("CORE");
            }
        }

        ClassInformationDAOImpl classInformationDAOImpl = new ClassInformationDAOImpl();
        Map<String, String> params = new HashMap<>();
        if(department.isPresent()) {
            params.put("department", department.get());
        }
        if(creditHours.isPresent()) {
            params.put("credit_hours", creditHours.get());
        }
        if(core.isPresent()) {
            params.put("Core", core.get());
        }
        return attemptDatabaseOperation(classInformationDAOImpl, params);
    }
}
