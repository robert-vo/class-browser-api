package com.scraper.main.util;

import com.scraper.main.Class;

import java.util.Optional;
import java.util.function.Predicate;

public class PredicateClassUtility {

    /**
     * 1990 = summer 2016
     * 2000 = fall 2016
     * 2010 = spring 2017
     * etc
     * @param term
     * @return
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
        return e -> !status.isPresent() || (status.get() == Boolean.TRUE ? e.getClassStatus().toString().equals("OPEN") : e.getClassStatus().toString().equals("CLOSED"));
    }

    public static Predicate<Class> getPredicateToFilterByInstructorName(Optional<String> instructorName) {
        return aClass -> !instructorName.isPresent() || aClass.getInstructorName().equals(instructorName.get());
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
     * Options include
     * LAB
     * LEC
     */
    public static Predicate<Class> getPredicateToFilterByComponent(Optional<String> component) {
        return e -> !component.isPresent() || e.getComponent().toUpperCase().equals(component.get().toUpperCase());
    }

    /**
     * Options include
     * 1, 0, yes, no, true, false
     * @param syllabus
     * @return
     */
    public static Predicate<Class> getPredicateToFilterBySyllabus(Optional<Boolean> syllabus) {
        return e -> !syllabus.isPresent() ||
                (syllabus.get() == Boolean.TRUE ?
                        !e.getSyllabus().equals("Unavailable") :
                        syllabus.get() == Boolean.FALSE && e.getSyllabus().equals("Unavailable"));
    }
    
}
