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
@CrossOrigin(origins = {"http://localhost:63342", "http://localhost:63343", "http://classbrowseruh.azurewebsites.net"})
@RestController
@RequestMapping("/api")
public class ClassBrowserAPIController {
    private static Logger log = Logger.getLogger(ClassBrowserAPIController.class);
    private final String REQUEST_MAPPING_URL_DEPARTMENT     = "/department";
    private final String REQUEST_MAPPING_URL_CORE           = "/core={coreID}";
    private final String REQUEST_MAPPING_URL_TERM           = "/classes/term={termID}";
    private final String REQUEST_MAPPING_URL_INFORMATION    = "/information";
    private final String CREDIT_HOURS_PARAMETER             = "credit-hours";
    private final String DEPARTMENT_PARAMETER               = "department";
    private final String CORE_PARAMETER                     = "core";

    /**
     * Endpoint for viewing information about core classes.
     *
     * @param coreID - The core ID, an integer from 1 to 10, that corresponds to the core class category.
     * @return A ResponseEntity with information about core classes, or an error message if anything went wrong.
     * Visit the following link for more information.
     * {@see https://github.com/robert-vo/class-browser-api/blob/master/endpoints/CORE.md}
     * @throws InvalidArgumentException Given an invalid argument.
     * @throws NumberFormatException Given an invalid core ID.
     */
    @RequestMapping(value = REQUEST_MAPPING_URL_CORE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCoreCourses(@PathVariable(value = "coreID") String coreID) throws InvalidArgumentException, NumberFormatException {
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
     * {@see https://github.com/robert-vo/class-browser-api/blob/master/endpoints/TERM.md}
     * @throws Exception
     */
    @RequestMapping(value = REQUEST_MAPPING_URL_TERM, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllClassesFromTerm(@PathVariable(value = "termID") String term,
                                                @RequestParam Map<String, String> params) throws Exception {
        final String TERM_PARAMETER = "term";

        log.info("User accessing /api/classes/" + TERM_PARAMETER + "=" + term);

        try {
            if (term.isEmpty()) {
                throw new Exception(TERM_PARAMETER + " is required.");
            }
            else if (OfferedClassInformation.isNotValidTerm(term)) {
                throw new InvalidArgumentException(TERM_PARAMETER);
            }
        }
        catch (Exception e) {
            return generateErrorMessageResponseEntity(TERM_PARAMETER);
        }

        OfferedClassInformationDAOImpl offeredClassInformationDAOImpl = new OfferedClassInformationDAOImpl();
        params.put(TERM_PARAMETER, term);

        log.info("User has inputted the following parameters -");
        params.keySet()
                .stream()
                .forEach(paramKey -> log.info("Parameter " + paramKey + " with the following value: " + params.get(paramKey)));

        return attemptDatabaseOperation(offeredClassInformationDAOImpl, params);
    }

    /**
     * Endpoint for viewing information about departments.
     *
     * @return A ResponseEntity with information about all of the departments.
     * Visit the following link for more information.
     * {@see https://github.com/robert-vo/class-browser-api/blob/master/endpoints/DEPARTMENT.md}
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
     * {@see https://github.com/robert-vo/class-browser-api/blob/master/endpoints/CLASS_INFORMATION.md}
     * @throws Exception
     */
    @RequestMapping(value = REQUEST_MAPPING_URL_INFORMATION, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getClassInformation(@RequestParam(value = DEPARTMENT_PARAMETER, required = false)     Optional<String> department,
                                              @RequestParam(value = CREDIT_HOURS_PARAMETER, required = false)   Optional<String> creditHours,
                                              @RequestParam(value = CORE_PARAMETER, required = false)           Optional<String> core) throws Exception {
        log.info("User accessing /api/information with parameters: ");
        if(department.isPresent()) {
            log.info(DEPARTMENT_PARAMETER + " = " + department.get());
        }
        if(creditHours.isPresent()) {
            log.info(CREDIT_HOURS_PARAMETER + " = " + creditHours.get());
        }
        if(core.isPresent()) {
            log.info(CORE_PARAMETER + " = " + core.get());
        }

        try {
            if (creditHours.isPresent() && OfferedClassInformation.isNotValidCreditHours(creditHours.get())) {
                log.error("The " + CREDIT_HOURS_PARAMETER + " parameter is invalid with value: " + creditHours.get());
                throw new InvalidArgumentException(CREDIT_HOURS_PARAMETER);
            }
            else if (core.isPresent() && OfferedClassInformation.isNotValidCore(core.get())) {
                log.error("The " + CORE_PARAMETER + " parameter is invalid with value: " + core.get());
                throw new InvalidArgumentException(CORE_PARAMETER);
            }
        }
        catch (Exception e) {
            if(e.getMessage().contains(CREDIT_HOURS_PARAMETER)) {
                return generateErrorMessageResponseEntity("CREDIT_HOUR");
            }
            else {
                return generateErrorMessageResponseEntity("CORE");
            }
        }

        ClassInformationDAOImpl classInformationDAOImpl = new ClassInformationDAOImpl();
        Map<String, String> params = new HashMap<>();

        if(department.isPresent()) {
            params.put(DEPARTMENT_PARAMETER, department.get());
        }
        if(creditHours.isPresent()) {
            params.put(CREDIT_HOURS_PARAMETER, creditHours.get());
        }
        if(core.isPresent()) {
            params.put(CORE_PARAMETER, core.get());
        }

        return attemptDatabaseOperation(classInformationDAOImpl, params);
    }
}
