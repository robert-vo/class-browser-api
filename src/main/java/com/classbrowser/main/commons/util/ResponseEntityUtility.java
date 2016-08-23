package com.classbrowser.main.commons.util;

import com.classbrowser.main.dao.*;
import com.classbrowser.main.pojo.ErrorMessage;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * ResponseEntityUtility include methods that will help create response entities
 * that will be returned in the ClassBrowserAPIController.
 *
 * @author Robert Vo
 */
public class ResponseEntityUtility {

    private static Logger log = Logger.getLogger(ResponseEntityUtility.class);

    /**
     * Performs database transactions on a given data access object (DAO).
     *
     * @param DAO
     * @param params
     * @return A ResponseEntity constructed from the attempted database transaction on a DAO.
     * If an error occurs, a ResponseEntity contructed from ErrorMessage will be returned.
     */
    public static ResponseEntity attemptDatabaseOperation(AbstractInformationDAO DAO, Map params) {
        try {
            log.info("Returning ResponseEntity for " + DAO.getClass().toString());
            return new ResponseEntity<>(DAO.getFromDatabaseAndResponseInfo(params), HttpStatus.OK);
        }
        catch (Exception e) {
            log.warn("Invalid Operation on DAO " + DAO);
            return generateErrorMessageResponseEntity(e);
        }
    }

    /**
     * @param param
     * @return ResponseEntity constructed using an ErrorMessage object.
     */
    public static ResponseEntity generateErrorMessageResponseEntity(Object param) {
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
        log.error("Error for " + param.toString() + " with message " + errorMessage.toString());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
