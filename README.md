# checker-app-rest-service
A self made REST API for a school project. This will function as the backend for an mobile app called Checker. 

Requirements
- MySQL 8.0
- Backend server
  * e.g. Payara Server (https://www.payara.fish)
- IDE
  * e.g. IntelliJ IDEA
  
Maven Dependecies (see pom.xml file for versions)
- Java EE 8 for JAX-RS
- Hibernate
- JPA 2.1
- MySQL Java Database Connector
- JSON-B

Test requests

You can test the requests by using the tool Postman (https://www.getpostman.com/).
You can import the CheckerApp.postman_collection.json file in Postman and all the available requests will show up.

To Start
- Change the credentials to your MySQL credentials in the DataSourceDefinitionConfig.java file. This file can be found in:
  src/main/java/nl/hva/mobdev/checker/config/
- Do a maven clean and install
- Add the backend server to your IDE and start this. If everything works correct a browser screen will pop up with the text Hello World!

Thats it... After this you can fire some Postman requests.




