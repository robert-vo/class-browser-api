package com.classbrowser.main.pojo;


import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentInformation {
    public String departmentName;
    public String departmentFullName;

    public DepartmentInformation(String departmentName, String departmentFullName) {
        this.departmentName = departmentName;
        this.departmentFullName = departmentFullName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentFullName() {
        return departmentFullName;
    }

    public void setDepartmentFullName(String departmentFullName) {
        this.departmentFullName = departmentFullName;
    }

    public static DepartmentInformation getPojoFromResultSet(ResultSet rs) throws SQLException {
        return new DepartmentInformation(rs.getString("DEPARTMENT_ABBREVIATION"), rs.getString("DEPARTMENT_NAME"));
    }
}
