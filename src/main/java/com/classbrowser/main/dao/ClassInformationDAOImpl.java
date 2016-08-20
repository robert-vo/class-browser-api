package com.classbrowser.main.dao;

import com.classbrowser.main.commons.util.StringSQLQueryUtility;
import com.classbrowser.main.pojo.ClassInformation;
import com.classbrowser.main.pojo.ResponseInformation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClassInformationDAOImpl extends AbstractInformationDAO implements ClassInformationDAO {

    private static Logger log = Logger.getLogger(ClassInformationDAOImpl.class);

    @Override
    public List<ClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<ClassInformation> allClassInformation = new LinkedList<>();
        while(rs.next()) {
            ClassInformation c = ClassInformation.getClassEntryFromResultSet(rs);
            allClassInformation.add(c);
        }
        return allClassInformation;
    }

    @Override
    public List<ClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, param);
            log.info("Executing SQL Query: " + preparedStatement.toString());
            return retrieveFromResultSet(preparedStatement.executeQuery());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseInformation<List<ClassInformation>> getFromDatabaseAndResponseInfo(Map allParams) throws Exception {
        List<ClassInformation> allClasses = selectAllClasses(allParams);
        int numberOfRows = allClasses.size();
        return new ResponseInformation<>(numberOfRows, allParams, allClasses);
    }

    @Override
    public List<ClassInformation> selectAllClasses(Map<String, String> allParams) throws Exception {
        final String SQL_QUERY_ALL_CLASSES = StringSQLQueryUtility.buildSqlQuery(allParams);
        return processStringQuery(SQL_QUERY_ALL_CLASSES, allParams.get("Term"));
    }

}
