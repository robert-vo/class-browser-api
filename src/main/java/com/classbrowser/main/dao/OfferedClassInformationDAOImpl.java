package com.classbrowser.main.dao;

import com.classbrowser.main.commons.util.StringSQLQueryUtility;
import com.classbrowser.main.pojo.OfferedClassInformation;
import com.classbrowser.main.pojo.ResponseInformation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OfferedClassInformationDAOImpl extends AbstractInformationDAO implements OfferedClassInformationDAO {

    private static Logger log = Logger.getLogger(OfferedClassInformationDAOImpl.class);

    @Override
    public List<OfferedClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<OfferedClassInformation> allOfferedClassInformation = new LinkedList<>();
        while(rs.next()) {
            OfferedClassInformation c = OfferedClassInformation.getClassEntryFromResultSet(rs);
            allOfferedClassInformation.add(c);
        }
        return allOfferedClassInformation;
    }

    @Override
    public List<OfferedClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, param);
            log.info("Executing SQL Query: " + preparedStatement.toString());
            return retrieveFromResultSet(preparedStatement.executeQuery());
        }
        catch (Exception e) {
            log.error("Error processing SQL Query");
            log.error(e);
        }
        return null;
    }

    @Override
    public ResponseInformation<List<OfferedClassInformation>> getFromDatabaseAndResponseInfo(Map allParams) throws Exception {
        List<OfferedClassInformation> allClasses = selectAllClasses(allParams);
        int numberOfRows = allClasses.size();
        log.info("Retrieved " + numberOfRows + ".");
        return new ResponseInformation<>(numberOfRows, allParams, allClasses);
    }

    @Override
    public List<OfferedClassInformation> selectAllClasses(Map<String, String> allParams) throws Exception {
        final String SQL_QUERY_ALL_CLASSES = StringSQLQueryUtility.buildSqlQuery(allParams);
        return processStringQuery(SQL_QUERY_ALL_CLASSES, allParams.get("Term"));
    }

}
