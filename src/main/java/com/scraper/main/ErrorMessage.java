package com.scraper.main;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private String error;
    private String errorMessage;
    private HttpStatus httpStatus;
    private int errorCode;

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
}
