# class-browser-api [![Build Status](https://travis-ci.org/robert-vo/class-browser-api.svg?branch=master)](https://travis-ci.org/robert-vo/class-browser-api)

The Class Browser API provides access to class information for classes offered at the University of Houston. 

The API is a RESTful API built using the Spring MVC Framework. All endpoints in this API returns JSON content.

The project includes a cron task which scrapes and updates the classes, at midnight, central US time. The scraper utilizes the [<code>Class-Scraper</code>](https://github.com/robert-vo/Class-Scraper) project, another project of mines. 

## Environment Variables

Environment Variables are used throughout the Class Browser API to establish a database connection for storing and retrieving class information and to set constraints on the Class Scraper.

### Database Connection String

* **DATABASE_URL**: Used to address the database. 
  * The JDBC database URL format is <code>jdbc:[subprotocol]:[node]/[databaseName]</code>
  * Default value for DATABASE_URL is: <code>jdbc:mysql://localhost/class</code>
* **JDBC_DRIVER**: The database driver that allows java to interact with a database.
  * Default value for JDBC_DRIVER is: <code>com.mysql.jdbc.Driver</code>
* **USERNAME**: The username used to access the specified database.
  * Default value for USERNAME is: <code>root</code>
* **PASSWORD**: The corresponding password for the username that grants access to the database.
  * Default value for PASSWORD is: <code>password</code>

### Class Scraper Constraints
* **CAN_START_CLASS_UPDATES**: A true (1, yes, true) value will allow the scraper to run, regardless of run method (cron task or forced through trigger). 
* **PAGE_LIMIT**: Determines how many pages the scraper will traverse through per term.
* **SESSIONS_TO_SCRAPE**: Comma-separated values that denotes which sessions, for each term, the scraper will traverse through. Values include: <code>1, 2, 3, 4, 5, 6, MIN</code>.
* **TERMS_TO_SCRAPE**: Comma-separated values that denotes which terms the scraper will traverse through. Values include: [<code>See:</code>```/api/classes/term={termID}```](endpoints/TERM.md) for more information about the allows term values.
* **TRIGGER_KEYWORD**: The keyword needed to initiate the scraper from an endpoint request. The keyword is used to prevent anyone from activating the scraper on demand.

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
