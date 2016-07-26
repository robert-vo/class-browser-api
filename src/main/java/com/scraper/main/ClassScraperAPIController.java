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

    ClassScraperAPIController() {
        loadAllClassesFromScraping();
    }

    private void loadAllClassesFromScraping() {
        classScraper = new ClassScraper(2016, "Fall");
        classScraper.setPageLimit(10);
        classScraper.startScraper();
        allClasses.addAll(classScraper.getAllClasses());

        classScraper = new ClassScraper(2016, "Summer");
        classScraper.setPageLimit(10);
        classScraper.startScraper();
        allClasses.addAll(classScraper.getAllClasses());
    }

    @RequestMapping(value = "/api/classes", method = RequestMethod.GET)
    public List<Class> getAllClasses(@RequestParam(value = "term", required = true)               Optional<String>    term,
                                       @RequestParam(value = "title", required = false)           Optional<String>    title,
                                       @RequestParam(value = "subject", required = false)         Optional<String>    subject,
                                       @RequestParam(value = "status", required = false)          Optional<String>    status,
                                       @RequestParam(value = "instructorName", required = false)  Optional<String>    instructorName,
                                       @RequestParam(value = "location", required = false)        Optional<String>    location,
                                       @RequestParam(value = "building", required = false)        Optional<String>    building,
                                       @RequestParam(value = "format", required = false)          Optional<String>    format,
                                       @RequestParam(value = "duration", required = false)        Optional<String>    duration,
                                       @RequestParam(value = "session", required = false)         Optional<String>    session,
                                       @RequestParam(value = "component", required = false)       Optional<String>    component,
                                       @RequestParam(value = "hours", required = false)           Optional<Integer>   hours,
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
                .filter(filterByTerm(term))
                .filter(e -> !title.isPresent() || e.getClassTitle().equals(title.get()))
                .filter(e -> !subject.isPresent() || e.getDepartmentAbbreviation().equals(subject.get().toUpperCase()))
                .filter(e -> !hours.isPresent() || getClassHours(e.getDepartmentCourseNumber()) == hours.get())
                .filter(e -> !status.isPresent() || e.getClassStatus().toString().equals(status.get()))
                .filter(e -> !core.isPresent() || (core.get() == Boolean.TRUE ? e.getAttributes().toUpperCase().contains("CORE") : core.get() != Boolean.FALSE || !e.getAttributes().toUpperCase().contains("CORE")))
                .filter(e -> !online.isPresent() || (online.get() == Boolean.TRUE ? e.getAttributes().toUpperCase().contains("DISTANCE") : online.get() != Boolean.FALSE || !e.getAttributes().toUpperCase().contains("DISTANCE")))
                .filter(e -> !isMonday.isPresent() || e.isMondayClass())
                .filter(e -> !isTuesday.isPresent() || e.isTuesdayClass())
                .filter(e -> !isWednesday.isPresent() || e.isWednesdayClass())
                .filter(e -> !isThursday.isPresent() || e.isThursdayClass())
                .filter(e -> !isFriday.isPresent() || e.isFridayClass())
                .filter(e -> !isSaturday.isPresent() || e.isSaturdayClass())
                .filter(e -> !isSunday.isPresent() || e.isSundayClass())
                .filter(e -> !instructorName.isPresent() || e.getInstructorName().equals(instructorName.get()))
                .filter(e -> !location.isPresent() || e.getLocation().equals(location.get()))
                .filter(e -> !building.isPresent() || e.getBuildingAbbreviation().equals(building.get()))
                .filter(e -> !format.isPresent() || e.getFormat().equals(format.get()))
                .filter(e -> !duration.isPresent() || e.getDuration().equals(duration.get()))
                .filter(e -> !session.isPresent() || (session.get().equals("1") ? e.getSession().equals("Regular Academic Session") : e.getSession().equals(session.get())))
                .filter(e -> !component.isPresent() || e.getComponent().equals(component.get()))
                .filter(e -> !syllabus.isPresent() || (syllabus.get() == Boolean.TRUE ? !e.getSyllabus().equals("Unavailable") : syllabus.get() == Boolean.FALSE && e.getSyllabus().equals("Unavailable")))
                .collect(Collectors.toList());
    }

    private int getClassHours(String classTitle) {
        return Character.getNumericValue(classTitle.charAt(1));
    }

    private Predicate<Class> filterByTerm(Optional<String> term) {
        return aClass -> !term.isPresent() || aClass.getTerm().getTermID().equals(term.get());
    }

}
