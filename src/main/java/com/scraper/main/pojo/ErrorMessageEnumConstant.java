package com.scraper.main.pojo;

public enum ErrorMessageEnumConstant {
    CORE ("Core must be a numeric value between 1 and 10, inclusive."),
    TERM ("Terms must be an integer starting from 1970 and incrementing by 10."),
    ERROR ("There has seem to be an error with the URL requested. Please consult check out the API documentation at <https://github.com/robert-vo/class-browser-api>.");

    private final String message;

    ErrorMessageEnumConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
