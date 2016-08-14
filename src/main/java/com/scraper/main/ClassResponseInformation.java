package com.scraper.main;

import java.util.List;

/**
 * Created by Robert on 8/14/16.
 */
public class ClassResponseInformation {
    private ResponseInformation responseInformation;
    private List<ClassInformation> classInformation;

    public ClassResponseInformation(ResponseInformation responseInformation, List<ClassInformation> classInformation) {
        this.responseInformation = responseInformation;
        this.classInformation = classInformation;
    }

    public List<ClassInformation> getClassInformation() {
        return classInformation;
    }

    public void setClassInformation(List<ClassInformation> classInformation) {
        this.classInformation = classInformation;
    }

    public ResponseInformation getResponseInformation() {
        return responseInformation;
    }

    public void setResponseInformation(ResponseInformation responseInformation) {
        this.responseInformation = responseInformation;
    }
}
