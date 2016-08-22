package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.OfferedClassInformation;
import com.classbrowser.main.pojo.ResponseInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OfferedClassInformationDAO extends InterfaceInformationDAO {
    @Override
    List<OfferedClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

    @Override
    List<OfferedClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException;

    @Override
    ResponseInformation<List<OfferedClassInformation>> getFromDatabaseAndResponseInfo(Map allParams) throws Exception;

    List<OfferedClassInformation> selectAllClasses(Map<String, String> allParams) throws Exception;
}