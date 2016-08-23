package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.DepartmentInformation;
import com.classbrowser.main.pojo.ResponseInformation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DepartmentInformationDAOImpl extends AbstractInformationDAO implements DepartmentInformationDAO {

    private static Logger log = Logger.getLogger(DepartmentInformationDAOImpl.class);

    @Override
    public List<DepartmentInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<DepartmentInformation> allDepartmentInformation = new LinkedList<>();
        while(rs.next()) {
            DepartmentInformation d = DepartmentInformation.getPojoFromResultSet(rs);
            allDepartmentInformation.add(d);
        }
        return allDepartmentInformation;
    }

    @Override
    public List<DepartmentInformation> processStringQuery(String sqlQuery, String param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            log.info("Executing SQL Query: " + preparedStatement.toString());
            return retrieveFromResultSet(preparedStatement.executeQuery());
        }
        catch (Exception e) {
            log.error("Error processing SQL Query.");
            log.error(e);
        }
        return null;
    }


    @Override
    public List<DepartmentInformation> selectAllDepartments(String departmentName) throws SQLException {
        final String SQL_QUERY_CORE_CLASSES = "select * from department;";
        return processStringQuery(SQL_QUERY_CORE_CLASSES, departmentName);
    }

    @Override
    public ResponseInformation<List<DepartmentInformation>> getFromDatabaseAndResponseInfo(Map params) throws Exception{
        List<DepartmentInformation> allCoreClasses = selectAllDepartments("");
        int numberOfRows = allCoreClasses.size();
        log.info("Retrieved " + numberOfRows + " items.");
        return new ResponseInformation<>(numberOfRows, params, allCoreClasses);
    }
}
