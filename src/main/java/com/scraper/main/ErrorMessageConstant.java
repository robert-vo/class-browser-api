package com.scraper.main;

/**
 * Created by Robert on 8/14/16.
 */
public enum ErrorMessageConstant {
    CORE ("Core must be a numeric value between 1 and 10, inclusive."),
    TERM ("Terms must be an integer starting from 1970 and incrementing by 10.");

    private final String message;

    ErrorMessageConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
