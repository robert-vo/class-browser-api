package com.scraper.main.util;

import com.scraper.main.dao.ClassInformationDAO;
import com.scraper.main.dao.CoreClassInformationDAO;
import com.scraper.main.pojo.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseEntityUtility {
    public static ResponseEntity attemptDatabaseOperation(Object DAO, Map params) {
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
            return generateErrorMessageResponseEntity(e);
        }
    }

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
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
