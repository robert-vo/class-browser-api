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

    public static String buildSqlQuery(Map<String, String> params) throws Exception {
        log.debug("Building SQL query...");
        StringBuilder sqlQuery = new StringBuilder(SQL_QUERY_FOR_ALL_TERMS);

        for (String s : params.keySet()) {
            String paramValue = params.get(s);
            log.debug("Retrieved parameter of " + s + " with value of " + paramValue);

            switch (s.toUpperCase()) {
                case ONLINE:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, FORMAT_COLUMN, ONLINE));
                    break;
                case HYBRID:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, FORMAT_COLUMN, HYBRID));
                    break;
                case FACE_TO_FACE_PARAM:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, FORMAT_COLUMN, FACE_TO_FACE_VALUE));
                    break;
                case STATUS:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue,
                            TRUE_VALUES + "|" + OPEN,
                            FALSE_VALUES + "|" + CLOSED,
                            STATUS_COLUMN, OPEN));
                    break;
                case SESSION:
                    sqlQuery.append(getStringForSessionQuery(SESSION_COLUMN, EQUALS, paramValue));
                    break;
                case DEPARTMENT:
                    sqlQuery.append(createStringFromColumnConditionValue(DEPARTMENT_COLUMN, EQUALS, paramValue));
                    break;
                case DEPARTMENT_CRN:
                    sqlQuery.append(createStringFromColumnConditionValue(DEPARTMENT_CRN_COLUMN, EQUALS, paramValue));
                    break;
                case LOCATION:
                    sqlQuery.append(createStringFromColumnConditionValue(LOCATION_COLUMN, EQUALS, paramValue));
                    break;
                case COMPONENT:
                    sqlQuery.append(createStringFromColumnConditionValue(COMPONENT_COLUMN, EQUALS, paramValue));
                    break;
                case BUILDING:
                    sqlQuery.append(createStringFromColumnConditionValue(BUILDING_COLUMN, EQUALS, paramValue));
                    break;
                case CREDIT_HOURS:
                    sqlQuery.append(createStringFromColumnConditionValue(CREDIT_HOURS_COLUMN, EQUALS, paramValue));
                    break;
                case IS_CORE:
                    sqlQuery.append(createLikeStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, LIKE, NOT_LIKE, ATTRIBUTES_COLUMN, "%" + CORE_COLUMN + "%"));
                    break;
                case CORE_ID:
                    sqlQuery.append(createStringMatchLikeEquals(paramValue, CORE_COLUMN));
                    break;
                case MONDAY:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, MONDAY_COLUMN, "1"));
                    break;
                case TUESDAY:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, TUESDAY_COLUMN, "1"));
                    break;
                case WEDNESDAY:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, WEDNESDAY_COLUMN, "1"));
                    break;
                case THURSDAY:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, THURSDAY_COLUMN, "1"));
                    break;
                case FRIDAY:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, FRIDAY_COLUMN, "1"));
                    break;
                case SATURDAY:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, SATURDAY_COLUMN, "1"));
                    break;
                case SUNDAY:
                    sqlQuery.append(createStringFromMatchingTrueFalseValues(paramValue, TRUE_VALUES, FALSE_VALUES, SUNDAY_COLUMN, "1"));
                    break;
                default:
                    if(!s.equalsIgnoreCase("term")) {
                        log.error("Invalid parameter " + s + " with value " + paramValue);
                    }
                    break;
            }
        }
        log.info("SQL Query complete. Retrieved the following query: " + sqlQuery.toString());
        return sqlQuery.toString();
    }

    private static String createLikeStringFromMatchingTrueFalseValues(String paramValue, String trueValues, String falseValues, String equals, String notEquals, String columnName, String param) throws Exception{
        if (paramValue.matches(trueValues)) {
            return createStringFromColumnConditionValue(columnName, equals, param);
        }
        else if (paramValue.matches(falseValues)) {
            return createStringFromColumnConditionValue(columnName, notEquals, param);
        }
        log.error("Parameter " + param + " was given invalid value of " + paramValue + ". True values include: 1, true, yes, open (status only). False values include: 0, false, no, closed (status only).");
        throw new Exception("Parameter " + param + " was given invalid value of " + paramValue + ". True values include: 1, true, yes, open (status only). False values include: 0, false, no, closed (status only).");
    }

    private static String createStringMatchLikeEquals(String paramValue, String column) {
        final String LIKE = " like ";
        return AND + " (" + column + " = " +
                paramValue + OR + column + LIKE + "'" +
                paramValue + ",%'" + OR + column + LIKE + "'%," +
                paramValue + "')";
    }

    private static String createStringFromMatchingTrueFalseValues(String paramValue, String trueValues, String falseValues, String columnName, String param) throws Exception {
        if (param.matches(trueValues)) {
            return createStringFromColumnConditionValue(columnName, EQUALS, param);
        }
        else if (param.matches(falseValues)) {
            return createStringFromColumnConditionValue(columnName, NOT_EQUALS, param);
        }
        log.error("Parameter " + param + " was given invalid value of " + paramValue + ". True values include: 1, true, yes, open (status only). False values include: 0, false, no, closed (status only).");
        throw new Exception("Parameter " + param + " was given invalid value of " + paramValue + ". True values include: 1, true, yes, open (status only). False values include: 0, false, no, closed (status only).");
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
}
