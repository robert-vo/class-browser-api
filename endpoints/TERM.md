# Term

```
GET /api/class/term={termID}
```
## Description
Returns an array of offered classes for a given term.

## Parameters

### Required Parameter
* term - A String that specifies the semester and year of the offered classes. The list below indicates the acceptable values for term.
  * "1970" returns classes for the Fall 2015 term.
  * "1980" returns classes for the Spring 2016 term.
  * "1990" returns classes for the Summer 2016 term.
  * "2000" returns classes for the Fall 2016 term.
  * "2010" returns classes for the Spring 2017 term.
  * "2020" returns classes for the Summer 2017 term.
  * "2030" returns classes for the Fall 2017 term.
  * "2040" returns classes for the Spring 2018 term.

### Optional Parameters
The optional parameters can be used to filter the results to your needs. True/False Values are given after this section.

* online - Use one of the True Values to indicate an online class, False Values for classes that are not online.
* hybrid - Use one of the True Values to indicate a hybrid class, False Values for classes that are not hybrid.
* facetoface - Use one of the True Values to indicate a face to face class, False Values for classes that are not face to face.
* status - Use one of the True Values, or "Open", to indicate an open class. False Values, or "Closed", indicates a closed class.
* session - Represents the session in which a class is under. Values include:
  * MIN - Mini Academic Session
  * 1 - Regular Academic Session
  * 2, 3, 4, 5, 6 - Sections of a Regular Academic Session
* department - A String that represents the department class(es) belong to.
* department_crn - The 4 digit course number for the class.
* location - Where the class takes place. Examples include:
  * "UH" - University of Houston
  * "UH-Northwest Campus" - University of Houston's Northwest Campus
  * "Rice University"
  * "UH-Sugar Land" - University of Houston's Sugar Land Campus
* component - A String that represents what component a class falls under. Example values include:
  * "IND" - Independent Studies
  * "LEC" - Lecture
  * "LAB" - Lab
  * "TUT" - Tutorial
* building - The building abbreviation where a class takes place. Example values include:
  * "SEC" - Science and Engineering Complex
  * "HBS" - Health and Biomedical Sciences
  * "AAA" - Agnes Arnold Auditorium
* credit_hours - An integer between 1 and 5, inclusive, to retrieve classes with a certain amount of hours fulfilled.
* isCore - Use one of the True Values to indicate if a class fulfills a core requirement. False Values to indicate otherwise.
* weekendU - Use one of the True Values to indicate if a class is a weekendU class. False Values to indicate otherwise.
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
* monday - Use one of the True Values to indicate that the class meets on Monday. Using a False Value indicates otherwise.
* tuesday - Use one of the True Values to indicate that the class meets on Tuesday. Using a False Value indicates otherwise.
* wednesday - Use one of the True Values to indicate that the class meets on Wednesday. Using a False Value indicates otherwise.
* thursday - Use one of the True Values to indicate that the class meets on Thursday. Using a False Value indicates otherwise.
* friday - Use one of the True Values to indicate that the class meets on Friday. Using a False Value indicates otherwise.
* saturday - Use one of the True Values to indicate that the class meets on Saturday. Using a False Value indicates otherwise.
* sunday - Use one of the True Values to indicate that the class meets on Sunday. Using a False Value indicates otherwise.
* syllabus - Use one of the True Values to indicate that the class has a syllabus. Using a False Value indicates otherwise.

#### Possible True/False Parameter Values
  * True Values (case insensitive)
    * 1, true, yes, open (status only)
  * False Values (case insensitive)
    * 0, false, no, closed (status only)

## Return Format
Returned is a JSON object with keys: 
* statusCode - An integer that indicates success (1), empty response (0), failure (-1).
* message - A String message indicating success or failure.
* numberOfRows - The number of elements in the result value.
* parameters - The parameter(s) passed through the request and their value(s).
* result - An object array holding the requested class information with the following structure.
  * termInformation - An object containing information about the term.
    * termID - A 4 digit number representing the year and semester.
    * year - The year which the class is offered.
    * semester - The semester which the semester is offered.
  * classTitle - The title of the class.
  * departmentInformation - An object containing information about the department of the class.
    * department - The department in which the class is in.
    * departmentName - The full name of the department in which the class is in.
  * classStatus - Denotes whether a class is open or closed to registration.
  * courseNumber - The 5-digit unique identifier for a class, also known as the course reference numbers (CRN).
  * seatInformation - An object containing information about the seats of a class.
    * seatsTaken - The number of seats taken in the class.
    * seatsAvailable - The number of seats available in the class.
    * seatsTotal - The total number of seats in the class.
  * dateTimeInformation - An object containing information about the class start/end time and date.
    * startDate - The start date, in the format of YYYY-MM-DD, of the class.
    * endDate - The end date, in the format of YYYY-MM-DD, of the class.
    * startTime - The time, in the format of the 24-hour clock, when the class starts.
    * endTime - The time, in the format of the 24-hour clock, when the class ends.
  * attributes - Additional features of the class. Attributes can include 'Core', 'WeekendU', 'Distance Education', or any combination of the three.
  * classDays - An object containing information whether the class takes place on a day or not.
    * monday - Indicates if a class meets up on Monday, or not.
    * tuesday - Indicates if a class meets up on Tuesday, or not.
    * wednesday - Indicates if a class meets up on Wednesday, or not.
    * thursday - Indicates if a class meets up on Thursday, or not.
    * friday - Indicates if a class meets up on Friday, or not.
    * saturday - Indicates if a class meets up on Saturday, or not.
    * sunday - Indicates if a class meets up on Sunday, or not.
  * instructorInformation - An object containing information about the instructor of the class.
    * instructor - The name of the instructor.
    * instructorEmail - The instructor's email address.
  * locationInformation - An object containing information about the location of the class.
    * location - The campus where the class takes place.
    * buildingID - Unique number to identify a building.
    * buildingAbbreviation - The abbreviation of a building.
    * buildingName - The full name of the building where the class takes place at.
  * format - Indicates how the content of the class will be delivered. Examples include: Face to Face, Hybrid and Online.
  * description - The full class description.
  * duration - The length, in weeks, of the class.
  * session - The portion of the term a class will take place under. For example, Regular Academic Session takes up the entire term while Session 6 takes up the last 5 weeks of a term.
  * component - The type of delivery of content the class has. Examples include: LEC (lecture), LAB (laboratory) and IND (Independent Studies).
  * syllabus - If a syllabus exists, this will be a URL that contains the syllabus, usually in a PDF or DOCX format. If a syllabus does not exist, the value will be "Unavailable".
  * core - An array that holds all of the core requirements this class fulfills.
  * lastUpdated - The date, in the format of YYYY-MM-DD HH:MM:SS, of the last time the last was updated/inserted. A class would usually update to reflect seat count changes.
    
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
