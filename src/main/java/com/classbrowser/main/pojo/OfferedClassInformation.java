package com.classbrowser.main.pojo;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

import static com.classbrowser.main.commons.util.StringSQLQueryUtility.getStringOrEmptyIfResultSetNull;

/**
 * Java POJO to represent a class that is being offered for a particular term.
 *
 * @author Robert Vo
 */
public class OfferedClassInformation {
    private HashMap<String, Object> termInformation;
    private String classTitle;
    private HashMap<String, Object> departmentInformation;
    private String classStatus;
    private String classReferenceNumber;
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

    public OfferedClassInformation(HashMap<String, Object> termInformation, String classTitle,
                                   HashMap<String, Object> departmentInformation, String classStatus,
                                   String classReferenceNumber, HashMap<String, Integer> seatInformation,
                                   HashMap<String, String> dateTimeInformation, String attributes,
                                   HashMap<String, Boolean> classDays, HashMap<String, String> instructorInformation,
                                   HashMap<String, String> locationInformation, String format, String description,
                                   String duration, String session, String component, String syllabus, String[] core,
                                   String lastUpdated) {
        this.termInformation = termInformation;
        this.classTitle = classTitle;
        this.departmentInformation = departmentInformation;
        this.classStatus = classStatus;
        this.classReferenceNumber = classReferenceNumber;
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

    public HashMap<String, Object> getTermInformation() {
        return termInformation;
    }

    public void setTermInformation(HashMap<String, Object> termInformation) {
        this.termInformation = termInformation;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public HashMap<String, Object> getDepartmentInformation() {
        return departmentInformation;
    }

    public void setDepartmentInformation(HashMap<String, Object> departmentInformation) {
        this.departmentInformation = departmentInformation;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

    public String getClassReferenceNumber() {
        return classReferenceNumber;
    }

    public void setClassReferenceNumber(String classReferenceNumber) {
        this.classReferenceNumber = classReferenceNumber;
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

    /**
     * Gets a OfferedClassInformation from a ResultSet.
     *
     * @param rs The ResultSet that is of a single row from a table result.
     * @return A OfferedClassInformation Object from the row in the ResultSet.
     * @throws SQLException When retrieving from the ResultSet fails.
     */
    public static OfferedClassInformation getPojoFromResultSet(ResultSet rs) throws SQLException {
        HashMap<String, Object> termInformation = new LinkedHashMap<>();
        termInformation.put("termID",  getStringOrEmptyIfResultSetNull(rs, "term_id"));
        termInformation.put("year",     rs.getInt("year"));
        termInformation.put("semester", getStringOrEmptyIfResultSetNull(rs, "semester"));

        HashMap<String, Integer> seatInformation = new LinkedHashMap<>();
        seatInformation.put("seatsTaken",       rs.getInt("seats_taken"));
        seatInformation.put("seatsAvailable",   rs.getInt("seats_available"));
        seatInformation.put("seatsTotal",       rs.getInt("seats_total"));

        HashMap<String, String> dateTimeInformation = new LinkedHashMap<>();
        dateTimeInformation.put("startDate",    getStringOrEmptyIfResultSetNull(rs, "start_date"));
        dateTimeInformation.put("endDate",      getStringOrEmptyIfResultSetNull(rs, "end_date"));
        dateTimeInformation.put("startTime",    getStringOrEmptyIfResultSetNull(rs, "start_time"));
        dateTimeInformation.put("endTime",      getStringOrEmptyIfResultSetNull(rs, "end_time"));

        HashMap<String, Boolean> classDays = new LinkedHashMap<>();
        classDays.put("monday",     rs.getBoolean("monday"));
        classDays.put("tuesday",    rs.getBoolean("tuesday"));
        classDays.put("wednesday",  rs.getBoolean("wednesday"));
        classDays.put("thursday",   rs.getBoolean("thursday"));
        classDays.put("friday",     rs.getBoolean("friday"));
        classDays.put("saturday",   rs.getBoolean("saturday"));
        classDays.put("sunday",     rs.getBoolean("sunday"));

        HashMap<String, Object> departmentInformation = new LinkedHashMap<>();
        departmentInformation.put("department",         getStringOrEmptyIfResultSetNull(rs, "department"));
        departmentInformation.put("departmentName",     getStringOrEmptyIfResultSetNull(rs, "department_name"));
        departmentInformation.put("departmentCRN",      getStringOrEmptyIfResultSetNull(rs, "department_crn"));
        departmentInformation.put("credit_hours",       rs.getDouble("credit_hours"));

        HashMap<String, String> locationInformation = new LinkedHashMap<>();
        locationInformation.put("location",               getStringOrEmptyIfResultSetNull(rs, "location"));
        locationInformation.put("buildingID",             getStringOrEmptyIfResultSetNull(rs, "building_id"));
        locationInformation.put("buildingAbbreviation",   getStringOrEmptyIfResultSetNull(rs, "building_abbreviation"));
        locationInformation.put("buildingName",           getStringOrEmptyIfResultSetNull(rs, "building_name"));
        locationInformation.put("roomNumber",             getStringOrEmptyIfResultSetNull(rs, "building_room_num"));

        HashMap<String, String> instructorInformation = new LinkedHashMap<>();
        instructorInformation.put("instructor",         getStringOrEmptyIfResultSetNull(rs, "instructor"));
        instructorInformation.put("instructorEmail",    getStringOrEmptyIfResultSetNull(rs, "instructor_email"));

        Optional<String> possibleCoreClasses = Optional.ofNullable(getStringOrEmptyIfResultSetNull(rs, "CORE"));
        String[] core = new String[] { };
        if(possibleCoreClasses.isPresent() && !possibleCoreClasses.get().equals("")) {
            core = possibleCoreClasses.get().split(",");
        }

        return new OfferedClassInformation(termInformation,
                getStringOrEmptyIfResultSetNull(rs, "CLASS_TITLE"),
                departmentInformation,
                getStringOrEmptyIfResultSetNull(rs, "Status"),
                getStringOrEmptyIfResultSetNull(rs, "CRN"),
                seatInformation,
                dateTimeInformation,
                getStringOrEmptyIfResultSetNull(rs, "attributes"),
                classDays, instructorInformation, locationInformation,
                getStringOrEmptyIfResultSetNull(rs, "format"),
                getStringOrEmptyIfResultSetNull(rs, "CLASS_DESCRIPTION"),
                getStringOrEmptyIfResultSetNull(rs, "duration"),
                getStringOrEmptyIfResultSetNull(rs, "session"),
                getStringOrEmptyIfResultSetNull(rs, "component"),
                getStringOrEmptyIfResultSetNull(rs, "syllabus"),
                core,
                getStringOrEmptyIfResultSetNull(rs, "last_updated_at"));
    }

    /**
     * Determines the validity of the string representation for the core category.
     *
     * @param core The string representation of the core category.
     * @return Returns true if the core is NOT valid, false if the core is valid.
     */
    public static boolean isNotValidCore(String core) {
        log.info("Checking if " + core + " is valid.");
        int numericCore = Integer.parseInt(core);
        return !(numericCore > 0 && numericCore < 11);
    }

    /**
     * Determines the validity of the string representation for the term.
     *
     * @param term The string representation of the term.
     * @return Returns true if the term is NOT valid, false if the term is valid.
     */
    public static boolean isNotValidTerm(String term) {
        log.info("Checking if " + term + " is valid.");
        int numericTerm = Integer.parseInt(term);
        return !(numericTerm >= 1950 && numericTerm % 10 == 0);
    }

    /**
     * Determines the validity of the string representation for the credit hours.
     *
     * @param creditHours The string representation of the credit hours.
     * @return Returns true if the credit hours is NOT valid, false if the credit hours is valid.
     */
    public static boolean isNotValidCreditHours(String creditHours) {
        log.info("Checking if " + creditHours + " is valid.");
        double numericCreditHours = Double.parseDouble(creditHours);
        return numericCreditHours < 0 || numericCreditHours > 9;
    }
}