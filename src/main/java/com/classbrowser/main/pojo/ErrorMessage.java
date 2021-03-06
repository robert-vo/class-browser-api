package com.classbrowser.main.pojo;

import org.springframework.http.HttpStatus;

/**
 * Java POJO to represent an error message.
 *
 * @author Robert Vo
 */
public class ErrorMessage {

    private String error;
    private String errorMessage;
    private HttpStatus httpStatus;
    private int errorCode;

    /**
     * Generates an ErrorMessage from a String parameter.
     *
     * @param invalidParameter The string representation of one of the ErrorMessageEnumConstant values.
     */
    public ErrorMessage(String invalidParameter) {
        ErrorMessageEnumConstant errorMessageEnumConstant = ErrorMessageEnumConstant.valueOf(invalidParameter.toUpperCase());
        this.error = errorMessageEnumConstant.name();
        this.errorMessage = errorMessageEnumConstant.getMessage();
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = HttpStatus.BAD_REQUEST.value();
    }

    /**
     * Generates an ErrorMessage from an Exception parameter.
     *
     * @param e An exception passed in from a failure somewhere else.
     */
    public ErrorMessage(Exception e) {
        this.error = "Java Exception thrown";
        this.errorMessage = e.getMessage();
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = HttpStatus.BAD_REQUEST.value();
    }

    public ErrorMessage(String error, String errorMessage, HttpStatus httpStatus, int errorCode) {
        this.error = error;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
