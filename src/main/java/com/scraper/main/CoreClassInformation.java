package com.scraper.main;

/**
 * Created by Robert on 8/8/16.
 */
public class CoreClassInformation {
    public String department;
    public String department_crn;
    public String class_description;
    public String class_title;
    public int    credit_hours;
    public String core;
    public int    core_id;
    public String core_title;
    public int    hours_required;

    public CoreClassInformation(String department, String department_crn, String class_description, String class_title, int credit_hours, String core, int core_id, String core_title, int hours_required) {
        this.department = department;
        this.department_crn = department_crn;
        this.class_description = class_description;
        this.class_title = class_title;
        this.credit_hours = credit_hours;
        this.core = core;
        this.core_id = core_id;
        this.core_title = core_title;
        this.hours_required = hours_required;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment_crn() {
        return department_crn;
    }

    public void setDepartment_crn(String department_crn) {
        this.department_crn = department_crn;
    }

    public String getClass_description() {
        return class_description;
    }

    public void setClass_description(String class_description) {
        this.class_description = class_description;
    }

    public String getClass_title() {
        return class_title;
    }

    public void setClass_title(String class_title) {
        this.class_title = class_title;
    }

    public int getCredit_hours() {
        return credit_hours;
    }

    public void setCredit_hours(int credit_hours) {
        this.credit_hours = credit_hours;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public int getCore_id() {
        return core_id;
    }

    public void setCore_id(int core_id) {
        this.core_id = core_id;
    }

    public String getCore_title() {
        return core_title;
    }

    public void setCore_title(String core_title) {
        this.core_title = core_title;
    }

    public int getHours_required() {
        return hours_required;
    }

    public void setHours_required(int hours_required) {
        this.hours_required = hours_required;
    }
}
