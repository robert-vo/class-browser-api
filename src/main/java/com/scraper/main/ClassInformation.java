//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.scraper.main;

import java.util.HashMap;

public class ClassInformation {
    private HashMap<String, String> termInformation;
    private String classTitle;
    private HashMap<String, String> departmentInformation;
    private String classStatus;
    private String courseNumber;
    private HashMap<String, Integer> seatInformation;
    private HashMap<String, String> dateTimeInformation;
    private String attributes;
    private HashMap<String, Boolean> classDays;
    private HashMap<String, String> instructorInformation;
    private HashMap<String, String> locationInformation;
    private String format;
    private String description;
    private String duration;
    private String session;
    private String component;
    private String syllabus;
    private String lastUpdated;

    public HashMap<String, String> getTermInformation() {
        return termInformation;
    }

    public void setTermInformation(HashMap<String, String> termInformation) {
        this.termInformation = termInformation;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public HashMap<String, String> getDepartmentInformation() {
        return departmentInformation;
    }

    public void setDepartmentInformation(HashMap<String, String> departmentInformation) {
        this.departmentInformation = departmentInformation;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public HashMap<String, Integer> getSeatInformation() {
        return seatInformation;
    }

    public void setSeatInformation(HashMap<String, Integer> seatInformation) {
        this.seatInformation = seatInformation;
    }

    public HashMap<String, String> getDateTimeInformation() {
        return dateTimeInformation;
    }

    public void setDateTimeInformation(HashMap<String, String> dateTimeInformation) {
        this.dateTimeInformation = dateTimeInformation;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public HashMap<String, Boolean> getClassDays() {
        return classDays;
    }

    public void setClassDays(HashMap<String, Boolean> classDays) {
        this.classDays = classDays;
    }

    public HashMap<String, String> getInstructorInformation() {
        return instructorInformation;
    }

    public void setInstructorInformation(HashMap<String, String> instructorInformation) {
        this.instructorInformation = instructorInformation;
    }

    public HashMap<String, String> getLocationInformation() {
        return locationInformation;
    }

    public void setLocationInformation(HashMap<String, String> locationInformation) {
        this.locationInformation = locationInformation;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ClassInformation(HashMap<String, String> termInformation, String classTitle, HashMap<String, String> departmentInformation, String classStatus, String courseNumber, HashMap<String, Integer> seatInformation, HashMap<String, String> dateTimeInformation, String attributes, HashMap<String, Boolean> classDays, HashMap<String, String> instructorInformation, HashMap<String, String> locationInformation, String format, String description, String duration, String session, String component, String syllabus, String lastUpdated) {
        this.termInformation = termInformation;
        this.classTitle = classTitle;
        this.departmentInformation = departmentInformation;
        this.classStatus = classStatus;
        this.courseNumber = courseNumber;
        this.seatInformation = seatInformation;
        this.dateTimeInformation = dateTimeInformation;
        this.attributes = attributes;
        this.classDays = classDays;
        this.instructorInformation = instructorInformation;
        this.locationInformation = locationInformation;
        this.format = format;
        this.description = description;
        this.duration = duration;
        this.session = session;
        this.component = component;
        this.syllabus = syllabus;
        this.lastUpdated = lastUpdated;
    }
}