package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.DepartmentInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object interface for the DepartmentInformation POJO.
 */
public interface DepartmentInformationDAO extends InterfaceInformationDAO {
    List<DepartmentInformation> selectAllDepartments() throws SQLException;

    @Override
    List<DepartmentInformation> processStringQuery(String sqlQuery, String... param) throws SQLException;

    @Override
    List<DepartmentInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

}
