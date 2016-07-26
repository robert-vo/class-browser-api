package com.scraper.main.util;

public class IntegerClassUtility {
    public static int getClassHours(String classTitle) {
        try {
            char classHours = classTitle.charAt(1);
            return classHours >= '0' && classHours <= '9' ? Character.getNumericValue(classHours) : 0;
        }
        catch (StringIndexOutOfBoundsException | NullPointerException e) {
            return 0;
        }
    }
}
