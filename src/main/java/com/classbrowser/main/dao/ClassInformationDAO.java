package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.ClassInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object interface for the ClassInformation POJO.
 */
public interface ClassInformationDAO extends InterfaceInformationDAO {
    List<ClassInformation> selectAllClassInformation(Map allParams) throws Exception;

    @Override
    List<ClassInformation> processStringQuery(String baseSQLQuery, String... param) throws SQLException;

    @Override
    List<ClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

}
