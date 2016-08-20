# class-browser-api [![Build Status](https://travis-ci.org/robert-vo/class-browser-api.svg?branch=master)](https://travis-ci.org/robert-vo/class-browser-api)

The Class Browser API provides access to class information for classes offered at the University of Houston. 

The API is a RESTful API built using the Spring MVC Framework. All endpoints in this API returns JSON content.

The project includes a cron task which scrapes and updates the classes, at midnight, central US time. The scraper utilizes the [<code>Class-Scraper</code>](https://github.com/robert-vo/Class-Scraper) project, another project of mines. 

## URL Scheme
```
/api/core={coreID}
```

```
/api/class/term={termID}?{parameter1}={paramValue1}&...&{parameterN}={paramValueN}
```

Parameters include:
  * Online
  * Hybrid
  * FaceToFace
  * Status
  * Session
  * Department
  * Department_CRN
  * Location
  * Component
  * Building
  * Credit_Hours
  * Core
  * Monday
  * Tuesday
  * Wednesday
  * Thursday
  * Friday
  * Saturday
  * Sunday

Parameter Values include: 
  * True Values
    * 1, true, yes, open (status only)
  * False Values
    * 0, false, no, closed (status only)
  * Session Values
    * MIN, 1, 2, 3, 4, 5, 6
  * Core Values
    * An Integer between 1 and 10, inclusive.
  * Credit_Hours Values
    * An Integer between 1 and 5, inclusive.
  * Component Values
    * 'LEC', 'PRA', 'SEM', 'IND', 'LAB', 'PLS', 'PCO', 'THE', 'TUT', 'PRC', 'DST', 'CLN'
