4-endpoints
===========

Code for M-GO

THis project contains code for the Four Endpoints exercise for M-GO.

The project was created usingg Intellij v13.1. Intellij project files are checked in. In addition, a Maven pom file is also checked in. 
To build the project from source navigate to the project's root directory and use Maven command as follows:

mvn -DskipTests package

Deploy the resulting WAR file found in the target directoruy to the container such as Tomcat. 

Restful functionality is provided by the Apache CXF framework. In addition, Spring and Hibernate are also used.

MySQL is uused as a back-end datastore. The root directory contains script files for several platforms which can be used to create and load test tables in MySQL's default "test" database. If you are installing MySQL from scratch, please use the following password for the default "root" user: dbadm1n. MySQL server host address, userId and password are supplied through the mysql.properties file which can be found under webapp/WEB-INF/properties directory. The host address is part of the mysql.connection_URL property.

To execute a db_creation_and_load* script, use mySQL command line utility, e.g. 

mysql < db_creation_and_load_ux.sql

THe repository also contains function tests that exercise each endpoint. The tests are written against the container running on localhost. 
To run against a different host, modify the host address in the code, recompile and run.

The endpoints are defined through the following URL's:

1. /rest/v1/getUsers - lists all the users defined in the database table. 
    Implemented as a GET request
    Accepts the following optional query parameters:
      attributeName - an attribute supplying crietria for selecting a subset of users
      attributeValue - desired value of the attribute to be used for comparison when selecting users

    possible attribute names are:
    
    firstName
    lastName
    gender
    addressLine1
    addressLine2
    city
    stateOrProvince
    country
    postalCode
    userId
    
    see the database loading script for specific values being loaded. Database column names correspond to the attribute names.
    
    If both query parameters are omitted, all the users are retrieved. EItehr both or none of the parameters can be specified.
    
    returns a JSON object which contains a list of users satisfying query criteria.
    
2. /rest/v1/authenticate - authenicates a user against the database. Matches user id and (potentially) encrypted pasword.
    receives a JSON request object containing userid and password through a POST request
    returns a JSON object conatining authentication status and possible reason for authentication failure (e.g. undefined user, wrong         password).

3. /rest/v1/resourceStatus - returns statuses of resources needed by this application. At this point the only resource is MySQL server.
    returns a JSON object containg a list of resource statuses. Implemented as a GET request.

4. /rest/v1/fileList - lists the contents of a given directory. Implemented as a GET request. Receives the absolute path to the directory as 
    a query parameter called "directoryPath". If directory exists and is in fact a valid directory, a lits of its files will be returned along with names and types. Otherwise, an error status is returned along with diagnistic message.

THe above URL's should be prefixed with host name and context root.


    
    
