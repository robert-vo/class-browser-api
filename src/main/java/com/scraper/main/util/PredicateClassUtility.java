package com.scraper.main.util;

import com.scraper.main.Class;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * PredicateClassUtility include methods that will help create predicates
 * to filter a stream for the ClassScraperAPIController.
 *
 * @author Robert Vo
 */
public class PredicateClassUtility {

    /**
     * @param term, a String literal that indicates the requested class title to be found.
     * The String must be in increments of 10 starting from 1970.
     * 1970 -> fall 2015
     * 1980 -> spring 2016
     * 1990 -> summer 2016
     * 2000 -> fall 2016
     * 2010 -> spring 2017
     * etc.
     * @return a predicate that will filter the applied stream to the class term, if {@param term} exists.
     * If {@param term} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByTerm(String term) {
        return e -> e.getTerm().getTermID().equalsIgnoreCase(term);
    }

    /**
     * @param title, a String literal that indicates the requested class title to be found.
     * @return a predicate that will filter the applied stream to the class title, if {@param title} exists.
     * If {@param title} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByTitle(Optional<String> title) {
        return e -> !title.isPresent() || e.getClassTitle().equalsIgnoreCase(title.get());
    }

    /**
     * @param subject, a String literal that indicates the requested class subject to be found.
     * @return a predicate that will filter the applied stream with the class subject, if {@param subject} exists.
     * If {@param subject} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterBySubject(Optional<String> subject) {
        return e -> !subject.isPresent() || e.getDepartmentAbbreviation().equalsIgnoreCase(subject.get());
    }

    /**
     * @param hours, an Integer that indicates the number of hours a course has.
     * @return a predicate that will filter the applied stream with the course hours, if the {@param hours} exists.
     * If {@param hours} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByHours(Optional<Integer> hours) {
        return e -> !hours.isPresent() || IntegerClassUtility.getClassHours(e.getDepartmentCourseNumber()) == hours.get();
    }

    /**
     * @param status, a Boolean object that indicates whether the requested status is open or closed.
     * Passing in TRUE indicates filtering for Open classes while passing in FALSE indicates filtering for Closed classes.
     * @return a predicate that will filter the applied stream with the course status, if the {@param status} exists.
     * If {@param status} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByStatus(Optional<Boolean> status) {
        return e -> !status.isPresent() || (status.get() == Boolean.TRUE ? e.getClassStatus().equals(Class.Status.Open) : e.getClassStatus().equals(Class.Status.Closed));
    }

    /**
     *
     * @param instructorName, a String literal that indicates the requested instructor to be found.
     * @return a predicate that will filter the applied stream with the course instructor's name, if the {@param instructorName} exists.
     * If {@param instructorName} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByInstructorName(Optional<String> instructorName) {
        return aClass -> !instructorName.isPresent() || aClass.getInstructorName().equalsIgnoreCase(instructorName.get());
    }

    /**
     * @param location, a String literal that indicates the requested location to be found.
     * Locations include: UH.
     * @return a predicate that will filter the applied stream with the course location, if the {@param location} exists.
     * If {@param location} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByLocation(Optional<String> location) {
        return e -> !location.isPresent() || e.getLocation().equalsIgnoreCase(location.get());
    }

    /**
     * @param doesExist, a Boolean object that indicates whether the {@param attributeType} exists or not.
     * @param attributeType, the attribute to be located.
     * Sample {@param attributeType} includes Core, Distance Education, Hybrid.
     * @return a predicate that will filter the applied stream with the course attribute types, if the {@param doesExist} exists.
     * If {@param doesExist} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByAttribute(Optional<Boolean> doesExist, String attributeType) {
        return e ->
                !doesExist.isPresent() ||
                        (doesExist.get() == Boolean.TRUE ? e.getAttributes().contains(attributeType) :
                                doesExist.get() != Boolean.FALSE || !e.getAttributes().contains(attributeType));
    }

    /**
     * @param building, a String literal that indicates the requested location to be found.
     * Buildings include: SEC, PGH, C.
     * @return a predicate that will filter the applied stream with the building the course is in, if the {@param building} exists.
     * If {@param building} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByBuilding(Optional<String> building) {
        return e -> !building.isPresent() || e.getBuildingAbbreviation().equalsIgnoreCase(building.get());
    }

    /**
     * @param isMonday, a Boolean object that indicates that the request class occurs on Monday or not.
     * @return a predicate that will filter the applied stream depending if {@param isMonday} is true or false.
     * If {@param isMonday} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByMonday(Optional<Boolean> isMonday) {
        return e -> !isMonday.isPresent() || e.isMondayClass();
    }

    /**
     * @param isTuesday, a Boolean object that indicates that the request class occurs on Monday or not.
     * @return a predicate that will filter the applied stream depending if {@param isTuesday} is true or false.
     * If {@param isTuesday} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByTuesday(Optional<Boolean> isTuesday) {
        return e -> !isTuesday.isPresent() || e.isTuesdayClass();
    }

    /**
     * @param isWednesday, a Boolean object that indicates that the request class occurs on Monday or not.
     * @return a predicate that will filter the applied stream depending if {@param isWednesday} is true or false.
     * If {@param isWednesday} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByWednesday(Optional<Boolean> isWednesday) {
        return e -> !isWednesday.isPresent() || e.isWednesdayClass();
    }

    /**
     * @param isThursday, a Boolean object that indicates that the request class occurs on Monday or not.
     * @return a predicate that will filter the applied stream depending if {@param isThursday} is true or false.
     * If {@param isThursday} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByThursday(Optional<Boolean> isThursday) {
        return e -> !isThursday.isPresent() || e.isThursdayClass();
    }

    /**
     * @param isFriday, a Boolean object that indicates that the request class occurs on Monday or not.
     * @return a predicate that will filter the applied stream depending if {@param isFriday} is true or false.
     * If {@param isFriday} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByFriday(Optional<Boolean> isFriday) {
        return e -> !isFriday.isPresent() || e.isFridayClass();
    }

    /**
     * @param isSaturday, a Boolean object that indicates that the request class occurs on Monday or not.
     * @return a predicate that will filter the applied stream depending if {@param isSaturday} is true or false.
     * If {@param isSaturday} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterBySaturday(Optional<Boolean> isSaturday) {
        return e -> !isSaturday.isPresent() || e.isSaturdayClass();
    }

    /**
     * @param isSunday, a Boolean object that indicates that the request class occurs on Monday or not.
     * @return a predicate that will filter the applied stream depending if {@param isSunday} is true or false.
     * If {@param isSunday} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterBySunday(Optional<Boolean> isSunday) {
        return e -> !isSunday.isPresent() || e.isSundayClass();
    }

    /**
     * @param format, a String literal that indicates the requested course format to be found.
     * Formats include: Face to Face, Online, Hybrid.
     * @return a predicate that will filter the applied stream with the course format, if the {@param format} exists.
     * If {@param format} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByFormat(Optional<String> format) {
        return e -> !format.isPresent() || e.getFormat().equalsIgnoreCase(format.get());
    }

    /**
     * @param duration, a String literal that indicates the requested class duration to be found.
     * Durations include: '6 weeks', '6', '15', '15 weeks'.
     * @return a predicate that will filter the applied stream with the class duration, if the {@param duration} exists.
     * If {@param duration} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterByDuration(Optional<String> duration) {
        return e -> !duration.isPresent() || e.getDuration().contains(duration.get());
    }

    /**
     * @param session, a String literal that indicates the requested class session to be found.
     * Sessions include: 1, 2, 3, 4, 5, 6, MIN.
     * Regular Academic Session corresponds with Session 1.
     * @return a predicate that will filter the applied stream with the course session, if the {@param session} exists.
     * If {@param session} does not exist, the original stream will not be altered.
     */
    public static Predicate<Class> getPredicateToFilterBySession(Optional<String> session) {
        return e -> !session.isPresent() ||
                (session.get().equalsIgnoreCase("1") ?
                        e.getSession().equalsIgnoreCase("Regular Academic Session") :
                        e.getSession().equalsIgnoreCase(session.get()));
    }

    /**
     * @param component, a String literal that should be either 'Lab', or 'Lec', case in-sensitive.
     * @return a predicate that will match the applied stream to specific class components.
     */
    public static Predicate<Class> getPredicateToFilterByComponent(Optional<String> component) {
        return e -> !component.isPresent() || e.getComponent().equalsIgnoreCase(component.get());
    }

    /**
     * @param syllabus, a Boolean object specifying TRUE for an available syllabus or FALSE for an unavailable syllabus.
     * @return a predicate that will match the applied stream to an available or unavailable syllabus.
     */
    public static Predicate<Class> getPredicateToFilterBySyllabus(Optional<Boolean> syllabus) {
        return e -> !syllabus.isPresent() ||
                (syllabus.get() == Boolean.TRUE ?
                        !e.getSyllabus().equalsIgnoreCase("Unavailable") :
                        syllabus.get() == Boolean.FALSE && e.getSyllabus().equalsIgnoreCase("Unavailable"));
    }

}
