package com.classbrowser.main.commons.util;

import com.scraper.main.pojo.Class;
import com.scraper.main.pojo.Status;
import com.scraper.main.pojo.Term;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.classbrowser.main.commons.util.PredicateClassUtility.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PredicateClassUtilityTest {

    Class classInformationOne;
    Class classInformationTwo;
    Class classInformationNull;
    final String CORE = "CORE";
    final String ONLINE = "DISTANCE";

    @Before
    public void setUp() {
        classInformationOne = new Class(Term.FALL_2016, "TitleA", "ONE_DEPT", "1111", Status.Open, "Course", 0, 0, 0, null, null, "Core", null, null,
                false, false, false, false, false, false, false, "classonename", "Email", "UH", "PGH", "Room", "Hybrid", "Description", "15 weeks",
                "Regular Academic Session", "LEC", "Syllabus");

        classInformationTwo = new Class(Term.SUMMER_2016, "TitleB", "TWO_DEPT", "9999", Status.Closed, "Course", 0, 0, 0, null, null, "Distance", null, null,
                true, true, true, true, true, true, true, "classtwoname", "Email", "UHV", "SEC", "Room", "Online", "Description", "6 weeks",
                "MIN", "LAB", "Unavailable");
        classInformationNull = null;
    }

    @Test
    public void testGetPredicateToFilterByTermForValidTermFall2016() {
        assertTrue(getPredicateToFilterByTerm("2000").test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByTermForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByTerm("1990").test(classInformationTwo));
    }

    @Test
    public void testGetPredicateFailsForIncorrectTerm() {
        assertFalse(getPredicateToFilterByTerm("1234").test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByTitleForValidTermFall2016() {
        assertTrue(getPredicateToFilterByTitle(Optional.of("titlea")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByTitleForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByTitle(Optional.of("titleb")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByTitleForNullTerm() {
        assertTrue(getPredicateToFilterByTitle(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterBySubjectForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySubject(Optional.of("one_dept")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterBySubjectForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySubject(Optional.of("two_dept")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterBySubjectForNullTerm() {
        assertTrue(getPredicateToFilterBySubject(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByHoursForValidTermFall2016() {
        assertTrue(getPredicateToFilterByHours(Optional.of(1)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByHoursForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByHours(Optional.of(9)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByHoursForNullTerm() {
        assertTrue(getPredicateToFilterByHours(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByStatusForValidTermFall2016() {
        assertTrue(getPredicateToFilterByStatus(Optional.of(Boolean.TRUE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByStatusForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByStatus(Optional.of(Boolean.FALSE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByStatusForNullTerm() {
        assertTrue(getPredicateToFilterByStatus(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByInstructorNameForValidTermFall2016() {
        assertTrue(getPredicateToFilterByInstructorName(Optional.of("ClaSsOnenAmE")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByInstructorNameForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByInstructorName(Optional.of("cLaSstwOnAme")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByInstructorNameForNullTerm() {
        assertTrue(getPredicateToFilterByInstructorName(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByLocationForValidTermFall2016() {
        assertTrue(getPredicateToFilterByLocation(Optional.of("UH")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByLocationForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByLocation(Optional.of("UHV")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByLocationForNullTerm() {
        assertTrue(getPredicateToFilterByLocation(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByCoreAttributeForValidTermFall2016() {
        assertTrue(getPredicateToFilterByAttribute(Optional.of(Boolean.TRUE), CORE).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByCoreAttributeForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByAttribute(Optional.of(Boolean.FALSE), CORE).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByCoreAttributeForNullTerm() {
        assertTrue(getPredicateToFilterByAttribute(Optional.empty(), CORE).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByOnlineAttributeForValidTermFall2016() {
        assertTrue(getPredicateToFilterByAttribute(Optional.of(Boolean.FALSE), ONLINE).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByOnlineAttributeForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByAttribute(Optional.of(Boolean.TRUE), ONLINE).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByOnlineAttributeForNullTerm() {
        assertTrue(getPredicateToFilterByAttribute(Optional.empty(), ONLINE).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByBuildingForValidTermFall2016() {
        assertTrue(getPredicateToFilterByBuilding(Optional.of("PGH")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByBuildingForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByBuilding(Optional.of("SEC")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByBuildingForNullTerm() {
        assertTrue(getPredicateToFilterByBuilding(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByMondayForValidTermFall2016() {
        assertFalse(getPredicateToFilterByMonday(Optional.of(Boolean.TRUE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByMondayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByMonday(Optional.of(Boolean.TRUE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByMondayForNullTerm() {
        assertTrue(getPredicateToFilterByMonday(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByTuesdayForValidTermFall2016() {
        assertFalse(getPredicateToFilterByTuesday(Optional.of(Boolean.TRUE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByTuesdayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByTuesday(Optional.of(Boolean.TRUE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByTuesdayForNullTerm() {
        assertTrue(getPredicateToFilterByTuesday(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByWednesdayForValidTermFall2016() {
        assertFalse(getPredicateToFilterByWednesday(Optional.of(Boolean.FALSE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByWednesdayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByWednesday(Optional.of(Boolean.TRUE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByWednesdayForNullTerm() {
        assertTrue(getPredicateToFilterByWednesday(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByThursdayForValidTermFall2016() {
        assertFalse(getPredicateToFilterByThursday(Optional.of(Boolean.TRUE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByThursdayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByThursday(Optional.of(Boolean.TRUE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByThursdayForNullTerm() {
        assertTrue(getPredicateToFilterByThursday(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByFridayForValidTermFall2016() {
        assertFalse(getPredicateToFilterByFriday(Optional.of(Boolean.TRUE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByFridayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByFriday(Optional.of(Boolean.TRUE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByFridayForNullTerm() {
        assertTrue(getPredicateToFilterByFriday(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterBySaturdayForValidTermFall2016() {
        assertFalse(getPredicateToFilterBySaturday(Optional.of(Boolean.TRUE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterBySaturdayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySaturday(Optional.of(Boolean.TRUE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterBySaturdayForNullTerm() {
        assertTrue(getPredicateToFilterBySaturday(Optional.empty()).test(classInformationNull));
    }


    @Test
    public void testGetPredicateToFilterBySundayForValidTermFall2016() {
        assertFalse(getPredicateToFilterBySunday(Optional.of(Boolean.TRUE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterBySundayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySunday(Optional.of(Boolean.TRUE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterBySundayForNullTerm() {
        assertTrue(getPredicateToFilterBySunday(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByFormatForValidTermFall2016() {
        assertTrue(getPredicateToFilterByFormat(Optional.of("Hybrid")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByFormatForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByFormat(Optional.of("Online")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByFormatForNullTerm() {
        assertTrue(getPredicateToFilterByFormat(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByDurationForValidTermFall2016() {
        assertTrue(getPredicateToFilterByDuration(Optional.of("15")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByDurationForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByDuration(Optional.of("6")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByDurationForNullTerm() {
        assertTrue(getPredicateToFilterByDuration(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterBySessionForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySession(Optional.of("1")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterBySessionForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySession(Optional.of("MIN")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterBySessionForNullTerm() {
        assertTrue(getPredicateToFilterBySession(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterByComponentForValidTermFall2016() {
        assertTrue(getPredicateToFilterByComponent(Optional.of("lEc")).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterByComponentForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByComponent(Optional.of("LaB")).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterByComponentForNullTerm() {
        assertTrue(getPredicateToFilterByComponent(Optional.empty()).test(classInformationNull));
    }

    @Test
    public void testGetPredicateToFilterBySyllabusForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySyllabus(Optional.of(Boolean.TRUE)).test(classInformationOne));
    }

    @Test
    public void testGetPredicateToFilterBySyllabusForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySyllabus(Optional.of(Boolean.FALSE)).test(classInformationTwo));
    }

    @Test
    public void testGetPredicateToFilterBySyllabusForNullTerm() {
        assertTrue(getPredicateToFilterBySyllabus(Optional.empty()).test(classInformationNull));
    }

}