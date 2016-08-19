package com.scraper.main.dao;

import com.scraper.main.pojo.ClassInformation;
import com.scraper.main.pojo.ResponseInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ClassInformationDAO extends InterfaceInformationDAO {
    @Override
    List<ClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException;

    @Override
    List<ClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException;

    @Override
    ResponseInformation<List<ClassInformation>> getFromDatabaseAndResponseInfo(Map allParams) throws Exception;

    List<ClassInformation> selectAllClasses(Map<String, String> allParams) throws Exception;
}
