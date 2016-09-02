package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.CoreClassInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object interface for the CoreClassInformation POJO.
 */
public interface CoreClassInformationDAO extends InterfaceInformationDAO {
    List<CoreClassInformation> selectAllCoreClass(String core) throws SQLException;

    @Override
    List<CoreClassInformation> processStringQuery(String baseSQLQuery, String... param) throws SQLException;

    @Override
    List<CoreClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

}
