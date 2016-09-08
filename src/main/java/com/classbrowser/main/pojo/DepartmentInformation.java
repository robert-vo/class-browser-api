package com.classbrowser.main.pojo;


import java.sql.ResultSet;
import java.sql.SQLException;

import static com.classbrowser.main.commons.util.StringSQLQueryUtility.getStringOrEmptyIfResultSetNull;

/**
 * Java POJO to represent the department information.
 *
 * @author Robert Vo
 */
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

    /**
     * Gets a DepartmentInformation from a ResultSet.
     *
     * @param rs The ResultSet that is of a single row from a table result.
     * @return A DepartmentInformation Object from the row in the ResultSet.
     * @throws SQLException
     */
    public static DepartmentInformation getPojoFromResultSet(ResultSet rs) throws SQLException {
        return new DepartmentInformation(
                getStringOrEmptyIfResultSetNull(rs, "DEPARTMENT_ABBREVIATION"),
                getStringOrEmptyIfResultSetNull(rs, "DEPARTMENT_NAME"));
    }
}
