package com.scraper.main;

import java.util.Map;

public class ResponseInformation {
    private int numberOfRows;
    private Map<String, String> parameters;

    public ResponseInformation(int numberOfRows, Map<String, String> parameters) {
        this.numberOfRows = numberOfRows;
        this.parameters = parameters;
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
}
