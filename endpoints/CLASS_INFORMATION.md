# Class Information

```
GET /api/information
```
## Description
Returns an array of classes.

## Parameters
* department - A String that represents the department class(es) belong to.
* credit_hours - An integer that represents the number of credit hours a class fulfills.
* core - A String representing the core categories of a class.

## Return Format
Returned is a JSON object with keys:
* statusCode - An integer that indicates success (1), empty response (0), failure (-1).
* message - A string message indicating success or failure.
* numberOfRows - The number of elements in the result value.
* parameters - The parameter(s) passed through the request and their value(s).
* result - An object array holding the requested core class information.
  * department - The department which the class resides in.
  * department_crn - The 4 digit course number for the class.
  * class_description - The description of the class.
  * class_title - The title of the class.
  * credit_hours - The number of credit hours a class fulfills.
  * core_id - An integer that corresponds to a core category.

## Example With No Parameters

**Requested URL**

```GET /api/information/```

**JSON Response**

The following JSON response has been shortened.
```
{
  "statusCode" : 1,
  "message" : "Successful",
  "numberOfRows" : 16,
  "parameters" : {
    "department" : "aas"
  },
  "result" : [ {
    "department" : "AAS",
    "department_crn" : "1310",
    "class_description" : "Cr: 3. (3-0). Survey of Africana culture in Americas and throughout the world by examining its origins and development from an African-centered point of reference.",
    "class_title" : "Introductn to Africana Culture",
    "credit_hours" : 3,
    "core_id" : null
  }, {
    "department" : "AAS",
    "department_crn" : "2320",
    "class_description" : "Cr. 3. (3-0). Prerequisite: ENGL 1303. An introductory analysis of the discipline of African American/African Studies, its mission, key theories and major concerns.",
    "class_title" : "Intro To African American Stdy",
    "credit_hours" : 3,
    "core_id" : "4"
  }, {

   .
   .
   .

  }, {
    "department" : "WGSS",
    "department_crn" : "4360",
    "class_description" : "Prerequisite(s): ENGL 1304 or equivalent. WGSS 3350 or WGSS 3360 or permission of the instructor. Student should be a registered WGSS major and have completed all or most of their other required coursework for the major. THE WGSS Capstone Internship Course involves the student working in an approved gender-equity-focused local advocacy or service nonprofit or business. In addition the student will document their work and write a paper analyzing it within a disciplinary framework arranged in advance with the course's faculty supervisor. The student will hold at least 4 meetings with the faculty supervisor over the semester, and work regularly with a supervisor based in the nonprofit or business itself.",
    "class_title" : "Capstone Internship Course",
    "credit_hours" : 3,
    "core_id" : null
  }	]
}
```

## Example With Parameters
**Requested URL**

```GET /api/information?department=COSC&credit_hours=3&core=9```

**JSON Response**

The following JSON response has been shortened.


```
{
  "statusCode" : 1,
  "message" : "Successful",
  "numberOfRows" : 1,
  "parameters" : {
    "department" : "COSC",
    "credit_hours" : "3",
    "Core" : "9"
  },
  "result" : [ {
    "department" : "COSC",
    "department_crn" : "1306",
    "class_description" : "Cr. 3. (3-0). Prerequisite: MATH 1310 or equivalent. May not be applied to a major or minor in computer science. Overview of basic hardware and software concepts of a computer with design, analysis and programming of efficient algorithms to solve computational problems.",
    "class_title" : "Computer Science & Program",
    "credit_hours" : 3,
    "core_id" : "9"
  } ]
}
```