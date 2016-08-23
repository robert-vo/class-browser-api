package com.classbrowser.main.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassInformation {
    public String department;
    public String department_crn;
    public String class_description;
    public String class_title;
    public int credit_hours;
    public String core_id;

    public ClassInformation(String department, String department_crn, String class_description, String class_title, int credit_hours, String core_id) {
        this.department = department;
        this.department_crn = department_crn;
        this.class_description = class_description;
        this.class_title = class_title;
        this.credit_hours = credit_hours;
        this.core_id = core_id;
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

    public String getCore_id() {
        return core_id;
    }

    public void setCore_id(String core_id) {
        this.core_id = core_id;
    }

    public static ClassInformation getPojoFromResultSet(ResultSet rs) throws SQLException {
        return new ClassInformation(rs.getString("Department"),
                rs.getString("Department_crn"),
                rs.getString("class_description"),
                rs.getString("class_title"),
                rs.getInt("credit_hours"),
                rs.getString("core"));
    }
}
