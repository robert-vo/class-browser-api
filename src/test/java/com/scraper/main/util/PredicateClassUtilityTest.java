package com.scraper.main.util;

import com.scraper.main.Class;
import com.scraper.main.Term;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.scraper.main.util.PredicateClassUtility.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Robert on 7/26/16.
 */
public class PredicateClassUtilityTest {

    Class classOne;
    Class classTwo;
    Class classNull;
    final String CORE = "CORE";
    final String ONLINE = "DISTANCE";

    @Before
    public void setUp() {
        classOne = new Class(Term.FALL_2016, "TitleA", "ONE_DEPT", "1111", Class.Status.Open, "Course", 0, 0, 0, null, null, "Core", null, null,
                false, false, false, false, false, false, false, "classonename", "Email", "UH", "PGH", "Room", "Hybrid", "Description", "15 weeks",
                "1", "LEC", "Syllabus");

        classTwo = new Class(Term.SUMMER_2016, "TitleB", "TWO_DEPT", "9999", Class.Status.Closed, "Course", 0, 0, 0, null, null, "Distance", null, null,
                true, true, true, true, true, true, true, "classtwoname", "Email", "UHV", "SEC", "Room", "Online", "Description", "6 weeks",
                "MIN", "LAB", "Unavailable");
        classNull = null;
    }

    @Test
    public void testGetPredicateToFilterByTermForValidTermFall2016() {
        assertTrue(getPredicateToFilterByTerm("2000").test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByTermForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByTerm("1990").test(classTwo));
    }

    @Test
    public void testGetPredicateFailsForIncorrectTerm() {
        assertFalse(getPredicateToFilterByTerm("1234").test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByTitleForValidTermFall2016() {
        assertTrue(getPredicateToFilterByTitle(Optional.of("titlea")).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByTitleForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByTitle(Optional.of("titleb")).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByTitleForNullTerm() {
        assertTrue(getPredicateToFilterByTitle(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterBySubjectForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySubject(Optional.of("one_dept")).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterBySubjectForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySubject(Optional.of("two_dept")).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterBySubjectForNullTerm() {
        assertTrue(getPredicateToFilterBySubject(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByHoursForValidTermFall2016() {
        assertTrue(getPredicateToFilterByHours(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByHoursForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByHours(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByHoursForNullTerm() {
        assertTrue(getPredicateToFilterByHours(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByStatusForValidTermFall2016() {
        assertTrue(getPredicateToFilterByStatus(Optional.of(Boolean.TRUE)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByStatusForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByStatus(Optional.of(Boolean.FALSE)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByStatusForNullTerm() {
        assertTrue(getPredicateToFilterByStatus(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByInstructorNameForValidTermFall2016() {
        assertTrue(getPredicateToFilterByInstructorName(Optional.of("ClaSsOnenAmE")).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByInstructorNameForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByInstructorName(Optional.of("cLaSstwOnAme")).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByInstructorNameForNullTerm() {
        assertTrue(getPredicateToFilterByInstructorName(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByLocationForValidTermFall2016() {
        assertTrue(getPredicateToFilterByLocation(Optional.of("UH")).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByLocationForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByLocation(Optional.of("UHV")).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByLocationForNullTerm() {
        assertTrue(getPredicateToFilterByLocation(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByCoreAttributeForValidTermFall2016() {
        assertTrue(getPredicateToFilterByAttribute(Optional.of(Boolean.TRUE), CORE).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByCoreAttributeForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByAttribute(Optional.of(Boolean.FALSE), CORE).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByCoreAttributeForNullTerm() {
        assertTrue(getPredicateToFilterByAttribute(Optional.ofNullable(null), CORE).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByOnlineAttributeForValidTermFall2016() {
        assertTrue(getPredicateToFilterByAttribute(Optional.of(Boolean.FALSE), ONLINE).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByOnlineAttributeForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByAttribute(Optional.of(Boolean.TRUE), ONLINE).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByOnlineAttributeForNullTerm() {
        assertTrue(getPredicateToFilterByAttribute(Optional.ofNullable(null), ONLINE).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByBuildingForValidTermFall2016() {
        assertTrue(getPredicateToFilterByBuilding(Optional.of("PGH")).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByBuildingForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByBuilding(Optional.of("SEC")).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByBuildingForNullTerm() {
        assertTrue(getPredicateToFilterByBuilding(Optional.ofNullable(null)).test(classNull));
    }

    /**
     *
     *
     *
     *
     *
     *
     *
     */

    @Test
    public void testGetPredicateToFilterByMondayForValidTermFall2016() {
        assertTrue(getPredicateToFilterByMonday(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByMondayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByMonday(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByMondayForNullTerm() {
        assertTrue(getPredicateToFilterByMonday(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByTuesdayForValidTermFall2016() {
        assertTrue(getPredicateToFilterByTuesday(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByTuesdayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByTuesday(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByTuesdayForNullTerm() {
        assertTrue(getPredicateToFilterByTuesday(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByWednesdayForValidTermFall2016() {
        assertTrue(getPredicateToFilterByWednesday(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByWednesdayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByWednesday(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByWednesdayForNullTerm() {
        assertTrue(getPredicateToFilterByWednesday(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByThursdayForValidTermFall2016() {
        assertTrue(getPredicateToFilterByThursday(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByThursdayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByThursday(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByThursdayForNullTerm() {
        assertTrue(getPredicateToFilterByThursday(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByFridayForValidTermFall2016() {
        assertTrue(getPredicateToFilterByFriday(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByFridayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByFriday(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByFridayForNullTerm() {
        assertTrue(getPredicateToFilterByFriday(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterBySaturdayForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySaturday(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterBySaturdayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySaturday(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterBySaturdayForNullTerm() {
        assertTrue(getPredicateToFilterBySaturday(Optional.ofNullable(null)).test(classNull));
    }


    @Test
    public void testGetPredicateToFilterBySundayForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySunday(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterBySundayForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySunday(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterBySundayForNullTerm() {
        assertTrue(getPredicateToFilterBySunday(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByFormatForValidTermFall2016() {
        assertTrue(getPredicateToFilterByFormat(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByFormatForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByFormat(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByFormatForNullTerm() {
        assertTrue(getPredicateToFilterByFormat(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByDurationForValidTermFall2016() {
        assertTrue(getPredicateToFilterByDuration(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByDurationForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByDuration(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByDurationForNullTerm() {
        assertTrue(getPredicateToFilterByDuration(Optional.ofNullable(null)).test(classNull));
    }
    @Test
    public void testGetPredicateToFilterBySessionForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySession(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterBySessionForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySession(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterBySessionForNullTerm() {
        assertTrue(getPredicateToFilterBySession(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterByComponentForValidTermFall2016() {
        assertTrue(getPredicateToFilterByComponent(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterByComponentForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByComponent(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterByComponentForNullTerm() {
        assertTrue(getPredicateToFilterByComponent(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilterBySyllabusForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySyllabus(Optional.of(1)).test(classOne));
    }

    @Test
    public void testGetPredicateToFilterBySyllabusForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySyllabus(Optional.of(9)).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilterBySyllabusForNullTerm() {
        assertTrue(getPredicateToFilterBySyllabus(Optional.ofNullable(null)).test(classNull));
    }

}