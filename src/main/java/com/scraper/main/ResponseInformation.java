package com.scraper.main;

import java.util.Map;

public class ResponseInformation<T> {
    private int numberOfRows;
    private Map<String, String> parameters;
    private T result;

    public ResponseInformation(int numberOfRows, Map<String, String> parameters, T result) {
        this.numberOfRows = numberOfRows;
        this.parameters = parameters;
        this.result = result;
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
