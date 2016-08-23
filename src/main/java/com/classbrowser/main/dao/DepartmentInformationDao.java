package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.DepartmentInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DepartmentInformationDAO extends InterfaceInformationDAO {
    @Override
    List<DepartmentInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

    @Override
    List<DepartmentInformation> processStringQuery(String sqlQuery, String param) throws SQLException;

    List<DepartmentInformation> selectAllDepartments(String departmentName) throws SQLException;
}
