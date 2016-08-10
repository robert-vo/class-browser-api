package com.scraper.main.util;

import java.util.Map;

public class StringSQLQueryUtility {
    final static String TRUE_VALUES    = "1|true|yes";
    final static String FALSE_VALUES   = "0|false|no";
    final static String NOT_EQUALS     = "<> ";
    final static String EQUALS         = "= ";
    final static String AND            = "AND";
    final static String FORMAT_COLUMN  = " class.format ";
    final static String STATUS_COLUMN  = " class.status ";
    final static String SESSION_COLUMN = " class.session ";
    final static String ONLINE         = "'ONLINE'";
    final static String HYBRID         = "'HYBRID'";
    final static String FACE_TO_FACE   = "'Face To Face'";
    final static String OPEN           = "'Open'";
    final static String CLOSED         = "'Closed'";
    final static String REGULAR_ACADEMIC_SESSION         = "'Regular Academic Session'";
    final static String MINI_SESSION    = "'MIN'";
    final static String SQL_QUERY_FOR_ALL_TERMS = "SELECT * FROM class, building, department, terms, class_information " +
            "WHERE class.TERM_ID = ? AND " +
            "building.building_abbreviation = class.building_abbv AND " +
            "department.department_abbreviation = class.department AND " +
            "terms.term_id = class.term_id AND " +
            "class.department_crn = class_information.department_crn ";

    public static String buildSqlQuery(Map<String, String> params) {

        StringBuilder sqlQuery = new StringBuilder(SQL_QUERY_FOR_ALL_TERMS);

        for (String s : params.keySet()) {

            String paramValue = params.get(s);

            switch (s.toUpperCase()) {
                case "ONLINE":
                    if (paramValue.matches(TRUE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(EQUALS)
                                .append(ONLINE);
                    }
                    else if (paramValue.matches(FALSE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(NOT_EQUALS)
                                .append(ONLINE);
                    }
                    break;
                case "HYBRID":
                    if (params.get(s).matches(TRUE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(EQUALS)
                                .append(HYBRID);
                    }
                    else if (params.get(s).matches(FALSE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(NOT_EQUALS)
                                .append(HYBRID);
                    }
                    break;
                case "FACETOFACE":
                    if (params.get(s).matches(TRUE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(EQUALS)
                                .append(FACE_TO_FACE);
                    }
                    else if (params.get(s).matches(FALSE_VALUES)) {
                        sqlQuery.append(AND)
                                .append(FORMAT_COLUMN)
                                .append(NOT_EQUALS)
                                .append(FACE_TO_FACE);
                    }
                    break;
                case "STATUS":
                    if (params.get(s).matches(TRUE_VALUES + "|open")) {
                        sqlQuery.append(AND)
                                .append(STATUS_COLUMN)
                                .append(EQUALS)
                                .append(OPEN);
                    }
                    else if (params.get(s).matches(FALSE_VALUES + "|closed")) {
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
                default:
                    break;
            }
            if (s.equalsIgnoreCase("department")) {
                sqlQuery.append("AND class.department = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("department_crn")) {
                sqlQuery.append("AND class.department_crn = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("location")) {
                sqlQuery.append("AND class.location = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("component")) {
                sqlQuery.append("AND class.component = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("building")) {
                sqlQuery.append("AND building_abbreviation = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("credit_hours")) {
                sqlQuery.append("AND credit_hours = '").append(params.get(s)).append("' ");
            }
            else if (s.equalsIgnoreCase("core")) {
                sqlQuery.append("AND (core = ")
                        .append(params.get(s))
                        .append(" or core like '")
                        .append(params.get(s))
                        .append(",%' or core like '%,")
                        .append(params.get(s))
                        .append("') ");
            }
        }
        return sqlQuery.toString();
    }
}
