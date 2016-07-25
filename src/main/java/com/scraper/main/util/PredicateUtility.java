package com.scraper.main.util;

import java.util.function.Predicate;
/**
 * Credits to @davidillsley on StackOverflow
 * http://stackoverflow.com/questions/21488056/how-to-negate-a-method-reference-predicate
 *
 * PredicateUtility is used to supplement the Predicate class.
 */
public class PredicateUtility {

    /**
     * @param t The predicate to be negated.
     * @return The predicate that represents the logical negation of this predicate.
     */
    public static <T> Predicate<T> not(Predicate<T> t) {
        return t.negate();
    }
}
