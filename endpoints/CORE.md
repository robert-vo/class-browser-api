# Core 

```
GET /api/core={coreID}
```
## Description
Returns an array of classes that fall under the requested core category.

## Parameters
* coreID - An integer between 1 and 10, inclusive. The list below indicates what each number corresponds to.
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
  * core_title - The name of the category the core falls under.
  * hours_required - The number of hours required to fulfill the core category.

## Example

**Requested URL**

```GET /api/core=1```

**JSON Response**
```
{
  "statusCode" : 1,
  "message" : "Successful",
  "numberOfRows" : 4,
  "parameters" : {
    "Core" : "1"
  },
  "result" : [ {
    "department" : "ENGL",
    "department_crn" : "1303",
    "class_description" : "[TCCN-ENGL 1301] Cr. 3. (3-0). Prerequisite: a score of at least 240 on the THEA Writing Test or its equivalent. Students who score below the minimum must enroll in ENGL 1300. Credit may not be received for both ENGL 1303 and 1309. Detailed study of the principles of rhetoric as applied in reading and writing expository essays.",
    "class_title" : "First Year Writing I",
    "credit_hours" : 3.0,
    "core_id" : 1,
    "core_title" : "Communication",
    "hours_required" : 6
  }, {
    "department" : "ENGL",
    "department_crn" : "1304",
    "class_description" : "[TCCN-ENGL 1302] Cr. 3. (3-0). Prerequisite: ENGL 1303 or equivalent. Credit may not be received for both ENGL 1304 and 1310 or 1370. Detailed study of the principles of rhetoric as applied to analyzing and writing argumentative and persuasive essays; principles and methods of research, culminating in writing a substantial research paper.",
    "class_title" : "First Year Writing II",
    "credit_hours" : 3.0,
    "core_id" : 1,
    "core_title" : "Communication",
    "hours_required" : 6
  }, {
    "department" : "ENGL",
    "department_crn" : "1370",
    "class_description" : "Cr. 3. (3-0). Prerequisite: placement by the university's Honors College. Corequisite: HON 2301. Credit for both ENGL 1370 and 1304, 1310, or 1360 may not apply toward a degree. Principles of rhetoric as applied to writing persuasive essays; principles and methods of research.",
    "class_title" : "Composition II-Honors",
    "credit_hours" : 3.0,
    "core_id" : 1,
    "core_title" : "Communication",
    "hours_required" : 6
  }, {
    "department" : "ENGL",
    "department_crn" : "2361",
    "class_description" : "Cr. 3. (3-0). Prerequisite: placement by the Honors College and concurrent enrollment in HON 2101. Credit for both ENGL 2361 and ENGL 2302 may not apply toward a degree. Neoclassical through the present. Emphasis on composition.",
    "class_title" : "Western World Lit II--Honors",
    "credit_hours" : 3.0,
    "core_id" : 1,
    "core_title" : "Communication",
    "hours_required" : 6
  } ]
} 
```
