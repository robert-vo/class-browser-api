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

    @Test
    public void testBuildSqlQueryForDepartmentCOSC() {
        paramMap.put("department", "COSC");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.department = 'COSC'");
    }

    @Test
    public void testBuildSqlQueryForDepartmentCRN1234() {
        paramMap.put("department_crn", "1234");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.department_crn = '1234'");
    }

    @Test
    public void testBuildSqlQueryForLocationUH() {
        paramMap.put("location", "UH");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.location = 'UH'");
    }

    @Test
    public void testBuildSqlQueryForComponentLAB() {
        paramMap.put("component", "lab");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.component = 'lab'");
    }

    @Test
    public void testBuildSqlQueryForComponentLEC() {
        paramMap.put("component", "lec");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND class.component = 'lec'");
    }

    @Test
    public void testBuildSqlQueryForBuildingSEC() {
        paramMap.put("building", "SEC");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND building_abbreviation = 'SEC'");
    }

    @Test
    public void testBuildSqlQueryForBuildingAH() {
        paramMap.put("building", "AH");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND building_abbreviation = 'AH'");
    }

    @Test
    public void testBuildSqlQueryForOneCreditHour() {
        paramMap.put("credit_hours", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND credit_hours = '1'");
    }

    @Test
    public void testBuildSqlQueryForTwoCreditHours() {
        paramMap.put("credit_hours", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND credit_hours = '2'");
    }

    @Test
    public void testBuildSqlQueryForEnglishCore() {
        paramMap.put("core", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND (core = 1 or core like '1,%' or core like '%,1')");
    }

    @Test
    public void testBuildSqlQueryForMathCore() {
        paramMap.put("core", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap), SQL_QUERY_FOR_ALL_TERMS + "AND (core = 2 or core like '2,%' or core like '%,2')");
    }

}