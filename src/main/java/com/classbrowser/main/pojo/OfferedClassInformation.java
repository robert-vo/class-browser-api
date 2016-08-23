package com.classbrowser.main.pojo;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

public class OfferedClassInformation {
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
    private String core[];
    private String lastUpdated;
    private static Logger log = Logger.getLogger(OfferedClassInformation.class);

    OfferedClassInformation() {

    }

    public OfferedClassInformation(HashMap<String, String> termInformation, String classTitle, HashMap<String, String> departmentInformation, String classStatus, String courseNumber, HashMap<String, Integer> seatInformation, HashMap<String, String> dateTimeInformation, String attributes, HashMap<String, Boolean> classDays, HashMap<String, String> instructorInformation, HashMap<String, String> locationInformation, String format, String description, String duration, String session, String component, String syllabus, String[] core, String lastUpdated) {
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
        this.core = core;
        this.lastUpdated = lastUpdated;
    }

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

    public String[] getCore() {
        return core;
    }

    public void setCore(String[] core) {
        this.core = core;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public static OfferedClassInformation getPojoFromResultSet(ResultSet rs) throws SQLException {
        HashMap<String, String> termInformation = new LinkedHashMap<>();
        termInformation.put("termID",  rs.getString("term_id"));
        termInformation.put("year",     rs.getString("year"));
        termInformation.put("semester", rs.getString("semester"));

        HashMap<String, Integer> seatInformation = new LinkedHashMap<>();
        seatInformation.put("seatsTaken",       rs.getInt("seats_taken"));
        seatInformation.put("seatsAvailable",   rs.getInt("seats_available"));
        seatInformation.put("seatsTotal",       rs.getInt("seats_total"));

        HashMap<String, String> dateTimeInformation = new LinkedHashMap<>();
        dateTimeInformation.put("startDate",    rs.getString("start_date"));
        dateTimeInformation.put("endDate",      rs.getString("end_date"));
        dateTimeInformation.put("startTime",    rs.getString("start_time"));
        dateTimeInformation.put("endTime",      rs.getString("end_time"));

        HashMap<String, Boolean> classDays = new LinkedHashMap<>();
        classDays.put("monday",     rs.getBoolean("monday"));
        classDays.put("tuesday",    rs.getBoolean("tuesday"));
        classDays.put("wednesday",  rs.getBoolean("wednesday"));
        classDays.put("thursday",   rs.getBoolean("thursday"));
        classDays.put("friday",     rs.getBoolean("friday"));
        classDays.put("saturday",   rs.getBoolean("saturday"));
        classDays.put("sunday",     rs.getBoolean("sunday"));

        HashMap<String, String> departmentInformation = new LinkedHashMap<>();
        departmentInformation.put("department",         rs.getString("department"));
        departmentInformation.put("departmentName",     rs.getString("department_name"));

        HashMap<String, String> locationInformation = new LinkedHashMap<>();
        locationInformation.put("location",               rs.getString("location"));
        locationInformation.put("buildingID",             rs.getString("building_id"));
        locationInformation.put("buildingAbbreviation",   rs.getString("building_abbreviation"));
        locationInformation.put("buildingName",           rs.getString("building_name"));

        HashMap<String, String> instructorInformation = new LinkedHashMap<>();
        instructorInformation.put("instructor",         rs.getString("instructor"));
        instructorInformation.put("instructorEmail",    rs.getString("instructor_email"));

        Optional<String> possibleCoreClasses = Optional.ofNullable(rs.getString("CORE"));
        String[] core = null;
        if(possibleCoreClasses.isPresent()) {
            core = possibleCoreClasses.get().split(",");
        }

        OfferedClassInformation c = new OfferedClassInformation(termInformation,
                rs.getString("CLASS_TITLE"), departmentInformation,
                rs.getString("Status"), rs.getString("crn"),
                seatInformation, dateTimeInformation, rs.getString("attributes"),
                classDays, instructorInformation, locationInformation,
                rs.getString("format"), rs.getString("CLASS_DESCRIPTION"),
                rs.getString("duration"), rs.getString("session"),
                rs.getString("component"), rs.getString("syllabus"),
                core,
                rs.getString("last_updated_at"));
        return c;
    }

    public static boolean isNotValidCore(String core) {
        log.info("Checking if " + core + " is valid.");
        int numericCore = Integer.parseInt(core);
        return !(numericCore > 0 && numericCore < 11);
    }

    public static boolean isNotValidTerm(String term) {
        log.info("Checking if " + term + " is valid.");
        int numericTerm = Integer.parseInt(term);
        return !(numericTerm >= 1950 && numericTerm % 10 == 0);
    }

    public static boolean isNotValidCreditHours(String creditHours) {
        log.info("Checking if " + creditHours + " is valid.");
        int numericCreditHours = Integer.parseInt(creditHours);
        return numericCreditHours < 0 || numericCreditHours > 9;
    }
}