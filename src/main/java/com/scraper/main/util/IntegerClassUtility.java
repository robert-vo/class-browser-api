package com.scraper.main.util;

public class IntegerClassUtility {
    public static int getClassHours(String classTitle) {
        return Character.getNumericValue(classTitle.charAt(1));
    }
}
