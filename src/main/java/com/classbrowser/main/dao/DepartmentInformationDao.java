package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.DepartmentInformation;
import com.classbrowser.main.pojo.ResponseInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DepartmentInformationDao extends InterfaceInformationDAO {
    @Override
    List<DepartmentInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

    @Override
    List<DepartmentInformation> processStringQuery(String sqlQuery, String param) throws SQLException;

    @Override
    ResponseInformation<List<DepartmentInformation>> getFromDatabaseAndResponseInfo(Map allParams) throws Exception;

    List<DepartmentInformation> selectAllDepartments(String departmentName) throws SQLException;
}
