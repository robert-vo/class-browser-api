package com.classbrowser.main.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.classbrowser.main.commons.util.StringSQLQueryUtility.getStringOrEmptyIfResultSetNull;

/**
 * Java POJO to represent a class that falls under one of the core categories.
 *
 * @author Robert Vo
 */
public class CoreClassInformation {
    public String department;
    public String department_crn;
    public String class_description;
    public String class_title;
    public double credit_hours;
    public int core_id;
    public String core_title;
    public int hours_required;

    public CoreClassInformation(String department, String department_crn, String class_description, String class_title,
                                double credit_hours, int core_id, String core_title, int hours_required) {
        this.department = department;
        this.department_crn = department_crn;
        this.class_description = class_description;
        this.class_title = class_title;
        this.credit_hours = credit_hours;
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

    public double getCredit_hours() {
        return credit_hours;
    }

    public void setCredit_hours(double credit_hours) {
        this.credit_hours = credit_hours;
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

    /**
     * Gets a CoreClassInformation from a ResultSet.
     *
     * @param rs The ResultSet that is of a single row from a table result.
     * @return A CoreClassInformation Object from the row in the ResultSet.
     * @throws SQLException
     */
    public static CoreClassInformation getPojoFromResultSet(ResultSet rs) throws SQLException {
        return new CoreClassInformation(getStringOrEmptyIfResultSetNull(rs,"Department"),
                getStringOrEmptyIfResultSetNull(rs, "Department_crn"),
                getStringOrEmptyIfResultSetNull(rs, "class_description"),
                getStringOrEmptyIfResultSetNull(rs, "class_title"),
                rs.getDouble("credit_hours"),
                rs.getInt("core_id"),
                getStringOrEmptyIfResultSetNull(rs, "core_title"),
                rs.getInt("hours_required"));
    }
}
