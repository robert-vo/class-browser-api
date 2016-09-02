package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.OfferedClassInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object interface for the OfferedClassInformation POJO.
 */
public interface OfferedClassInformationDAO extends InterfaceInformationDAO {

    List<OfferedClassInformation> selectAllOfferedClasses(Map<String, String> allParams) throws Exception;

    @Override
    List<OfferedClassInformation> processStringQuery(String baseSQLQuery, String... param) throws SQLException;

    @Override
    List<OfferedClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

}
