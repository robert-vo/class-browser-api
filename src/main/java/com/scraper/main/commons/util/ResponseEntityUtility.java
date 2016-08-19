package com.scraper.main.commons.util;

import com.scraper.main.dao.ClassInformationDAOImpl;
import com.scraper.main.dao.CoreClassInformationDAOImpl;
import com.scraper.main.pojo.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseEntityUtility {
    public static ResponseEntity attemptDatabaseOperation(Object DAO, Map params) {
        try {
            if (DAO instanceof ClassInformationDAOImpl) {
                final ClassInformationDAOImpl classInformationDAOImpl = (ClassInformationDAOImpl) DAO;
                return new ResponseEntity<>(classInformationDAOImpl.getFromDatabaseAndResponseInfo(params), HttpStatus.OK);
            }
            else if (DAO instanceof CoreClassInformationDAOImpl) {
                final CoreClassInformationDAOImpl coreClassInformationDAOImpl = (CoreClassInformationDAOImpl) DAO;
                return new ResponseEntity<>(coreClassInformationDAOImpl.getFromDatabaseAndResponseInfo(params), HttpStatus.OK);
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
