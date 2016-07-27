package com.scraper.main.util;

import com.scraper.main.Class;

import java.util.Optional;
import java.util.function.Predicate;

public class PredicateClassUtility {

    /**
     * @param term, a String literal of the Term object. The String must be in increments of 10 starting from 1970.
     * 1970 = fall 2015
     * 1980 = spring 2016
     * 1990 = summer 2016
     * 2000 = fall 2016
     * 2010 = spring 2017
     * etc.
     * @return a predicate that will match the applied stream to specific terms.
     */
    public static Predicate<Class> getPredicateToFilterByTerm(String term) {
        return e -> e.getTerm().getTermID().equals(term);
    }

    public static Predicate<Class> getPredicateToFilterByTitle(Optional<String> title) {
        return e -> !title.isPresent() || e.getClassTitle().toUpperCase().equals(title.get().toUpperCase());
    }

    public static Predicate<Class> getPredicateToFilterBySubject(Optional<String> subject) {
        return e -> !subject.isPresent() || e.getDepartmentAbbreviation().toUpperCase().equals(subject.get().toUpperCase());
    }

    public static Predicate<Class> getPredicateToFilterByHours(Optional<Integer> hours) {
        return e -> !hours.isPresent() || IntegerClassUtility.getClassHours(e.getDepartmentCourseNumber()) == hours.get();
    }

    /**
     * Options include
     * Open/Closed
     * @param status
     * @return
     */
    public static Predicate<Class> getPredicateToFilterByStatus(Optional<Boolean> status) {
        return e -> !status.isPresent() || (status.get() == Boolean.TRUE ? e.getClassStatus().equals(Class.Status.Open) : e.getClassStatus().equals(Class.Status.Closed));
    }

    public static Predicate<Class> getPredicateToFilterByInstructorName(Optional<String> instructorName) {
        return aClass -> !instructorName.isPresent() || aClass.getInstructorName().toUpperCase().equals(instructorName.get().toUpperCase());
    }

    public static Predicate<Class> getPredicateToFilterByLocation(Optional<String> location) {
        return e -> !location.isPresent() || e.getLocation().equals(location.get());
    }

    public static Predicate<Class> getPredicateToFilterByAttribute(Optional<Boolean> doesExist, String attributeType) {
        return e ->
                !doesExist.isPresent() ||
                        (doesExist.get() == Boolean.TRUE ? e.getAttributes().toUpperCase().contains(attributeType) :
                                doesExist.get() != Boolean.FALSE || !e.getAttributes().toUpperCase().contains(attributeType));
    }

    public static Predicate<Class> getPredicateToFilterByBuilding(Optional<String> building) {
        return e -> !building.isPresent() || e.getBuildingAbbreviation().toUpperCase().equals(building.get().toUpperCase());
    }

    public static Predicate<Class> getPredicateToFilterByMonday(Optional<Boolean> isMonday) {
        return e -> !isMonday.isPresent() || e.isMondayClass();
    }

    public static Predicate<Class> getPredicateToFilterByTuesday(Optional<Boolean> isTuesday) {
        return e -> !isTuesday.isPresent() || e.isTuesdayClass();
    }

    public static Predicate<Class> getPredicateToFilterByWednesday(Optional<Boolean> isWednesday) {
        return e -> !isWednesday.isPresent() || e.isWednesdayClass();
    }

    public static Predicate<Class> getPredicateToFilterByThursday(Optional<Boolean> isThursday) {
        return e -> !isThursday.isPresent() || e.isThursdayClass();
    }

    public static Predicate<Class> getPredicateToFilterByFriday(Optional<Boolean> isFriday) {
        return e -> !isFriday.isPresent() || e.isFridayClass();
    }

    public static Predicate<Class> getPredicateToFilterBySaturday(Optional<Boolean> isSaturday) {
        return e -> !isSaturday.isPresent() || e.isSaturdayClass();
    }

    public static Predicate<Class> getPredicateToFilterBySunday(Optional<Boolean> isSunday) {
        return e -> !isSunday.isPresent() || e.isSundayClass();
    }

    /**
     * Options include
     * Face to Face
     * Online
     * Hybrid
     */
    public static Predicate<Class> getPredicateToFilterByFormat(Optional<String> format) {
        return e -> !format.isPresent() || e.getFormat().toUpperCase().equals(format.get().toUpperCase());
    }

    /**
     * Options include
     * xx weeks
     * numeric value (6, 15, etc)
     * @param duration
     * @return
     */
    public static Predicate<Class> getPredicateToFilterByDuration(Optional<String> duration) {
        return e -> !duration.isPresent() || e.getDuration().contains(duration.get());
    }

    /**
     * Options include
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * MIN
     */
    public static Predicate<Class> getPredicateToFilterBySession(Optional<String> session) {
        return e -> !session.isPresent() ||
                (session.get().equals("1") ?
                        e.getSession().equals("Regular Academic Session") :
                        e.getSession().toUpperCase().equals(session.get().toUpperCase()));
    }

    /**
     * @param component, a String literal that should be either 'Lab', or 'Lec', case in-sensitive.
     * @return a predicate that will match the applied stream to specific class components.
     */
    public static Predicate<Class> getPredicateToFilterByComponent(Optional<String> component) {
        return e -> !component.isPresent() || e.getComponent().toUpperCase().equals(component.get().toUpperCase());
    }

    /**
     * @param syllabus, a Boolean object specifying TRUE for an available syllabus or FALSE for an unavailable syllabus.
     * @return a predicate that will match the applied stream to an available or unavailable syllabus.
     */
    public static Predicate<Class> getPredicateToFilterBySyllabus(Optional<Boolean> syllabus) {
        return e -> !syllabus.isPresent() ||
                (syllabus.get() == Boolean.TRUE ?
                        !e.getSyllabus().equals("Unavailable") :
                        syllabus.get() == Boolean.FALSE && e.getSyllabus().equals("Unavailable"));
    }
    
}
