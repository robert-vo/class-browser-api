package com.scraper.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ClassScraperAPIController {

    private ClassScraper classScraper;
    private List<Class> allClasses = new LinkedList<>();

    //departmentAbbreviation = subject
    //isMondayClass = monday
    //isTuesdayClass = tuesday
    //isWednesdayClass = wednesday
    //isThursdayClass = thursday
    //isFridayClass = friday
    //isSaturdayClass = saturday
    //isSundayClass = sunday
    private List<String> validParams = Arrays.asList("term",
            "classTitle", "subject", "departmentCourseNumber",
            "classStatus", "courseNumber", "seatsTaken", "seatsAvailable",
            "seatsTotal", "classStartDate", "classEndDate", "attributes",
            "classStartTime", "classEndTime",
            "monday",
            "tuesdas",
            "wednesday",
            "thursday",
            "friday",
            "saturday",
            "sunday", "instructorName", "instructorEmail", "location",
            "buildingAbbreviation", "buildingRoomNumber", "format", "description",
            "duration", "session", "component", "syllabus");


    ClassScraperAPIController() {
        loadAllClassesFromScraping();
    }

    private void loadAllClassesFromScraping() {
        classScraper = new ClassScraper(2016, "Fall");
        classScraper.setPageLimit(10);
        classScraper.startScraper();
        allClasses.addAll(classScraper.getAllClasses());

//        classScraper = new ClassScraper(2016, "Summer");
//        classScraper.setPageLimit(10);
//        classScraper.startScraper();
//        allClasses.addAll(classScraper.getAllClasses());
    }

    @RequestMapping(value = "/api/classes", method = RequestMethod.GET)
    public List<Class> getAllClasses(@RequestParam(value = "term", required = true) String term,
                                     @RequestParam Map<String,String> requestParams) throws Exception {

        if(areAllParamsValid(requestParams)) {

            Stream<Class> stream = allClasses.stream();

            stream = filterByTerm(stream, term);

            for (String param : requestParams.keySet()) {
                stream = filterStreamWithParam(stream, param, requestParams.get(param));
            }

            return new ArrayList<>(stream.collect(Collectors.toList()));
        }
        else {
            return null;
        }
    }

    private Stream<Class> filterStreamWithParam(Stream<Class> stream, final String param, final String paramValue) throws Exception {
        switch (param) {
            case "session":
                if (paramValue.equals("1")) {
                    String newParamValue = "Regular Academic Session";
                    return stream.filter(e -> e.getSession().equals(newParamValue));
                }
                return stream.filter(e -> e.getSession().equals(paramValue));
            case "subject":
                return stream.filter(e -> e.getDepartmentAbbreviation().equals(paramValue));
            case "seatsTaken":
                return stream.filter(e -> e.getSeatsTaken() < Integer.parseInt(paramValue));
            case "seatsAvailable":
                return stream.filter(e -> e.getSeatsAvailable() < Integer.parseInt(paramValue));
            case "seatsTotal":
                return stream.filter(e -> e.getSeatsTotal() < Integer.parseInt(paramValue));
            case "departmentCourseNumber":
                return stream.filter(e -> e.getDepartmentCourseNumber().equals(paramValue));
            case "attributes":
                return stream.filter(e -> e.getAttributes().contains(paramValue));
            case "location":
                return stream.filter(e -> e.getLocation().contains(paramValue));
            case "classStatus":
                return stream.filter(e -> e.getClassStatus().name().equals(paramValue));
            case "instructorName":
                return stream.filter(e -> e.getInstructorName().equals(paramValue));
            case "instructorEmail":
                return stream.filter(e -> e.getInstructorEmail().equals(paramValue));
            case "buildingAbbreviation":
                return stream.filter(e -> e.getBuildingAbbreviation().equals(paramValue));
            case "buildingRoomNumber":
                return stream.filter(e -> e.getBuildingRoomNumber().equals(paramValue));
            case "format":
                return stream.filter(e -> e.getFormat().equals(paramValue));
            case "duration":
                return stream.filter(e -> e.getDuration().contains(paramValue));
            case "component":
                return stream.filter(e -> e.getComponent().equals(paramValue));
            case "syllabus":
                if(paramValue.toUpperCase().equals("UNAVAILABLE")) {
                    return stream.filter(e -> e.getSyllabus().equals("Unavailable"));
                }
                else if(paramValue.toUpperCase().equals("1") || paramValue.toUpperCase().equals("AVAILABLE")){
                    return stream.filter(e -> !e.getSyllabus().equals("Unavailable"));
                }
                else {
                    System.out.println("Invalid syllabus parameter");
                }
            case "monday":
                if(paramValue.equals("false")) {
                    return stream.filter(e -> !e.isMondayClass());
                }
                else if(paramValue.equals("true")) {
                    return stream.filter(e -> e.isMondayClass());
                }
                else {
                    System.out.println("Invalid day parameter");
                }
            case "tuesday":
                if(paramValue.equals("false")) {
                    return stream.filter(e -> !e.isTuesdayClass());
                }
                else if(paramValue.equals("true")) {
                    return stream.filter(e -> e.isTuesdayClass());
                }
                else {
                    System.out.println("Invalid day parameter");
                }
            case "wednesday":
                if(paramValue.equals("false")) {
                    return stream.filter(e -> !e.isWednesdayClass());
                }
                else if(paramValue.equals("true")) {
                    return stream.filter(e -> e.isWednesdayClass());
                }
                else {
                    System.out.println("Invalid day parameter");
                }
            case "thursday":
                if(paramValue.equals("false")) {
                    return stream.filter(e -> !e.isThursdayClass());
                }
                else if(paramValue.equals("true")) {
                    return stream.filter(e -> e.isThursdayClass());
                }
                else {
                    System.out.println("Invalid day parameter");
                }
            case "friday":
                if(paramValue.equals("false")) {
                    return stream.filter(e -> !e.isFridayClass());
                }
                else if(paramValue.equals("true")) {
                    return stream.filter(e -> e.isFridayClass());
                }
                else {
                    System.out.println("Invalid day parameter");
                }

            case "saturday":
                if(paramValue.equals("false")) {
                    return stream.filter(e -> !e.isSaturdayClass());
                }
                else if(paramValue.equals("true")) {
                    return stream.filter(e -> e.isSaturdayClass());
                }
                else {
                    System.out.println("Invalid day parameter");
                }

            case "sunday":
                return filterBySunday(stream, paramValue);
            default:
                return stream;
        }
    }

    private Stream<Class> filterByTerm(Stream<Class> stream, String term) {
        return stream.filter(e -> e.getTerm().getTermID().equals(term));
    }

    private Stream<Class> filterBySunday(Stream<Class> stream, String exists) throws Exception {
        switch (exists) {
            case "false":
                return stream.filter(e -> !e.isSundayClass());
            case "true":
                return stream.filter(Class::isSundayClass);
            default:
                throw new Exception("Invalid parameter for sunday");
        }
    }

    private boolean areAllParamsValid(Map<String, String> params) {
        for (String param : params.keySet()) {
            if(!validParams.contains(param)) {
                System.out.println(param + " is invalid.");
                return false;
            }
        }
        return true;
    }

}
