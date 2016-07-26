package com.scraper.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class ClassScraperAPIController {

    private ClassScraper classScraper;
    private List<Class> allClasses = new LinkedList<>();
    final private String CORE = "CORE";
    final private String ONLINE = "DISTANCE";
    ClassScraperAPIController() {
        loadAllClassesFromScraping();
    }

    private void loadAllClassesFromScraping() {
//        classScraper = new ClassScraper(2016, "Fall");
//        classScraper.setPageLimit(150);
//        classScraper.startScraper();
//        allClasses.addAll(classScraper.getAllClasses());

        classScraper = new ClassScraper(2016, "Summer");
//        classScraper.setPageLimit(1);
        classScraper.startScraper();
        allClasses.addAll(classScraper.getAllClasses());
    }

    @RequestMapping(value = "/api/classes", method = RequestMethod.GET)
    public List<Class> getAllClasses(@RequestParam(value = "term", required = true)             Optional<String>    term,
                                     @RequestParam(value = "title", required = false)           Optional<String>    title,
                                     @RequestParam(value = "subject", required = false)         Optional<String>    subject,
                                     @RequestParam(value = "instructorName", required = false)  Optional<String>    instructorName,
                                     @RequestParam(value = "location", required = false)        Optional<String>    location,
                                     @RequestParam(value = "building", required = false)        Optional<String>    building,
                                     @RequestParam(value = "format", required = false)          Optional<String>    format,
                                     @RequestParam(value = "duration", required = false)        Optional<String>    duration,
                                     @RequestParam(value = "session", required = false)         Optional<String>    session,
                                     @RequestParam(value = "component", required = false)       Optional<String>    component,
                                     @RequestParam(value = "hours", required = false)           Optional<Integer>   hours,
                                     @RequestParam(value = "status", required = false)          Optional<Boolean>   status,
                                     @RequestParam(value = "online", required = false)          Optional<Boolean>   online,
                                     @RequestParam(value = "core", required = false)            Optional<Boolean>   core,
                                     @RequestParam(value = "monday", required = false)          Optional<Boolean>   isMonday,
                                     @RequestParam(value = "tuesday", required = false)         Optional<Boolean>   isTuesday,
                                     @RequestParam(value = "wednesday", required = false)       Optional<Boolean>   isWednesday,
                                     @RequestParam(value = "thursday", required = false)        Optional<Boolean>   isThursday,
                                     @RequestParam(value = "friday", required = false)          Optional<Boolean>   isFriday,
                                     @RequestParam(value = "saturday", required = false)        Optional<Boolean>   isSaturday,
                                     @RequestParam(value = "sunday", required = false)          Optional<Boolean>   isSunday,
                                     @RequestParam(value = "syllabus", required = false)        Optional<Boolean>   syllabus) {

        return allClasses
                .stream()
                .filter(getPredicateToFilterByTerm(term))
                .filter(getPredicateToFilterByTitle(title))
                .filter(getPredicateToFilterBySubject(subject))
                .filter(getPredicateToFilterByHours(hours))
                .filter(getPredicateToFilterByStatus(status))
                .filter(getPredicateToFilterByAttribute(core, CORE))
                .filter(getPredicateToFilterByAttribute(online, ONLINE))
                .filter(getPredicateToFilterByMonday(isMonday))
                .filter(getPredicateToFilterByTuesday(isTuesday))
                .filter(getPredicateToFilterByWednesday(isWednesday))
                .filter(getPredicateToFilterByThursday(isThursday))
                .filter(getPredicateToFilterByFriday(isFriday))
                .filter(getPredicateToFilterBySaturday(isSaturday))
                .filter(getPredicateToFilterBySunday(isSunday))
                .filter(getPredicateToFilterByInstructorName(instructorName))
                .filter(getPredicateToFilterByLocation(location))
                .filter(getPredicateToFilterByBuilding(building))
                .filter(getPredicateToFilterByFormat(format))
                .filter(getPredicateToFilterByDuration(duration))
                .filter(getPredicateToFilterBySession(session))
                .filter(getPredicateToFilterByComponent(component))
                .filter(getPredicateToFilterBySyllabus(syllabus))
                .collect(Collectors.toList());
    }

    private int getClassHours(String classTitle) {
        return Character.getNumericValue(classTitle.charAt(1));
    }

    private Predicate<Class> getPredicateToFilterByTerm(Optional<String> term) {
        return e -> !term.isPresent() || e.getTerm().getTermID().equals(term.get());
    }

    private Predicate<Class> getPredicateToFilterByTitle(Optional<String> title) {
        return e -> !title.isPresent() || e.getClassTitle().toUpperCase().equals(title.get().toUpperCase());
    }

    private Predicate<Class> getPredicateToFilterBySubject(Optional<String> subject) {
        return e -> !subject.isPresent() || e.getDepartmentAbbreviation().toUpperCase().equals(subject.get().toUpperCase());
    }

    private Predicate<Class> getPredicateToFilterByHours(Optional<Integer> hours) {
        return e -> !hours.isPresent() || getClassHours(e.getDepartmentCourseNumber()) == hours.get();
    }

    private Predicate<Class> getPredicateToFilterByStatus(Optional<Boolean> status) {
        return e -> !status.isPresent() || (status.get() == Boolean.TRUE ? e.getClassStatus().toString().equals("OPEN") : e.getClassStatus().toString().equals("CLOSED"));
    }

    private Predicate<Class> getPredicateToFilterByInstructorName(Optional<String> instructorName) {
        return aClass -> !instructorName.isPresent() || aClass.getInstructorName().equals(instructorName.get());
    }

    private Predicate<Class> getPredicateToFilterByLocation(Optional<String> location) {
        return e -> !location.isPresent() || e.getLocation().equals(location.get());
    }

    private Predicate<Class> getPredicateToFilterByAttribute(Optional<Boolean> doesExist, String attributeType) {
        return e ->
                !doesExist.isPresent() ||
                        (doesExist.get() == Boolean.TRUE ? e.getAttributes().toUpperCase().contains(attributeType) :
                                doesExist.get() != Boolean.FALSE || !e.getAttributes().toUpperCase().contains(attributeType));
    }

    private Predicate<Class> getPredicateToFilterByBuilding(Optional<String> building) {
        return e -> !building.isPresent() || e.getBuildingAbbreviation().toUpperCase().equals(building.get().toUpperCase());
    }

    private Predicate<Class> getPredicateToFilterByMonday(Optional<Boolean> isMonday) {
        return e -> !isMonday.isPresent() || e.isMondayClass();
    }

    private Predicate<Class> getPredicateToFilterByTuesday(Optional<Boolean> isTuesday) {
        return e -> !isTuesday.isPresent() || e.isTuesdayClass();
    }

    private Predicate<Class> getPredicateToFilterByWednesday(Optional<Boolean> isWednesday) {
        return e -> !isWednesday.isPresent() || e.isWednesdayClass();
    }

    private Predicate<Class> getPredicateToFilterByThursday(Optional<Boolean> isThursday) {
        return e -> !isThursday.isPresent() || e.isThursdayClass();
    }

    private Predicate<Class> getPredicateToFilterByFriday(Optional<Boolean> isFriday) {
        return e -> !isFriday.isPresent() || e.isFridayClass();
    }

    private Predicate<Class> getPredicateToFilterBySaturday(Optional<Boolean> isSaturday) {
        return e -> !isSaturday.isPresent() || e.isSaturdayClass();
    }

    private Predicate<Class> getPredicateToFilterBySunday(Optional<Boolean> isSunday) {
        return e -> !isSunday.isPresent() || e.isSundayClass();
    }

    /**
     * Options include
     * Face to Face
     * Online
     * Hybrid
     */
    private Predicate<Class> getPredicateToFilterByFormat(Optional<String> format) {
        return e -> !format.isPresent() || e.getFormat().toUpperCase().equals(format.get().toUpperCase());
    }

    private Predicate<Class> getPredicateToFilterByDuration(Optional<String> duration) {
        return e -> !duration.isPresent() || e.getDuration().equals(duration.get());
    }

    private Predicate<Class> getPredicateToFilterBySession(Optional<String> session) {
        return e -> !session.isPresent() ||
                (session.get().equals("1") ?
                        e.getSession().equals("Regular Academic Session") :
                        e.getSession().equals(session.get()));
    }

    /**
     * Options include
     * LAB
     * LEC
     */
    private Predicate<Class> getPredicateToFilterByComponent(Optional<String> component) {
        return e -> !component.isPresent() || e.getComponent().toUpperCase().equals(component.get().toUpperCase());
    }

    private Predicate<Class> getPredicateToFilterBySyllabus(Optional<Boolean> syllabus) {
        return e -> !syllabus.isPresent() ||
                (syllabus.get() == Boolean.TRUE ?
                        !e.getSyllabus().equals("Unavailable") :
                        syllabus.get() == Boolean.FALSE && e.getSyllabus().equals("Unavailable"));
    }

}
