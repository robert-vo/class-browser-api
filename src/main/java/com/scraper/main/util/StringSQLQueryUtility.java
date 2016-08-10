package com.scraper.main.util;

import java.util.Map;

public class StringSQLQueryUtility {


    final static String TRUE_VALUES                 = "1|true|yes";
    final static String FALSE_VALUES                = "0|false|no";
    final static String NOT_EQUALS                  = "<> ";
    final static String EQUALS                      = "= ";
    final static String AND                         = " AND";
    final static String FORMAT_COLUMN               = " class.format ";
    final static String STATUS_COLUMN               = " class.status ";
    final static String SESSION_COLUMN              = " class.session ";
    final static String DEPARTMENT_COLUMN           = " class.department ";
    final static String DEPARTMENT_CRN_COLUMN       = " class.department_crn ";
    final static String COMPONENT_COLUMN            = " class.component ";
    final static String LOCATION_COLUMN             = " class.location ";
    final static String BUILDING_COLUMN             = " building_abbreviation ";
    final static String CREDIT_HOURS_COLUMN         = " credit_hours ";
    final static String CORE_COLUMN                 = " core ";
    final static String ONLINE                      = "'ONLINE'";
    final static String HYBRID                      = "'HYBRID'";
    final static String FACE_TO_FACE                = "'Face To Face'";
    final static String OPEN                        = "'Open'";
    final static String CLOSED                      = "'Closed'";
    final static String REGULAR_ACADEMIC_SESSION    = "'Regular Academic Session'";
    final static String MINI_SESSION                = "'MIN'";
    final static String SQL_QUERY_FOR_ALL_TERMS     = "SELECT * FROM class, building, department, terms, class_information " +
            "WHERE class.TERM_ID = ? AND " +
            "building.building_abbreviation = class.building_abbv AND " +
            "department.department_abbreviation = class.department AND " +
            "terms.term_id = class.term_id AND " +
            "class.department_crn = class_information.department_crn";

    public static String buildSqlQuery(Map<String, String> params) {

        StringBuilder sqlQuery = new StringBuilder(SQL_QUERY_FOR_ALL_TERMS);

        for (String s : params.keySet()) {

            String paramValue = params.get(s);

            switch (s.toUpperCase()) {
                case "ONLINE":
                    if (paramValue.matches(TRUE_VALUES)) {
                        sqlQuery.append(createStringFromColumnConditionValue(FORMAT_COLUMN, EQUALS, ONLINE));
                    }
                    else if (paramValue.matches(FALSE_VALUES)) {
                        sqlQuery.append(createStringFromColumnConditionValue(FORMAT_COLUMN, NOT_EQUALS, ONLINE));
                    }
                    break;
                case "HYBRID":
                    if (paramValue.matches(TRUE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(EQUALS)
                                .append(HYBRID);
                    }
                    else if (paramValue.matches(FALSE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(NOT_EQUALS)
                                .append(HYBRID);
                    }
                    break;
                case "FACETOFACE":
                    if (paramValue.matches(TRUE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(EQUALS)
                                .append(FACE_TO_FACE);
                    }
                    else if (paramValue.matches(FALSE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(NOT_EQUALS)
                                .append(FACE_TO_FACE);
                    }
                    break;
                case "STATUS":
                    if (paramValue.matches(TRUE_VALUES + "|open")) {
                        sqlQuery.append(AND)
                                .append(STATUS_COLUMN)
                                .append(EQUALS)
                                .append(OPEN);
                    }
                    else if (paramValue.matches(FALSE_VALUES + "|closed")) {
                        sqlQuery.append(AND)
                                .append(STATUS_COLUMN)
                                .append(EQUALS)
                                .append(CLOSED);
                    }
                    break;
                case "SESSION":
                    switch (paramValue) {
                        case "1":
                            sqlQuery.append(AND)
                                    .append(SESSION_COLUMN)
                                    .append(EQUALS)
                                    .append(REGULAR_ACADEMIC_SESSION);
                            break;
                        case "MIN":
                            sqlQuery.append(AND)
                                    .append(SESSION_COLUMN)
                                    .append(EQUALS)
                                    .append(MINI_SESSION);
                            break;
                        case "2":
                        case "3":
                        case "4":
                        case "5":
                        case "6":
                            sqlQuery.append(AND)
                                    .append(SESSION_COLUMN)
                                    .append(EQUALS)
                                    .append(paramValue);
                            break;
                    }
                    break;
                case "DEPARTMENT":
                    sqlQuery.append(AND)
                            .append(DEPARTMENT_COLUMN)
                            .append(EQUALS)
                            .append("'")
                            .append(paramValue)
                            .append("'");
                    break;
                case "DEPARTMENT_CRN":
                    sqlQuery.append(AND)
                            .append(DEPARTMENT_CRN_COLUMN)
                            .append(EQUALS)
                            .append("'")
                            .append(paramValue)
                            .append("'");
                    break;
                case "LOCATION":
                    sqlQuery.append(AND)
                            .append(LOCATION_COLUMN)
                            .append(EQUALS)
                            .append("'")
                            .append(paramValue)
                            .append("'");
                    break;
                case "COMPONENT":
                    sqlQuery.append(AND)
                            .append(COMPONENT_COLUMN)
                            .append(EQUALS)
                            .append("'")
                            .append(paramValue)
                            .append("'");
                    break;
                case "BUILDING":
                    sqlQuery.append(AND)
                            .append(BUILDING_COLUMN)
                            .append(EQUALS)
                            .append("'")
                            .append(paramValue)
                            .append("'");
                    break;
                case "CREDIT_HOURS":
                    sqlQuery.append(AND)
                            .append(CREDIT_HOURS_COLUMN)
                            .append(EQUALS)
                            .append("'")
                            .append(paramValue)
                            .append("'");
                    break;
                case "CORE":
                    sqlQuery.append(AND)
                            .append(" (core = ")
                            .append(paramValue)
                            .append(" or core like '")
                            .append(paramValue)
                            .append(",%' or core like '%,")
                            .append(paramValue)
                            .append("')");
                    break;
                default:
                    break;
            }
        }
        return sqlQuery.toString();
    }

    private static String createStringFromColumnConditionValue(String column, String condition, String paramValue) {
        return AND + column + condition + paramValue;
    }
}
