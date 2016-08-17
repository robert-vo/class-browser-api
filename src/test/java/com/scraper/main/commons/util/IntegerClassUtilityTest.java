package com.scraper.main.commons.util;

import com.scraper.main.util.IntegerClassUtility;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegerClassUtilityTest {

    @Test
    public void testGetClassHoursForValidCourse1300() {
        assertEquals(IntegerClassUtility.getClassHours("1300"), 3);
    }

    @Test
    public void testGetClassHoursForValidCourse1400() {
        assertEquals(IntegerClassUtility.getClassHours("1400"), 4);
    }

    @Test
    public void testGetClassHoursForValidCourse1200() {
        assertEquals(IntegerClassUtility.getClassHours("1200"), 2);
    }

    @Test
    public void testGetClassHoursForValidCourse1100() {
        assertEquals(IntegerClassUtility.getClassHours("1100"), 1);
    }

    @Test
    public void testGetClassHoursForInvalidCourseXXXX() {
        assertEquals(IntegerClassUtility.getClassHours("XXXX"), 0);
    }

    @Test
    public void testGetClassHoursForNullCourse() {
        assertEquals(IntegerClassUtility.getClassHours(null), 0);
    }

    @Test
    public void testGetClassHoursForEmptyCourse() {
        assertEquals(IntegerClassUtility.getClassHours(""), 0);
    }
}