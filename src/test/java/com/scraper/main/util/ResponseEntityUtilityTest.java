package com.scraper.main.util;

import com.scraper.main.commons.util.ResponseEntityUtility;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class ResponseEntityUtilityTest {

    @Test
    public void attemptDatabaseOperationReturnsBadRequest() {
        assertTrue(ResponseEntityUtility.attemptDatabaseOperation("", new HashMap<>()).getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void generateErrorMessageResponseEntityReturnsBadRequest() {
        assertTrue(ResponseEntityUtility.generateErrorMessageResponseEntity(new HashMap<>()).getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void generateErrorMessageResponseEntityReturnsBadRequestForTermString() {
        assertTrue(ResponseEntityUtility.generateErrorMessageResponseEntity("Term").getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void generateErrorMessageResponseEntityReturnsBadRequestForException() {
        assertTrue(ResponseEntityUtility.generateErrorMessageResponseEntity(new Exception("")).getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

}