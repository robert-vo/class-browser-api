package com.classbrowser.main.pojo;

/**
 * Stores the messages for the response status codes.
 *
 * @author Robert Vo
 */
public enum ResponseStatusCodeEnumConstant {
    SUCCESS (1, "Successful"),
    NOTHING (0, "No data fetched"),
    ERROR (-1, "Error");

    private int statusCode;
    private String message;

    ResponseStatusCodeEnumConstant(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
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
}
