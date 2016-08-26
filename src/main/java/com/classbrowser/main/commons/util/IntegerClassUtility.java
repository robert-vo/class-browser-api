package com.classbrowser.main.commons.util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * IntegerClassUtility includes a method that will help create retrieve
 * class hours from a String literal.
 *
 * @author Robert Vo
 */
@Component
public class IntegerClassUtility {

    private static Logger log = Logger.getLogger(IntegerClassUtility.class);

    /**
     * This method returns the number of credit hours for a class for a given class number.
     *
     * @param classNumber - The String literal version of the class number in the format of 1234.
     * @return an integer between 0 and 9, inclusive. If any errors occur, the method should return 0.
     */
    public static int getClassHours(String classNumber) {
        try {
            char classHours = classNumber.charAt(1);
            return classHours >= '0' && classHours <= '9' ? Character.getNumericValue(classHours) : 0;
        }
        catch (StringIndexOutOfBoundsException | NullPointerException e) {
            log.error("Invalid Class Number. Returning 0.");
            return 0;
        }
    }
}
