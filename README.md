# class-browser-api [![Build Status](https://travis-ci.org/robert-vo/class-browser-api.svg?branch=master)](https://travis-ci.org/robert-vo/class-browser-api)

The Class Browser API provides access to class information for classes offered at the University of Houston. 

The API is a RESTful API built using the Spring MVC Framework. All endpoints in this API returns JSON content.

The project includes a cron task which scrapes and updates the classes, at midnight, central US time. The scraper utilizes the [<code>Class-Scraper</code>](https://github.com/robert-vo/Class-Scraper) project, another project of mines. 

## Endpoints
Click on the hyperlinks to get more information about each endpoint. 

### Information about Classes
[<code>GET:</code>```/api/core={coreID}```](endpoints/CORE.md)

[<code>GET:</code>```/api/department```](endpoints/DEPARTMENT.md)

[<code>GET:</code>```/api/information```](endpoints/CLASS_INFORMATION.md)

### Class Offerings
[<code>GET:</code>```/api/classes/term={termID}```](endpoints/TERM.md)

## Example

An example using the Class Browser API can be found at the following link. 

[Web Example](https://github.com/robert-vo/class-browser-uh-web)
