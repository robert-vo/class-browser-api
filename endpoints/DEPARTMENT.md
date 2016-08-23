# Department 

```
GET /api/department
```
## Description
Returns an array containing the abbreviation and names of all departments at the University of Houston.

## Parameters
No parameters are used for this endpoint.

## Return Format
Returned is a JSON object with keys: 
* statusCode - An integer that indicates success (1) or failure (0).
* message - A string message indicating success or failure.
* numberOfRows - The number of elements in the result value.
* parameters - The parameter(s) passed through the request and their value(s).
* result - An object array holding the requested core class information.
  * departmentName - The abbreviation of the department.
  * departmentFullName - The full name of the department.

## Example

**Requested URL**

```GET /api/department```

**JSON Response**

The following JSON response has been shortened for readability.

```
{
  "statusCode" : 1,
  "message" : "Successful",
  "numberOfRows" : 153,
  "parameters" : { },
  "result" : [ {
    "departmentName" : "AAS",
    "departmentFullName" : "African American Studies"
  }, {
    "departmentName" : "ACCT",
    "departmentFullName" : "Accounting"
  }, {
    "departmentName" : "AFSC",
    "departmentFullName" : "Air Force Science"
  },
   
   ...
   
   , {
    "departmentName" : "WGSS",
    "departmentFullName" : "WomenGendSexualitySt"
  } ]
}
```
