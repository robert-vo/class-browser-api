package com.classbrowser.main.commons.util;

import org.apache.log4j.Logger;

import java.util.Map;

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
    final static String FORMAT_COLUMN               = " class.format ";
    final static String STATUS_COLUMN               = " class.status ";
    final static String SESSION_COLUMN              = " class.session ";
    final static String DEPARTMENT_COLUMN           = " class.department ";
    final static String DEPARTMENT_CRN_COLUMN       = " class.department_crn ";
    final static String COMPONENT_COLUMN            = " class.component ";
    final static String LOCATION_COLUMN             = " class.location ";
    final static String BUILDING_COLUMN             = " building_abbreviation ";
    final static String CREDIT_HOURS_COLUMN         = " credit_hours ";
    final static String CORE_COLUMN                 = "core";
    final static String LIKE_CORE                   = "%core%";
    final static String LIKE_WEEKEND_U              = "%Weekend U%";
    final static String ATTRIBUTES_COLUMN           = " class.attributes ";
    final static String ONLINE                      = "ONLINE";
    final static String HYBRID                      = "HYBRID";
    final static String FACE_TO_FACE_PARAM          = "FACETOFACE";
    final static String FACE_TO_FACE_VALUE          = "Face To Face";
    final static String STATUS                      = "STATUS";
    final static String SESSION                     = "SESSION";
    final static String OPEN                        = "Open";
    final static String CLOSED                      = "Closed";
    final static String DEPARTMENT                  = "DEPARTMENT";
    final static String DEPARTMENT_CRN              = "DEPARTMENT_CRN";
    final static String LOCATION                    = "LOCATION";
    final static String COMPONENT                   = "COMPONENT";
    final static String BUILDING                    = "BUILDING";
    final static String CREDIT_HOURS                = "CREDIT_HOURS";
    final static String CORE_ID                     = "CORE";
    final static String IS_CORE                     = "ISCORE";
    final static String MONDAY                      = "MONDAY";
    final static String TUESDAY                     = "TUESDAY";
    final static String WEDNESDAY                   = "WEDNESDAY";
    final static String THURSDAY                    = "THURSDAY";
    final static String FRIDAY                      = "FRIDAY";
    final static String SATURDAY                    = "SATURDAY";
    final static String SUNDAY                      = "SUNDAY";
    final static String WEEKEND_U                   = "WEEKENDU";
    final static String MONDAY_COLUMN               = " CLASS.Monday ";
    final static String TUESDAY_COLUMN              = " CLASS.Tuesday ";
    final static String WEDNESDAY_COLUMN            = " CLASS.Wednesday ";
    final static String THURSDAY_COLUMN             = " CLASS.Thursday ";
    final static String FRIDAY_COLUMN               = " CLASS.Friday ";
    final static String SATURDAY_COLUMN             = " CLASS.Saturday ";
    final static String SUNDAY_COLUMN               = " CLASS.Sunday ";
    final static String REGULAR_ACADEMIC_SESSION    = "Regular Academic Session";
    final static String MINI_SESSION                = "MIN";
    final static String SQL_QUERY_FOR_ALL_TERMS     = "SELECT * FROM class, building, department, terms, class_information " +
            "WHERE class.TERM_ID = ? AND " +
            "class.department = class_information.department AND " +
            "building.building_abbreviation = class.building_abbv AND " +
            "department.department_abbreviation = class.department AND " +
            "terms.term_id = class.term_id AND " +
            "class.department_crn = class_information.department_crn";
    final static String SQL_QUERY_FOR_ALL_INFORMATION = "SELECT * FROM CLASS_INFORMATION";

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
    
    private static String getStringToAppendForParameter(String parameter, String paramValue) throws Exception {
        switch (parameter.toUpperCase()) {
            case ONLINE:
                return createStringFromMatchingTrueFalseValues(ONLINE, paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, FORMAT_COLUMN);
            case HYBRID:
                return createStringFromMatchingTrueFalseValues(HYBRID, paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, FORMAT_COLUMN);
            case FACE_TO_FACE_PARAM:
                return createStringFromMatchingTrueFalseValues(FACE_TO_FACE_VALUE, paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, FORMAT_COLUMN);
            case STATUS:
                return createStringFromMatchingTrueFalseValues(OPEN, paramValue,
                        TRUE_VALUES + "|" + OPEN,
                        FALSE_VALUES + "|" + CLOSED,
                        EQUALS, NOT_EQUALS, STATUS_COLUMN);
            case SESSION:
                return getStringForSessionQuery(SESSION_COLUMN, EQUALS, paramValue);
            case DEPARTMENT:
                return createStringFromColumnConditionValue(DEPARTMENT_COLUMN, EQUALS, paramValue);
            case DEPARTMENT_CRN:
                return createStringFromColumnConditionValue(DEPARTMENT_CRN_COLUMN, EQUALS, paramValue);
            case LOCATION:
                return createStringFromColumnConditionValue(LOCATION_COLUMN, EQUALS, paramValue);
            case COMPONENT:
                return createStringFromColumnConditionValue(COMPONENT_COLUMN, EQUALS, paramValue);
            case BUILDING:
                return createStringFromColumnConditionValue(BUILDING_COLUMN, EQUALS, paramValue);
            case CREDIT_HOURS:
                return createStringFromColumnConditionValue(CREDIT_HOURS_COLUMN, EQUALS, paramValue);
            case IS_CORE:
                return createStringFromMatchingTrueFalseValues(LIKE_CORE, paramValue, TRUE_VALUES, FALSE_VALUES, LIKE, NOT_LIKE, ATTRIBUTES_COLUMN);
            case WEEKEND_U:
                return createStringFromMatchingTrueFalseValues(LIKE_WEEKEND_U, paramValue, TRUE_VALUES, FALSE_VALUES, LIKE, NOT_LIKE, ATTRIBUTES_COLUMN);
            case CORE_ID:
                return createStringMatchLikeEquals(paramValue, CORE_COLUMN);
            case MONDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, MONDAY_COLUMN);
            case TUESDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, TUESDAY_COLUMN);
            case WEDNESDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, WEDNESDAY_COLUMN);
            case THURSDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, THURSDAY_COLUMN);
            case FRIDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, FRIDAY_COLUMN);
            case SATURDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, SATURDAY_COLUMN);
            case SUNDAY:
                return createStringFromMatchingTrueFalseValues("1", paramValue, TRUE_VALUES, FALSE_VALUES, EQUALS, NOT_EQUALS, SUNDAY_COLUMN);
            default:
                if(!parameter.equalsIgnoreCase("term")) {
                    log.error("Invalid parameter " + parameter + " with value " + paramValue);
                }
        }
        return "";
    }

    private static String createStringMatchLikeEquals(String paramValue, String column) {
        final String LIKE = " like ";
        return AND + " (" + column + " = " +
                paramValue + OR + column + LIKE + "'" +
                paramValue + ",%'" + OR + column + LIKE + "'%," +
                paramValue + "')";
    }

    private static String createStringFromMatchingTrueFalseValues(String param, String paramValue,
                                                                  String trueValues, String falseValues,
                                                                  String equals, String notEquals,
                                                                  String columnName) throws Exception {
        if (paramValue.matches(trueValues)) {
            return createStringFromColumnConditionValue(columnName, equals, param);
        }
        else if (paramValue.matches(falseValues)) {
            return createStringFromColumnConditionValue(columnName, notEquals, param);
        }

        final String errorMessage = "Parameter " + param + " was given invalid value of " + paramValue +
                ". True values for include: 1, true, yes, open (status only)." +
                " False values include: 0, false, no, closed (status only).";
        log.error(errorMessage);
        throw new Exception(errorMessage);
    }

    private static String createStringFromColumnConditionValue(String column, String condition, String paramValue) {
        return AND + column + condition + "'" + paramValue + "'";
    }

    private static String getStringForSessionQuery(String column, String condition, String paramValue) throws Exception {
        switch (paramValue.toUpperCase()) {
            case "MIN":
                return createStringFromColumnConditionValue(column, condition, MINI_SESSION);
            case "1":
                return createStringFromColumnConditionValue(column, condition, REGULAR_ACADEMIC_SESSION);
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
                return createStringFromColumnConditionValue(column, condition, paramValue);
        }
        log.error("Invalid key for session query.");
        throw new Exception("Invalid key for session query.");
    }

    public static String buildSqlQueryForInformation(Map<String, String> allParams) throws Exception {
        if(allParams.size() == 0) {
            return SQL_QUERY_FOR_ALL_INFORMATION;
        }
        else {
            return buildSqlQuery(allParams, SQL_QUERY_FOR_ALL_INFORMATION + " WHERE");
        }
    }
}
