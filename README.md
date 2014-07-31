4-endpoints
===========

Code for M-GO

THis project contains code for the Four Endpoints exercise for M-GO.

The project was created usingg Intellij v13.1. Intellij project files are checked in. In addition, a Maven pom file is also checked in. 
To build the project from source navigate to the project's root directory and use Maven command as follows:

mvn -DskipTests package

Deploy the resulting WAR file found in the target directoruy to the container. 

THe repository also contains function tests that exercise each endpoint. The tests are written against the container running on localhost. 
To run against a different host, modify the host address in the code, recompile and run.
