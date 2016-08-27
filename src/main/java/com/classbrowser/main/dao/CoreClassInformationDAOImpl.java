package com.classbrowser.main.dao;

import com.classbrowser.main.pojo.CoreClassInformation;
import com.classbrowser.main.pojo.ResponseInformation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CoreClassInformationDAOImpl extends AbstractInformationDAO implements CoreClassInformationDAO {

    private static Logger log = Logger.getLogger(CoreClassInformationDAOImpl.class);

    /**
     * Iterates through the result of the sql query and stores each row into a List of CoreClassInformation.
     *
     * @param rs - The result of the sql query.
     * @return A List of CoreClassInformation where each entry in the list represents a row in the ResultSet.
     * @throws SQLException
     */
    @Override
    public List<CoreClassInformation> retrieveFromResultSet(ResultSet rs) throws SQLException {
        List<CoreClassInformation> allCoreClassInformation = new LinkedList<>();
        while(rs.next()) {
            CoreClassInformation c = CoreClassInformation.getPojoFromResultSet(rs);
            allCoreClassInformation.add(c);
        }
        return allCoreClassInformation;
    }

    @Override
    public List<CoreClassInformation> processStringQuery(String sqlQuery, String param) throws SQLException {
        try(Connection conn = DriverManager.getConnection(databaseURL, userName, passWord)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param + ",%");
            preparedStatement.setString(3, "%, " + param);
            preparedStatement.setString(4, param);
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
    public ResponseInformation<List<CoreClassInformation>> getFromDatabaseAndResponseInfo(Map params) throws Exception{
        List<CoreClassInformation> allCoreClasses = selectAllCoreClass((String) params.get("Core"));
        int numberOfRows = allCoreClasses.size();
        log.info("Retrieved " + numberOfRows + " items.");
        return new ResponseInformation<>(numberOfRows, params, allCoreClasses);
    }

    @Override
    public List<CoreClassInformation> selectAllCoreClass(String core) throws SQLException {
        final String SQL_QUERY_CORE_CLASSES = "SELECT * FROM class.class_information, class.core " +
                "where (core = ? or core like ? or core like ?) " +
                "and ? = core.core_id";
        return processStringQuery(SQL_QUERY_CORE_CLASSES, core);
    }

}
