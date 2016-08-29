package com.classbrowser.main.pojo;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Java POJO that represents the entire response information at an endpoint.
 *
 * @param <T> The type of the result. It could be an ErrorMessage or one of the Java POJOs.
 * @author Robert Vo
 */
public class ResponseInformation<T> {
    private int statusCode;
    private String message;
    private int numberOfRows;
    private Map<String, String> parameters;
    private T result;
    private static Logger log = Logger.getLogger(ResponseInformation.class);

    public ResponseInformation(int numberOfRows, Map<String, String> parameters, T result) {
        if(numberOfRows < 0) {
            this.statusCode = ResponseStatusCodeEnumConstant.ERROR.getStatusCode();
            this.message = ResponseStatusCodeEnumConstant.ERROR.getMessage();
            log.warn("Number of rows is less than zero. Please check the data in the database.");
        }
        else if(numberOfRows == 0) {
            this.statusCode = ResponseStatusCodeEnumConstant.NOTHING.getStatusCode();
            this.message = ResponseStatusCodeEnumConstant.NOTHING.getMessage();
            log.warn("Number of rows is equal to zero. No data was fetched.");
        }
        else {
            this.statusCode = ResponseStatusCodeEnumConstant.SUCCESS.getStatusCode();
            this.message = ResponseStatusCodeEnumConstant.SUCCESS.getMessage();
            log.info("Success. Returning the items.");
        }
        this.numberOfRows = numberOfRows;
        this.parameters = parameters;
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
