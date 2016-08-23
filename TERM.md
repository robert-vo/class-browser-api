# Term

```
GET /api/class/term={termID}?{parameter1}={paramValue1}& ... &{parameterN}={paramValueN}
```
## Description
Returns an array of classes for a given term and specified optional parameters.

## Parameters

### Required Parameter
* term 

### Optional Parameters
* online
* hybrid
* facetoface
* status
* session
* department
* department_crn
* location
* component
* building
* credit_hours
* isCore
* weekendU
* core_id - An integer between 1 and 10, inclusive. The list below indicates what each number corresponds to.
  * 1: Communication
  * 2: Mathematics
  * 3: Life and Physical Sciences
  * 4: Language, Philosophy & Culture
  * 5: Creative Arts
  * 6: American History
  * 7: Government/Political Science
  * 8: Social & Behavorial Sciences
  * 9: Mathematics/Reasoning
  * 10: Writing in the Disciplines
* monday
* tuesday
* wednesday
* thursday
* friday
* saturday
* sunday
* syllabus

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


## Return Format
Returned is a JSON object with keys: 
* statusCode - An integer that indicates success (1) or failure (0).
* message - A string message indicating success or failure.
* numberOfRows - The number of elements in the result value.
* parameters - The parameter(s) passed through the request and their value(s).
* result - An object array holding the requested class information.
  * one
  * two
  * three
    * nest
    * nest
    
## Example

**Requested URL**

```GET /api/classes/term=2000?status=0&online=no&core=10&department=anth```

**JSON Response**
```
{
  "statusCode" : 1,
  "message" : "Successful",
  "numberOfRows" : 1,
  "parameters" : {
    "status" : "0",
    "online" : "no",
    "core" : "10",
    "department" : "anth",
    "Term" : "2000"
  },
  "result" : [ {
    "termInformation" : {
      "termID" : "2000",
      "year" : "2016",
      "semester" : "Fall"
    },
    "classTitle" : "Anthropology of Religion",
    "departmentInformation" : {
      "department" : "ANTH",
      "departmentName" : "Anthropology"
    },
    "classStatus" : "Closed",
    "courseNumber" : "24422",
    "seatInformation" : {
      "seatsTaken" : 49,
      "seatsAvailable" : 0,
      "seatsTotal" : 49
    },
    "dateTimeInformation" : {
      "startDate" : "2016-08-22",
      "endDate" : "2016-12-15",
      "startTime" : "14:30:00",
      "endTime" : "16:00:00"
    },
    "attributes" : "Core",
    "classDays" : {
      "monday" : false,
      "tuesday" : true,
      "wednesday" : false,
      "thursday" : true,
      "friday" : false,
      "saturday" : false,
      "sunday" : false
    },
    "instructorInformation" : {
      "instructor" : "Susan Rasmussen",
      "instructorEmail" : "srasmussen@uh.edu"
    },
    "locationInformation" : {
      "location" : "UH",
      "buildingID" : "534",
      "buildingAbbreviation" : "H",
      "buildingName" : "Fred J. Heyne"
    },
    "format" : "Face to Face",
    "description" : "Cr. 3. (3-0). Prerequisite: ANTH 1300, 2302 or consent of instructor. Cross-cultural survey of religious beliefs and practices.",
    "duration" : "15 weeks",
    "session" : "Regular Academic Session",
    "component" : "LEC",
    "syllabus" : "Unavailable",
    "core" : [ "10" ],
    "lastUpdated" : "2016-08-22 05:07:13.0"
  } ]
}
```
