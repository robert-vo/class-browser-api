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

    @Before
    public void setUp() {
        classOne = new Class(Term.FALL_2016, "TitleA", "ONE_DEPT", "1111", Class.Status.Open, "Course", 0, 0, 0, null, null, "Core", null, null,
                false, false, false, false, false, false, false, "Name", "Email", "Location", "PGH", "Room", "Hybrid", "Description", "15 weeks",
                "1", "LEC", "Syllabus");

        classTwo = new Class(Term.SUMMER_2016, "TitleB", "TWO_DEPT", "9999", Class.Status.Closed, "Course", 0, 0, 0, null, null, "Distance", null, null,
                true, true, true, true, true, true, true, "Name", "Email", "Location", "SEC", "Room", "Online", "Description", "6 weeks",
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
    public void testGetPredicateToFilerByTitleForValidTermFall2016() {
        assertTrue(getPredicateToFilterByTitle(Optional.of("titlea")).test(classOne));
    }

    @Test
    public void testGetPredicateToFilerByTitleForValidTermSummer2016() {
        assertTrue(getPredicateToFilterByTitle(Optional.of("titleb")).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilerByTitleForNullTerm() {
        assertTrue(getPredicateToFilterByTitle(Optional.ofNullable(null)).test(classNull));
    }

    @Test
    public void testGetPredicateToFilerBySubjectForValidTermFall2016() {
        assertTrue(getPredicateToFilterBySubject(Optional.of("one_dept")).test(classOne));
    }

    @Test
    public void testGetPredicateToFilerBySubjectForValidTermSummer2016() {
        assertTrue(getPredicateToFilterBySubject(Optional.of("two_dept")).test(classTwo));
    }

    @Test
    public void testGetPredicateToFilerBySubjectForNullTerm() {
        assertTrue(getPredicateToFilterBySubject(Optional.ofNullable(null)).test(classNull));
    }

}