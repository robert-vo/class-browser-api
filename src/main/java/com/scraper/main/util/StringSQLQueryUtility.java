package com.scraper.main.util;

import java.util.Map;

public class StringSQLQueryUtility {
    final static String TRUE_VALUES    = "1|true|yes";
    final static String FALSE_VALUES   = "0|false|no";
    final static String NOT_EQUALS     = "<> ";
    final static String EQUALS         = "= ";
    final static String AND            = "AND";
    final static String FORMAT_COLUMN  = " class.format ";
    final static String ONLINE         = "'ONLINE'";
    final static String HYBRID         = "'HYBRID'";
    final static String FACE_TO_FACE   = "'Face To Face'";
    final static String SQL_QUERY_FOR_ALL_TERMS = "SELECT * FROM class, building, department, terms, class_information " +
            "WHERE class.TERM_ID = ? AND " +
            "building.building_abbreviation = class.building_abbv AND " +
            "department.department_abbreviation = class.department AND " +
            "terms.term_id = class.term_id AND " +
            "class.department_crn = class_information.department_crn ";

    public static String buildSqlQuery(Map<String, String> params) {

        StringBuilder sqlQuery = new StringBuilder(SQL_QUERY_FOR_ALL_TERMS);

        for (String s : params.keySet()) {
            if (s.equalsIgnoreCase("online")) {
                if (params.get(s).matches(TRUE_VALUES)) {
                    sqlQuery.append(AND)
                            .append(FORMAT_COLUMN)
                            .append(EQUALS)
                            .append(ONLINE);
                } else if (params.get(s).matches(FALSE_VALUES)) {
                    sqlQuery.append(AND)
                            .append(FORMAT_COLUMN)
                            .append(NOT_EQUALS)
                            .append(ONLINE);
                }
            }
            else if (s.equalsIgnoreCase("hybrid")) {
                if (params.get(s).matches(TRUE_VALUES)) {
                    sqlQuery.append(AND)
                            .append(FORMAT_COLUMN)
                            .append(EQUALS)
                            .append(HYBRID);
                } else if (params.get(s).matches(FALSE_VALUES)) {
                    sqlQuery.append(AND)
                            .append(FORMAT_COLUMN)
                            .append(NOT_EQUALS)
                            .append(HYBRID);
                }
            }
            else if (s.equalsIgnoreCase("facetoface")) {
                if (params.get(s).matches(TRUE_VALUES)) {
                    sqlQuery.append(AND)
                            .append(FORMAT_COLUMN)
                            .append(EQUALS)
                            .append(FACE_TO_FACE);
                } else if (params.get(s).matches(FALSE_VALUES)) {
                    sqlQuery.append(AND)
                            .append(FORMAT_COLUMN)
                            .append(NOT_EQUALS)
                            .append(FACE_TO_FACE);
                }
            }
            else if (s.equalsIgnoreCase("status")) {
                if (params.get(s).equalsIgnoreCase("open") || params.get(s).equalsIgnoreCase("1")) {
                    sqlQuery.append("AND class.status = 'Open' ");
                }
                else if (params.get(s).equalsIgnoreCase("closed") || params.get(s).equalsIgnoreCase("0")) {
                    sqlQuery.append("AND class.status = 'Closed' ");
                }
            }
            else if (s.equalsIgnoreCase("session")) {
                if (params.get(s).equals("1")) {
                    sqlQuery.append("AND class.session = 'Regular Academic Session' ");
                }
                else if (params.get(s).equals("MIN")) {
                    sqlQuery.append("AND class.session = 'MIN' ");
                }
                else {
                    sqlQuery.append("AND class.session = ").append(params.get(s)).append(" ");
                }
            }
            else if (s.equalsIgnoreCase("department")) {
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
