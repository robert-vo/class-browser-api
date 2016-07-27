//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.scraper.main;

import java.sql.Date;
import java.sql.Time;

public class Class {
    private Term term;
    private String classTitle;
    private String departmentAbbreviation;
    private String departmentCourseNumber;
    private Class.Status classStatus;
    private String courseNumber;
    private int seatsTaken;
    private int seatsAvailable;
    private int seatsTotal;
    private Date classStartDate;
    private Date classEndDate;
    private String attributes;
    private Time classStartTime;
    private Time classEndTime;
    private boolean isMondayClass;
    private boolean isTuesdayClass;
    private boolean isWednesdayClass;
    private boolean isThursdayClass;
    private boolean isFridayClass;
    private boolean isSaturdayClass;
    private boolean isSundayClass;
    private String instructorName;
    private String instructorEmail;
    private String location;
    private String buildingAbbreviation;
    private String buildingRoomNumber;
    private String format;
    private String description;
    private String duration;
    private String session;
    private String component;
    private String syllabus;
    private String lastUpdated;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Class(Term term, String classTitle, String departmentAbbreviation, String departmentCourseNumber, Class.Status classStatus, String courseNumber, int seatsTaken, int seatsAvailable, int seatsTotal, Date classStartDate, Date classEndDate, String attributes, Time classStartTime, Time classEndTime, boolean isMondayClass, boolean isTuesdayClass, boolean isWednesdayClass, boolean isThursdayClass, boolean isFridayClass, boolean isSaturdayClass, boolean isSundayClass, String instructorName, String instructorEmail, String location, String buildingAbbreviation, String buildingRoomNumber, String format, String description, String duration, String session, String component, String syllabus) {
        this.term = term;
        this.classTitle = classTitle;
        this.departmentAbbreviation = departmentAbbreviation;
        this.departmentCourseNumber = departmentCourseNumber;
        this.classStatus = classStatus;
        this.courseNumber = courseNumber;
        this.seatsTaken = seatsTaken;
        this.seatsAvailable = seatsAvailable;
        this.seatsTotal = seatsTotal;
        this.classStartDate = classStartDate;
        this.classEndDate = classEndDate;
        this.attributes = attributes;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.isMondayClass = isMondayClass;
        this.isTuesdayClass = isTuesdayClass;
        this.isWednesdayClass = isWednesdayClass;
        this.isThursdayClass = isThursdayClass;
        this.isFridayClass = isFridayClass;
        this.isSaturdayClass = isSaturdayClass;
        this.isSundayClass = isSundayClass;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.location = location;
        this.buildingAbbreviation = buildingAbbreviation;
        this.buildingRoomNumber = buildingRoomNumber;
        this.format = format;
        this.description = description;
        this.duration = duration;
        this.session = session;
        this.component = component;
        this.syllabus = syllabus;
    }

    public Term getTerm() {
        return this.term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getClassTitle() {
        return this.classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getDepartmentAbbreviation() {
        return this.departmentAbbreviation;
    }

    public void setDepartmentAbbreviation(String departmentAbbreviation) {
        this.departmentAbbreviation = departmentAbbreviation;
    }

    public String getDepartmentCourseNumber() {
        return this.departmentCourseNumber;
    }

    public void setDepartmentCourseNumber(String departmentCourseNumber) {
        this.departmentCourseNumber = departmentCourseNumber;
    }

    public Class.Status getClassStatus() {
        return this.classStatus;
    }

    public void setClassStatus(Class.Status classStatus) {
        this.classStatus = classStatus;
    }

    public String getCourseNumber() {
        return this.courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getSeatsTaken() {
        return this.seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    public int getSeatsAvailable() {
        return this.seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public int getSeatsTotal() {
        return this.seatsTotal;
    }

    public void setSeatsTotal(int seatsTotal) {
        this.seatsTotal = seatsTotal;
    }

    public Date getClassStartDate() {
        return this.classStartDate;
    }

    public void setClassStartDate(Date classStartDate) {
        this.classStartDate = classStartDate;
    }

    public Date getClassEndDate() {
        return this.classEndDate;
    }

    public void setClassEndDate(Date classEndDate) {
        this.classEndDate = classEndDate;
    }

    public String getAttributes() {
        return this.attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Time getClassStartTime() {
        return this.classStartTime;
    }

    public void setClassStartTime(Time classStartTime) {
        this.classStartTime = classStartTime;
    }

    public Time getClassEndTime() {
        return this.classEndTime;
    }

    public void setClassEndTime(Time classEndTime) {
        this.classEndTime = classEndTime;
    }

    public boolean isMondayClass() {
        return this.isMondayClass;
    }

    public void setMondayClass(boolean mondayClass) {
        this.isMondayClass = mondayClass;
    }

    public boolean isTuesdayClass() {
        return this.isTuesdayClass;
    }

    public void setTuesdayClass(boolean tuesdayClass) {
        this.isTuesdayClass = tuesdayClass;
    }

    public boolean isWednesdayClass() {
        return this.isWednesdayClass;
    }

    public void setWednesdayClass(boolean wednesdayClass) {
        this.isWednesdayClass = wednesdayClass;
    }

    public boolean isThursdayClass() {
        return this.isThursdayClass;
    }

    public void setThursdayClass(boolean thursdayClass) {
        this.isThursdayClass = thursdayClass;
    }

    public boolean isFridayClass() {
        return this.isFridayClass;
    }

    public void setFridayClass(boolean fridayClass) {
        this.isFridayClass = fridayClass;
    }

    public boolean isSaturdayClass() {
        return this.isSaturdayClass;
    }

    public void setSaturdayClass(boolean saturdayClass) {
        this.isSaturdayClass = saturdayClass;
    }

    public boolean isSundayClass() {
        return this.isSundayClass;
    }

    public void setSundayClass(boolean sundayClass) {
        this.isSundayClass = sundayClass;
    }

    public String getInstructorName() {
        return this.instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return this.instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBuildingAbbreviation() {
        return this.buildingAbbreviation;
    }

    public void setBuildingAbbreviation(String buildingAbbreviation) {
        this.buildingAbbreviation = buildingAbbreviation;
    }

    public String getBuildingRoomNumber() {
        return this.buildingRoomNumber;
    }

    public void setBuildingRoomNumber(String buildingRoomNumber) {
        this.buildingRoomNumber = buildingRoomNumber;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSession() {
        return this.session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getComponent() {
        return this.component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getSyllabus() {
        return this.syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public static enum Status {
        Open,
        Closed;

        private Status() {
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class aClass = (Class) o;

        if (seatsTaken != aClass.seatsTaken) return false;
        if (seatsAvailable != aClass.seatsAvailable) return false;
        if (seatsTotal != aClass.seatsTotal) return false;
        if (isMondayClass != aClass.isMondayClass) return false;
        if (isTuesdayClass != aClass.isTuesdayClass) return false;
        if (isWednesdayClass != aClass.isWednesdayClass) return false;
        if (isThursdayClass != aClass.isThursdayClass) return false;
        if (isFridayClass != aClass.isFridayClass) return false;
        if (isSaturdayClass != aClass.isSaturdayClass) return false;
        if (isSundayClass != aClass.isSundayClass) return false;
        if (term != aClass.term) return false;
        if (classTitle != null ? !classTitle.equals(aClass.classTitle) : aClass.classTitle != null) return false;
        if (departmentAbbreviation != null ? !departmentAbbreviation.equals(aClass.departmentAbbreviation) : aClass.departmentAbbreviation != null)
            return false;
        if (departmentCourseNumber != null ? !departmentCourseNumber.equals(aClass.departmentCourseNumber) : aClass.departmentCourseNumber != null)
            return false;
        if (classStatus != aClass.classStatus) return false;
        if (courseNumber != null ? !courseNumber.equals(aClass.courseNumber) : aClass.courseNumber != null)
            return false;
        if (classStartDate != null ? !classStartDate.equals(aClass.classStartDate) : aClass.classStartDate != null)
            return false;
        if (classEndDate != null ? !classEndDate.equals(aClass.classEndDate) : aClass.classEndDate != null)
            return false;
        if (attributes != null ? !attributes.equals(aClass.attributes) : aClass.attributes != null) return false;
        if (classStartTime != null ? !classStartTime.equals(aClass.classStartTime) : aClass.classStartTime != null)
            return false;
        if (classEndTime != null ? !classEndTime.equals(aClass.classEndTime) : aClass.classEndTime != null)
            return false;
        if (instructorName != null ? !instructorName.equals(aClass.instructorName) : aClass.instructorName != null)
            return false;
        if (instructorEmail != null ? !instructorEmail.equals(aClass.instructorEmail) : aClass.instructorEmail != null)
            return false;
        if (location != null ? !location.equals(aClass.location) : aClass.location != null) return false;
        if (buildingAbbreviation != null ? !buildingAbbreviation.equals(aClass.buildingAbbreviation) : aClass.buildingAbbreviation != null)
            return false;
        if (buildingRoomNumber != null ? !buildingRoomNumber.equals(aClass.buildingRoomNumber) : aClass.buildingRoomNumber != null)
            return false;
        if (format != null ? !format.equals(aClass.format) : aClass.format != null) return false;
        if (description != null ? !description.equals(aClass.description) : aClass.description != null) return false;
        if (duration != null ? !duration.equals(aClass.duration) : aClass.duration != null) return false;
        if (session != null ? !session.equals(aClass.session) : aClass.session != null) return false;
        if (component != null ? !component.equals(aClass.component) : aClass.component != null) return false;
        return syllabus != null ? syllabus.equals(aClass.syllabus) : aClass.syllabus == null;

    }

    @Override
    public int hashCode() {
        int result = term != null ? term.hashCode() : 0;
        result = 31 * result + (classTitle != null ? classTitle.hashCode() : 0);
        result = 31 * result + (departmentAbbreviation != null ? departmentAbbreviation.hashCode() : 0);
        result = 31 * result + (departmentCourseNumber != null ? departmentCourseNumber.hashCode() : 0);
        result = 31 * result + (classStatus != null ? classStatus.hashCode() : 0);
        result = 31 * result + (courseNumber != null ? courseNumber.hashCode() : 0);
        result = 31 * result + seatsTaken;
        result = 31 * result + seatsAvailable;
        result = 31 * result + seatsTotal;
        result = 31 * result + (classStartDate != null ? classStartDate.hashCode() : 0);
        result = 31 * result + (classEndDate != null ? classEndDate.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (classStartTime != null ? classStartTime.hashCode() : 0);
        result = 31 * result + (classEndTime != null ? classEndTime.hashCode() : 0);
        result = 31 * result + (isMondayClass ? 1 : 0);
        result = 31 * result + (isTuesdayClass ? 1 : 0);
        result = 31 * result + (isWednesdayClass ? 1 : 0);
        result = 31 * result + (isThursdayClass ? 1 : 0);
        result = 31 * result + (isFridayClass ? 1 : 0);
        result = 31 * result + (isSaturdayClass ? 1 : 0);
        result = 31 * result + (isSundayClass ? 1 : 0);
        result = 31 * result + (instructorName != null ? instructorName.hashCode() : 0);
        result = 31 * result + (instructorEmail != null ? instructorEmail.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (buildingAbbreviation != null ? buildingAbbreviation.hashCode() : 0);
        result = 31 * result + (buildingRoomNumber != null ? buildingRoomNumber.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (session != null ? session.hashCode() : 0);
        result = 31 * result + (component != null ? component.hashCode() : 0);
        result = 31 * result + (syllabus != null ? syllabus.hashCode() : 0);
        return result;
    }
}
