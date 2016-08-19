package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.CoreClassInformation;
import com.classbrowser.main.pojo.ResponseInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CoreClassInformationDAO extends InterfaceInformationDAO {
    @Override
    List<CoreClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

    @Override
    List<CoreClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException;

    @Override
    ResponseInformation<List<CoreClassInformation>> getFromDatabaseAndResponseInfo(Map allParams) throws Exception;

    List<CoreClassInformation> selectAllCoreClass(String core) throws SQLException;
}
