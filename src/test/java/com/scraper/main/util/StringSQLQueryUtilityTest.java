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

    @Test
    public void testBuildSqlQueryForFaceToFaceFilterTrue() {
        paramMap.put("facetoface", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.format = 'Face To Face'");
    }

    @Test
    public void testBuildSqlQueryForFaceToFaceFilterFalse() {
        paramMap.put("FaceTOFACE", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.format <> 'Face To Face'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterOpen1() {
        paramMap.put("status", "open");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.status = 'Open'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterOpen2() {
        paramMap.put("status", "true");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.status = 'Open'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterClosed1() {
        paramMap.put("status", "closed");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.status = 'Closed'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterClosed2() {
        paramMap.put("status", "0");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.status = 'Closed'");
    }

    @Test
    public void testBuildSqlQueryForSession1() {
        paramMap.put("session", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.session = 'Regular Academic Session'");
    }

    @Test
    public void testBuildSqlQueryForSession2() {
        paramMap.put("session", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.session = 2");
    }

    @Test
    public void testBuildSqlQueryForSession3() {
        paramMap.put("session", "3");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.session = 3");
    }

    @Test
    public void testBuildSqlQueryForSession4() {
        paramMap.put("session", "4");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.session = 4");
    }

    @Test
    public void testBuildSqlQueryForSession5() {
        paramMap.put("session", "5");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.session = 5");
    }

    @Test
    public void testBuildSqlQueryForSession6() {
        paramMap.put("session", "6");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.session = 6");
    }

    @Test
    public void testBuildSqlQueryForSessionMIN() {
        paramMap.put("session", "MIN");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.session = 'MIN'");
    }


}