package com.scraper.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.scraper.main.util.PredicateClassUtility.*;

@RestController
public class ClassScraperAPIController {

    private ClassScraper classScraper;
    private List<Class> allClasses = new LinkedList<>();

    ClassScraperAPIController() {
        loadAllClassesFromScraping();
    }

    private void loadAllClassesFromScraping() {
//        classScraper = new ClassScraper(2016, "Fall");
//        classScraper.setPageLimit(150);
//        classScraper.startScraper();
//        allClasses.addAll(classScraper.getAllClasses());

        classScraper = new ClassScraper(2016, "Summer");
        classScraper.setPageLimit(5);
        classScraper.startScraper();
        allClasses.addAll(classScraper.getAllClasses());
    }

    @RequestMapping(value = "/api/classes", method = RequestMethod.GET)
    public List<Class> getAllClasses(@RequestParam(value = "term", required = true)             String              term,
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

        return new ArrayList<>(allClasses
                .parallelStream()
                .filter(getPredicateToFilterByTerm(term))
                .filter(getPredicateToFilterByTitle(title))
                .filter(getPredicateToFilterBySubject(subject))
                .filter(getPredicateToFilterByHours(hours))
                .filter(getPredicateToFilterByStatus(status))
                .filter(getPredicateToFilterByAttribute(core, "CORE"))
                .filter(getPredicateToFilterByAttribute(online, "ONLINE"))
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
                .collect(Collectors.toList()));
    }

}
