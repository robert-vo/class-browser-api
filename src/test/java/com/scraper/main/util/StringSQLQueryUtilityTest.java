package com.scraper.main.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StringSQLQueryUtilityTest {
    final static String SQL_QUERY_FOR_ALL_TERMS = "SELECT * FROM class, building, department, terms, class_information " +
            "WHERE class.TERM_ID = ? AND " +
            "building.building_abbreviation = class.building_abbv AND " +
            "department.department_abbreviation = class.department AND " +
            "terms.term_id = class.term_id AND " +
            "class.department_crn = class_information.department_crn";

    Map<String, String> paramMap;

    @Before
    public void setUp() throws Exception {
        paramMap = new HashMap<>();
    }

    @Test
    public void testBuildSqlQueryForEmptyMap() throws Exception {
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS);
    }

    @Test
    public void testBuildSqlQueryForOnlineFilterTrue() throws Exception {
        paramMap.put("online", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.format = 'ONLINE'");
    }

    @Test
    public void testBuildSqlQueryForOnlineFilterFalse() throws Exception {
        paramMap.put("online", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.format <> 'ONLINE'");
    }

    @Test
    public void testBuildSqlQueryForHybridFilterTrue() throws Exception {
        paramMap.put("HyBriD", "yes");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.format = 'HYBRID'");
    }

    @Test
    public void testBuildSqlQueryForHybridFilterFalse() throws Exception {
        paramMap.put("HYBRID", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.format <> 'HYBRID'");
    }

    @Test
    public void testBuildSqlQueryForFaceToFaceFilterTrue() throws Exception {
        paramMap.put("facetoface", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.format = 'Face To Face'");
    }

    @Test
    public void testBuildSqlQueryForFaceToFaceFilterFalse() throws Exception {
        paramMap.put("FaceTOFACE", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.format <> 'Face To Face'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterOpen1() throws Exception {
        paramMap.put("status", "OPEN");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.status = 'Open'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterOpen2() throws Exception {
        paramMap.put("status", "true");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.status = 'Open'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterClosed1() throws Exception {
        paramMap.put("status", "closed");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.status <> 'Open'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterClosed2() throws Exception {
        paramMap.put("status", "0");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.status <> 'Open'");
    }

    @Test
    public void testBuildSqlQueryForSession1() throws Exception {
        paramMap.put("session", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.session = 'Regular Academic Session'");
    }

    @Test
    public void testBuildSqlQueryForSession2() throws Exception {
        paramMap.put("session", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.session = '2'");
    }

    @Test
    public void testBuildSqlQueryForSession3() throws Exception {
        paramMap.put("session", "3");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.session = '3'");
    }

    @Test
    public void testBuildSqlQueryForSession4() throws Exception {
        paramMap.put("session", "4");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.session = '4'");
    }

    @Test
    public void testBuildSqlQueryForSession5() throws Exception {
        paramMap.put("session", "5");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.session = '5'");
    }

    @Test
    public void testBuildSqlQueryForSession6() throws Exception {
        paramMap.put("session", "6");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.session = '6'");
    }

    @Test
    public void testBuildSqlQueryForSessionMIN() throws Exception {
        paramMap.put("session", "MIN");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.session = 'MIN'");
    }

    @Test
    public void testBuildSqlQueryForDepartmentCOSC() throws Exception {
        paramMap.put("department", "COSC");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.department = 'COSC'");
    }

    @Test
    public void testBuildSqlQueryForDepartmentCRN1234() throws Exception {
        paramMap.put("department_crn", "1234");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.department_crn = '1234'");
    }

    @Test
    public void testBuildSqlQueryForLocationUH() throws Exception {
        paramMap.put("location", "UH");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.location = 'UH'");
    }

    @Test
    public void testBuildSqlQueryForComponentLAB() throws Exception {
        paramMap.put("component", "lab");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.component = 'lab'");
    }

    @Test
    public void testBuildSqlQueryForComponentLEC() throws Exception {
        paramMap.put("component", "lec");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.component = 'lec'");
    }

    @Test
    public void testBuildSqlQueryForBuildingSEC() throws Exception {
        paramMap.put("building", "SEC");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND building_abbreviation = 'SEC'");
    }

    @Test
    public void testBuildSqlQueryForBuildingAH() throws Exception {
        paramMap.put("building", "AH");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND building_abbreviation = 'AH'");
    }

    @Test
    public void testBuildSqlQueryForOneCreditHour() throws Exception {
        paramMap.put("credit_hours", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND credit_hours = '1'");
    }

    @Test
    public void testBuildSqlQueryForTwoCreditHours() throws Exception {
        paramMap.put("credit_hours", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND credit_hours = '2'");
    }

    @Test
    public void testBuildSqlQueryForEnglishCore() throws Exception {
        paramMap.put("core", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND (core = 1 OR core like '1,%' OR core like '%,1')");
    }

    @Test
    public void testBuildSqlQueryForMathCore() throws Exception {
        paramMap.put("core", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND (core = 2 OR core like '2,%' OR core like '%,2')");
    }

    @Test
    public void testBuildSqlQueryForOnlineTrueAndSession1() throws Exception {
        paramMap.put("online", "true");
        paramMap.put("session", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.session = 'Regular Academic Session' AND class.format = 'ONLINE'");
    }

    @Test
    public void testBuildSqlQueryForOpenCoscClassCore2() throws Exception {
        paramMap.put("status", "open");
        paramMap.put("department", "cosc");
        paramMap.put("core", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND (core = 2 OR core like '2,%' OR core like '%,2') AND class.department = 'cosc' AND class.status = 'Open'");
    }

    @Test
    public void testBuildSqlQueryForLocationABCComponentLecHybrid2HourCourse() throws Exception {
        paramMap.put("location", "ABC");
        paramMap.put("component", "lec");
        paramMap.put("hybrid", "true");
        paramMap.put("credit_hours", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + " AND class.component = 'lec' AND class.format = 'HYBRID' AND class.location = 'ABC' AND credit_hours = '2'");
    }
}