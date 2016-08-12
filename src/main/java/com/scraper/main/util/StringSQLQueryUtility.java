package com.scraper.main.util;

import java.util.Map;

public class StringSQLQueryUtility {


    final static String TRUE_VALUES                 = "1|true|yes(?i)";
    final static String FALSE_VALUES                = "0|false|no(?i)";
    final static String NOT_EQUALS                  = "<> ";
    final static String EQUALS                      = "= ";
    final static String AND                         = " AND";
    final static String OR                          = "OR";
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
    final static String CORE                        = "CORE";
    final static String REGULAR_ACADEMIC_SESSION    = "Regular Academic Session";
    final static String MINI_SESSION                = "MIN";
    final static String SQL_QUERY_FOR_ALL_TERMS     = "SELECT * FROM class, building, department, terms, class_information " +
            "WHERE class.TERM_ID = ? AND " +
            "building.building_abbreviation = class.building_abbv AND " +
            "department.department_abbreviation = class.department AND " +
            "terms.term_id = class.term_id AND " +
            "class.department_crn = class_information.department_crn";

    public static String buildSqlQuery(Map<String, String> params) throws Exception {

        StringBuilder sqlQuery = new StringBuilder(SQL_QUERY_FOR_ALL_TERMS);

        for (String s : params.keySet()) {

            String paramValue = params.get(s);

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
                case CORE:
                    sqlQuery.append(createStringMatchLikeEquals(paramValue, CORE_COLUMN));
                    break;
                default:
                    break;
            }
        }
        return sqlQuery.toString();
    }

    private static String createStringMatchLikeEquals(String paramValue, String column) {
        final String LIKE = " like ";
        return AND +
                " (" + column + " = " + paramValue +
                " " + OR + " " + column + LIKE + "'" +
                paramValue + ",%' " + OR + " " + column + LIKE + "'%," +
                paramValue + "')";
    }

    private static String createStringFromMatchingTrueFalseValues(String paramValue, String trueValues, String falseValues, String columnName, String param) throws Exception {
        if (paramValue.matches(trueValues)) {
            return createStringFromColumnConditionValue(columnName, EQUALS, param);
        }
        else if (paramValue.matches(falseValues)) {
            return createStringFromColumnConditionValue(columnName, NOT_EQUALS, param);
        }
        throw new Exception("Invalid parameter value for " + param);
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
        throw new Exception("Invalid key for session query.");
    }
}
