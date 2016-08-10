package com.scraper.main.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StringSQLQueryUtilityTest {
    final String SQL_QUERY_FOR_ALL_TERMS = "SELECT * FROM class, building, department, terms, class_information " +
            "WHERE class.TERM_ID = ? AND " +
            "building.building_abbreviation = class.building_abbv AND " +
            "department.department_abbreviation = class.department AND " +
            "terms.term_id = class.term_id AND " +
            "class.department_crn = class_information.department_crn ";

    Map<String, String> paramMap;

    @Before
    public void setUp() {
        paramMap = new HashMap<>();
    }

    @Test
    public void testBuildSqlQueryForEmptyMap() throws Exception {
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS);
    }

    @Test
    public void testBuildSqlQueryForOnlineFilterTrue() throws Exception {
        paramMap.put("online", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.format = 'ONLINE'");
    }

    @Test
    public void testBuildSqlQueryForOnlineFilterFalse() throws Exception {
        paramMap.put("online", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.format <> 'ONLINE'");
    }

    @Test
    public void testBuildSqlQueryForHybridFilterTrue() {
        paramMap.put("HyBriD", "yes");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.format = 'HYBRID'");
    }

    @Test
    public void testBuildSqlQueryForHybridFilterFalse() {
        paramMap.put("HYBRID", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.format <> 'HYBRID'");
    }

}