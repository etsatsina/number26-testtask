## Technology Stack
* The API was implemented using Spring Boot and (embedded) Tomcat
* Made use of Java 8
* Maven was chosen for building and managing dependencies
* Unit and integration tests were implemented with the help of Mockito, Junit and Spring Test Tools
* A HashMap was used as data structure for storing transactions

You can run project with following command - `mvn spring-boot:run`

***
## Other thoughts
I made an assumption that PUT request only creates new transaction without possible updating. 

Also I decided to use HashMap as a happy medium between other possible solutions. Depends on requests frequency, the amount of stored data, importance of fast reading of data or concurrency usage the current realization of repository can be easily replaced with realization based on e.g.
* ConcurrentHashMap when you need very high concurrency 
* different types of maps for access by type, other parameters
* SQL solution.

HashMap doesn't give us overhead like ConcurrentHashMap and shows a constant complexity in common case for the most often operations like `get(id)`, `put()`, `contains(id)`.






