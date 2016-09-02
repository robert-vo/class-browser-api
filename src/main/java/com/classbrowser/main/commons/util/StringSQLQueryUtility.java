package com.classbrowser.main.commons.util;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * StringSQLQueryUtility is a class that will assist with the
 * creation of SQL Queries.
 *
 * @author Robert Vo
 */

public class StringSQLQueryUtility {

    private static Logger log = Logger.getLogger(StringSQLQueryUtility.class);
    final static String TRUE_VALUES                 = "(?i)1|true|yes";
    final static String FALSE_VALUES                = "(?i)0|false|no";
    final static String NOT_EQUALS                  = "<> ";
    final static String NOT_LIKE                    = "not like ";
    final static String EQUALS                      = "= ";
    final static String LIKE                        = "like ";
    final static String AND                         = " AND";
    final static String OR                          = " OR ";
    final static String CLASS_TABLE                 = " CLASS";
    final static String CLASS_INFORMATION_TABLE     = " CLASS_INFORMATION";
    final static String FORMAT_COLUMN               = "format";
    final static String STATUS_COLUMN               = "status";
    final static String SESSION_COLUMN              = "session";
    final static String DEPARTMENT_COLUMN           = "department";
    final static String DEPARTMENT_CRN_COLUMN       = "department_crn";
    final static String DEPARTMENT_CRN_PARAMETER    = "DEPARTMENT-CRN";
    final static String COMPONENT_COLUMN            = "component";
    final static String LOCATION_COLUMN             = "location";
    final static String BUILDING_COLUMN             = "building_abbreviation";
    final static String CREDIT_HOURS_COLUMN         = "credit_hours";
    final static String CREDIT_HOURS_PARAMETER      = "CREDIT-HOURS";
    final static String SUBJECT_PARAMETER           = "SUBJECT";
    final static String CORE_COLUMN                 = "core";
    final static String LIKE_CORE                   = "%core%";
    final static String LIKE_WEEKEND_U              = "%Weekend U%";
    final static String ATTRIBUTES_COLUMN           = "attributes";
    final static String ONLINE                      = "ONLINE";
    final static String HYBRID                      = "HYBRID";
    final static String FACE_TO_FACE_PARAM          = "FACE-TO-FACE";
    final static String FACE_TO_FACE_VALUE          = "Face To Face";
    final static String STATUS                      = "STATUS";
    final static String SESSION                     = "SESSION";
    final static String SYLLABUS                    = "SYLLABUS";
    final static String OPEN                        = "Open";
    final static String CLOSED                      = "Closed";
    final static String DEPARTMENT                  = "DEPARTMENT";
    final static String DEPARTMENT_CRN              = "DEPARTMENT_CRN";
    final static String LOCATION                    = "LOCATION";
    final static String COMPONENT                   = "COMPONENT";
    final static String BUILDING                    = "BUILDING";
    final static String CREDIT_HOURS                = "CREDIT_HOURS";
    final static String CORE_ID                     = "CORE";
    final static String CORE_ID_PARAMETER           = "CORE-ID";
    final static String IS_CORE                     = "IS_CORE";
    final static String IS_CORE_PARAMETER           = "IS-CORE";
    final static String MONDAY                      = "MONDAY";
    final static String TUESDAY                     = "TUESDAY";
    final static String WEDNESDAY                   = "WEDNESDAY";
    final static String THURSDAY                    = "THURSDAY";
    final static String FRIDAY                      = "FRIDAY";
    final static String SATURDAY                    = "SATURDAY";
    final static String SUNDAY                      = "SUNDAY";
    final static String WEEKEND_U                   = "WEEKENDU";
    final static String MONDAY_COLUMN               = "Monday";
    final static String TUESDAY_COLUMN              = "Tuesday";
    final static String WEDNESDAY_COLUMN            = "Wednesday";
    final static String THURSDAY_COLUMN             = "Thursday";
    final static String FRIDAY_COLUMN               = "Friday";
    final static String SATURDAY_COLUMN             = "Saturday";
    final static String SUNDAY_COLUMN               = "Sunday";
    final static String REGULAR_ACADEMIC_SESSION    = "Regular Academic Session";
    final static String MINI_SESSION                = "MIN";
    final static String SQL_QUERY_FOR_ALL_INFORMATION = "SELECT * FROM CLASS_INFORMATION";

    /**
     * Builds a SQL Query, in the form of a String, given parameters and a base sql query.
     *
     * @param params A Map that stores parameter -> parameter value pair(s).
     * @param sqlQuery The base SQL query to have params applied to.
     * @return A String SQL Query that is ready to be executed.
     * @throws Exception
     */
    public static String buildSqlQuery(Map<String, String> params, String sqlQuery) throws Exception {

        log.debug("Building SQL query...");

        StringBuilder sqlQueryToBuild = new StringBuilder(sqlQuery);
        boolean canReplaceAnd = !sqlQuery.contains(" AND ");

        for (String s : params.keySet()) {
            String paramValue = params.get(s);
            log.debug("Retrieved parameter of " + s + " with value of " + paramValue);
            String stringToAppend = getStringToAppendForParameter(s, paramValue);
            if(canReplaceAnd) {
                stringToAppend = stringToAppend.replace("AND", "");
                canReplaceAnd = false;
            }
            sqlQueryToBuild.append(stringToAppend);
        }

        log.info("SQL Query complete. Retrieved the following query: " + sqlQueryToBuild.toString());
        return sqlQueryToBuild.toString();
    }

    /**
     * Gets a string, which will be appended to the SQL Query, based on the parameter and paramValue.
     * The format of the string is: AND {columnName} {equals/not equals} {paramValue}
     *
     * @param parameter The parameter, or column, in the database.
     * @param paramValue - The value to be filtered in the database select expression.
     * @return A String that will be added to a String SQL Query.
     * @throws Exception
     */
    private static String getStringToAppendForParameter(String parameter, String paramValue) throws Exception {
        switch (parameter.toUpperCase()) {
            case ONLINE:
                return createStringFromMatchingTrueFalseValues(ONLINE, paramValue, TRUE_VALUES, FALSE_VALUES,
                        EQUALS, NOT_EQUALS, CLASS_TABLE, FORMAT_COLUMN);
            case HYBRID:
                return createStringFromMatchingTrueFalseValues(HYBRID, paramValue, TRUE_VALUES, FALSE_VALUES,
                        EQUALS, NOT_EQUALS, CLASS_TABLE, FORMAT_COLUMN);
            case FACE_TO_FACE_PARAM:
                return createStringFromMatchingTrueFalseValues(FACE_TO_FACE_VALUE, paramValue,
                        TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, FORMAT_COLUMN);
            case STATUS:
                return createStringFromMatchingTrueFalseValues(OPEN, paramValue,
                        TRUE_VALUES + "|" + OPEN,
                        FALSE_VALUES + "|" + CLOSED,
                        EQUALS, NOT_EQUALS, CLASS_TABLE, STATUS_COLUMN);
            case SESSION:
                return getStringForSessionQuery(SESSION_COLUMN, EQUALS, paramValue);
            case DEPARTMENT:
            case SUBJECT_PARAMETER:
                return createStringFromColumnConditionValue(CLASS_INFORMATION_TABLE, DEPARTMENT_COLUMN,
                        EQUALS, paramValue);
            case DEPARTMENT_CRN:
            case DEPARTMENT_CRN_PARAMETER:
                return createStringFromColumnConditionValue(CLASS_INFORMATION_TABLE, DEPARTMENT_CRN_COLUMN,
                        EQUALS, paramValue);
            case LOCATION:
                return createStringFromColumnConditionValue(CLASS_TABLE, LOCATION_COLUMN,
                        EQUALS, paramValue);
            case COMPONENT:
                return createStringFromColumnConditionValue(CLASS_TABLE, COMPONENT_COLUMN,
                        EQUALS, paramValue);
            case BUILDING:
                return createStringFromColumnConditionValue("", BUILDING_COLUMN,
                        EQUALS, paramValue);
            case CREDIT_HOURS:
            case CREDIT_HOURS_PARAMETER:
                return createStringFromColumnConditionValue("", CREDIT_HOURS_COLUMN,
                        EQUALS, paramValue);
            case IS_CORE:
            case IS_CORE_PARAMETER:
                return createStringFromMatchingTrueFalseValues(LIKE_CORE, paramValue,
                        TRUE_VALUES, FALSE_VALUES, LIKE, NOT_LIKE, CLASS_TABLE, ATTRIBUTES_COLUMN);
            case WEEKEND_U:
                return createStringFromMatchingTrueFalseValues(LIKE_WEEKEND_U, paramValue,
                        TRUE_VALUES, FALSE_VALUES, LIKE, NOT_LIKE, CLASS_TABLE, ATTRIBUTES_COLUMN);
            case CORE_ID:
            case CORE_ID_PARAMETER:
                return createStringMatchLikeEquals(paramValue, CORE_COLUMN);
            case MONDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue,
                        TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, MONDAY_COLUMN);
            case TUESDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue,
                        TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, TUESDAY_COLUMN);
            case WEDNESDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue,
                        TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, WEDNESDAY_COLUMN);
            case THURSDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue,
                        TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, THURSDAY_COLUMN);
            case FRIDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue,
                        TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, FRIDAY_COLUMN);
            case SATURDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue,
                        TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, SATURDAY_COLUMN);
            case SUNDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue,
                        TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, SUNDAY_COLUMN);
            case SYLLABUS:
                return createStringFromMatchingTrueFalseValues("Unavailable", paramValue,
                        FALSE_VALUES, TRUE_VALUES, EQUALS, NOT_EQUALS, CLASS_TABLE, SYLLABUS);
            default:
                if(!parameter.equalsIgnoreCase("term")) {
                    log.error("Invalid parameter " + parameter + " with value " + paramValue);
                }
        }
        return "";
    }

    /**
     * The purpose of this helper method is for the instance of using 'like' in a SQL Query.
     * For example, AND (core = 10 or core like '10,%' or core like '%,10')
     *
     * @param paramValue - The value to be filtered in the database select expression.
     * @param column - A string that represents the column in the database.
     * @return Gets a string will will be added to a SQL Query.
     */
    private static String createStringMatchLikeEquals(String paramValue, String column) {
        final String LIKE = " like ";
        return AND + " (" + column + " = " +
                paramValue + OR + column + LIKE + "'" +
                paramValue + ",%'" + OR + column + LIKE + "'%," +
                paramValue + "')";
    }

    /**
     * A helper method used while for the creation of the sql query.
     * @param param - The column in the database.
     * @param paramValue - The value to be filtered for in the database.
     * @param trueValues - Values that determine the condition set on the sql query.
     * @param falseValues - Values that determine the condition set on the sql query.
     * @param equals - The equality operation set in between the column and value.
     * @param notEquals - The equality operation set in between the column and value.
     * @param tableName - The table where the column resides in the database.
     * @param columnName - The full column name in the database, based off of param.
     * @return A String that will be added to a String SQL Query.
     * @throws Exception
     */
    private static String createStringFromMatchingTrueFalseValues(String param, String paramValue,
                                                                  String trueValues, String falseValues,
                                                                  String equals, String notEquals,
                                                                  String tableName, String columnName) throws Exception {
        if (paramValue.matches(trueValues)) {
            return createStringFromColumnConditionValue(tableName, columnName, equals, param);
        }
        else if (paramValue.matches(falseValues)) {
            return createStringFromColumnConditionValue(CLASS_TABLE, columnName, notEquals, param);
        }

        final String errorMessage = "Parameter " + param + " was given invalid value of " + paramValue +
                ". True values for include: 1, true, yes, open (status only)." +
                " False values include: 0, false, no, closed (status only).";
        log.error(errorMessage);
        throw new Exception(errorMessage);
    }

    /**
     * Helper method to createStringFromMatchingTrueFalseValues that returns a String to be appended
     * to the SQL Query.
     * @param tableName - The name of the database table.
     * @param column - The name of the database column.
     * @param condition - A value that determines the operator used for the condition.
     * @param paramValue - The value to be filtered in the database select expression.
     * @return Returns a String in the format of: AND {tableName.column/column} {=, <>} '{paramValue}'
     */
    private static String createStringFromColumnConditionValue(String tableName, String column,
                                                               String condition, String paramValue) {
        if(!tableName.isEmpty()) {
            return AND + tableName + "." + column + " " + condition + "'" + paramValue + "'";
        }
        else {
            return AND + " " + column + " " + condition + "'" + paramValue + "'";
        }
    }

    /**
     * Returns a String to be appended to the SQL query for the "session" parameter.
     * This is used because "MIN" refers to Mini-Session and
     * 1 refers to "Regular Academic Session".
     *
     * @param column - The database column.
     * @param condition - Condition to be applied to the sql expression.
     * @param paramValue - The value to be filtered in the database select expression.
     * @return A String in the format of: AND session {=/<>} {sessionNumber}
     * @throws Exception
     */
    private static String getStringForSessionQuery(String column, String condition, String paramValue) throws Exception {
        switch (paramValue.toUpperCase()) {
            case MINI_SESSION:
                return createStringFromColumnConditionValue(CLASS_TABLE, column,
                        condition, MINI_SESSION);
            case "1":
                return createStringFromColumnConditionValue(CLASS_TABLE, column,
                        condition, REGULAR_ACADEMIC_SESSION);
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
                return createStringFromColumnConditionValue(CLASS_TABLE, column,
                        condition, paramValue);
        }
        log.error("Invalid key for session query.");
        throw new Exception("Invalid key for session query.");
    }

    /**
     * Builds a SQL Query for contacting the class.class_information table.
     *
     * @param allParams - Possible parameter(s) to be appended to the initial SQL Query
     * @return - A SQL Query ready for execution.
     * @throws Exception
     */
    public static String buildSqlQueryForInformation(Map<String, String> allParams) throws Exception {
        return allParams.size() == 0 ?
                SQL_QUERY_FOR_ALL_INFORMATION : buildSqlQuery(allParams, SQL_QUERY_FOR_ALL_INFORMATION + " WHERE");
    }
}
