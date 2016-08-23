package com.classbrowser.main.commons.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StringSQLQueryUtilityTest {
    final static String SQL_QUERY_FOR_ALL_TERMS     = "SELECT * FROM class, building, department, terms, class_information " +
            "WHERE class.TERM_ID = ? AND " +
            "class.department = class_information.department AND " +
            "building.building_abbreviation = class.building_abbv AND " +
            "department.department_abbreviation = class.department AND " +
            "terms.term_id = class.term_id AND " +
            "class.department_crn = class_information.department_crn";
    final static String SQL_QUERY_FOR_ALL_INFORMATION = "SELECT * FROM CLASS_INFORMATION";
    Map<String, String> paramMap;

    @Before
    public void setUp() throws Exception {
        paramMap = new HashMap<>();
    }

    @Test
    public void testBuildSqlQueryForEmptyMap() throws Exception {
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS);
    }

    @Test
    public void testBuildSqlQueryForOnlineFilterTrue() throws Exception {
        paramMap.put("online", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.format = 'ONLINE'");
    }

    @Test
    public void testBuildSqlQueryForOnlineFilterFalse() throws Exception {
        paramMap.put("online", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.format <> 'ONLINE'");
    }

    @Test
    public void testBuildSqlQueryForHybridFilterTrue() throws Exception {
        paramMap.put("HyBriD", "yes");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.format = 'HYBRID'");
    }

    @Test
    public void testBuildSqlQueryForHybridFilterFalse() throws Exception {
        paramMap.put("HYBRID", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.format <> 'HYBRID'");
    }

    @Test
    public void testBuildSqlQueryForFaceToFaceFilterTrue() throws Exception {
        paramMap.put("facetoface", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.format = 'Face To Face'");
    }

    @Test
    public void testBuildSqlQueryForFaceToFaceFilterFalse() throws Exception {
        paramMap.put("FaceTOFACE", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.format <> 'Face To Face'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterOpen1() throws Exception {
        paramMap.put("status", "OPEN");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.status = 'Open'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterOpen2() throws Exception {
        paramMap.put("status", "true");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.status = 'Open'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterClosed1() throws Exception {
        paramMap.put("status", "closed");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.status <> 'Open'");
    }

    @Test
    public void testBuildSqlQueryForStatusFilterClosed2() throws Exception {
        paramMap.put("status", "0");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.status <> 'Open'");
    }

    @Test
    public void testBuildSqlQueryForSession1() throws Exception {
        paramMap.put("session", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.session = 'Regular Academic Session'");
    }

    @Test
    public void testBuildSqlQueryForSession2() throws Exception {
        paramMap.put("session", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.session = '2'");
    }

    @Test
    public void testBuildSqlQueryForSession3() throws Exception {
        paramMap.put("session", "3");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.session = '3'");
    }

    @Test
    public void testBuildSqlQueryForSession4() throws Exception {
        paramMap.put("session", "4");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.session = '4'");
    }

    @Test
    public void testBuildSqlQueryForSession5() throws Exception {
        paramMap.put("session", "5");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.session = '5'");
    }

    @Test
    public void testBuildSqlQueryForSession6() throws Exception {
        paramMap.put("session", "6");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.session = '6'");
    }

    @Test
    public void testBuildSqlQueryForSessionMIN() throws Exception {
        paramMap.put("session", "MIN");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.session = 'MIN'");
    }

    @Test
    public void testBuildSqlQueryForDepartmentCOSC() throws Exception {
        paramMap.put("department", "COSC");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS_INFORMATION.department = 'COSC'");
    }

    @Test
    public void testBuildSqlQueryForDepartmentCRN1234() throws Exception {
        paramMap.put("department_crn", "1234");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS_INFORMATION.department_crn = '1234'");
    }

    @Test
    public void testBuildSqlQueryForLocationUH() throws Exception {
        paramMap.put("location", "UH");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.location = 'UH'");
    }

    @Test
    public void testBuildSqlQueryForComponentLAB() throws Exception {
        paramMap.put("component", "lab");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.component = 'lab'");
    }

    @Test
    public void testBuildSqlQueryForComponentLEC() throws Exception {
        paramMap.put("component", "lec");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.component = 'lec'");
    }

    @Test
    public void testBuildSqlQueryForBuildingSEC() throws Exception {
        paramMap.put("building", "SEC");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND building_abbreviation = 'SEC'");
    }

    @Test
    public void testBuildSqlQueryForBuildingAH() throws Exception {
        paramMap.put("building", "AH");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND building_abbreviation = 'AH'");
    }

    @Test
    public void testBuildSqlQueryForOneCreditHour() throws Exception {
        paramMap.put("credit_hours", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND credit_hours = '1'");
    }

    @Test
    public void testBuildSqlQueryForTwoCreditHours() throws Exception {
        paramMap.put("credit_hours", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND credit_hours = '2'");
    }

    @Test
    public void testBuildSqlQueryForEnglishCore() throws Exception {
        paramMap.put("core", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND (core = 1 OR core like '1,%' OR core like '%,1')");
    }

    @Test
    public void testBuildSqlQueryForMathCore() throws Exception {
        paramMap.put("core", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND (core = 2 OR core like '2,%' OR core like '%,2')");
    }

    @Test
    public void testBuildSqlQueryForOnlineTrueAndSession1() throws Exception {
        paramMap.put("online", "true");
        paramMap.put("session", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.session = 'Regular Academic Session' AND CLASS.format = 'ONLINE'");
    }

    @Test
    public void testBuildSqlQueryForOpenCoscClassCore2() throws Exception {
        paramMap.put("status", "open");
        paramMap.put("department", "cosc");
        paramMap.put("core", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND (core = 2 OR core like '2,%' OR core like '%,2') AND CLASS_INFORMATION.department = 'cosc' AND CLASS.status = 'Open'");
    }

    @Test
    public void testBuildSqlQueryForLocationABCComponentLecHybrid2HourCourse() throws Exception {
        paramMap.put("location", "ABC");
        paramMap.put("component", "lec");
        paramMap.put("hybrid", "true");
        paramMap.put("credit_hours", "2");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.component = 'lec' AND CLASS.format = 'HYBRID' AND CLASS.location = 'ABC' AND credit_hours = '2'");
    }

    @Test
    public void testBuildSqlQueryForMondayTrue() throws Exception {
        paramMap.put("monday", "true");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Monday = '1'");
    }

    @Test
    public void testBuildSqlQueryForTuesdayTrue() throws Exception {
        paramMap.put("Tuesday", "true");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Tuesday = '1'");
    }

    @Test
    public void testBuildSqlQueryForWednesdayTrue() throws Exception {
        paramMap.put("Wednesday", "true");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Wednesday = '1'");
    }

    @Test
    public void testBuildSqlQueryForThursdayTrue() throws Exception {
        paramMap.put("Thursday", "true");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Thursday = '1'");
    }

    @Test
    public void testBuildSqlQueryForFridayTrue() throws Exception {
        paramMap.put("Friday", "truE");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Friday = '1'");
    }

    @Test
    public void testBuildSqlQueryForSaturdayTrue() throws Exception {
        paramMap.put("Saturday", "trUe");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Saturday = '1'");
    }

    @Test
    public void testBuildSqlQueryForSundayTrue() throws Exception {
        paramMap.put("Sunday", "1");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Sunday = '1'");
    }

    @Test
    public void testBuildSqlQueryForMondayFalse() throws Exception {
        paramMap.put("mondAY", "fAlSe");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Monday <> '1'");
    }

    @Test
    public void testBuildSqlQueryForTuesdayFalse() throws Exception {
        paramMap.put("TuesdAy", "fAlSe");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Tuesday <> '1'");
    }

    @Test
    public void testBuildSqlQueryForWednesdayFalse() throws Exception {
        paramMap.put("WednesdAy", "fAlSe");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Wednesday <> '1'");
    }

    @Test
    public void testBuildSqlQueryForThursdayFalse() throws Exception {
        paramMap.put("ThursdAy", "fAlSe");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Thursday <> '1'");
    }

    @Test
    public void testBuildSqlQueryForFridayFalse() throws Exception {
        paramMap.put("FridAy", "fAlse");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Friday <> '1'");
    }

    @Test
    public void testBuildSqlQueryForSaturdayFalse() throws Exception {
        paramMap.put("SaturdAy", "fAlSe");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Saturday <> '1'");
    }

    @Test
    public void testBuildSqlQueryForSundayFalse() throws Exception {
        paramMap.put("Sunday", "no");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Sunday <> '1'");
    }

    @Test
    public void testBuildSqlQueryForIsCoreYes() throws Exception {
        paramMap.put("isCore", "yes");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.attributes like '%core%'");
    }

    @Test
    public void testBuildSqlQueryForIsCoreNo() throws Exception {
        paramMap.put("isCore", "no");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.attributes not like '%core%'");
    }

    @Test
    public void testBuildSqlQueryForSundayYesIsCoreNo() throws Exception {
        paramMap.put("sunday", "yes");
        paramMap.put("isCore", "no");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.Sunday = '1' AND CLASS.attributes not like '%core%'");
    }

    @Test
    public void testBuildSqlQueryForWeekendUYes() throws Exception {
        paramMap.put("weekendu", "yes");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.attributes like '%Weekend U%'");
    }

    @Test
    public void testBuildSqlQueryForWeekendUFalse() throws Exception {
        paramMap.put("weekendu", "false");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.attributes not like '%Weekend U%'");
    }

    @Test
    public void testBuildSqlQueryForAllInformation() throws Exception {
        assertEquals(StringSQLQueryUtility.buildSqlQueryForInformation(paramMap), SQL_QUERY_FOR_ALL_INFORMATION);
    }

    @Test
    public void testBuildSqlQueryForInformationDepartmentAAS() throws Exception {
        paramMap.put("department", "aas");
        assertEquals(StringSQLQueryUtility.buildSqlQueryForInformation(paramMap), SQL_QUERY_FOR_ALL_INFORMATION + " WHERE  CLASS_INFORMATION.department = 'aas'");
    }

    @Test
    public void testBuildSqlQueryForUnavailableSyllabus() throws Exception {
        paramMap.put("syllabus", "no");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.SYLLABUS = 'Unavailable'");
    }

    @Test
    public void testBuildSqlQueryForAvailableSyllabus() throws Exception {
        paramMap.put("syllabus", "yes");
        assertEquals(StringSQLQueryUtility.buildSqlQuery(paramMap, SQL_QUERY_FOR_ALL_TERMS), SQL_QUERY_FOR_ALL_TERMS + " AND CLASS.SYLLABUS <> 'Unavailable'");
    }

}