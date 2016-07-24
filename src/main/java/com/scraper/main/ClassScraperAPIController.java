package com.scraper.main;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClassScraperAPIController {

    private ClassScraper classScraper;
    private List<Class> allClasses = new ArrayList<>();

    ClassScraperAPIController() {
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
    public List<Class> getAllClasses(@RequestParam(value = "term", required = true) String term,
                                     @RequestParam(value = "subject", required = false) String subject,
                                     @RequestParam(value = "session", required = false) String session) {
        return allClasses.stream()
                .filter(e -> e.getDepartmentAbbreviation().equals(subject))
                .filter(e -> e.getSession().equals(session))
                .collect(Collectors.toList());
    }

}
