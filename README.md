# doctor-label-service
This microservices responsible to manager Label informations

## Back-end Technology stack used

- Java 11
- Spring Boot Web 2.4.3
- Spring Boot Starter Data JPA
- Spring Boot devtools
- Spring Boot Starter Test
- Spring Boot Starter Validation
- Spring Cloud Starter Openfeign
- Spring Boot Starter Security
- IO JSON WEB TOKEN v0.9.1
- SPringFox Swagger 2 v2.9.2
- SpringFox Swagger UI v2.9.2
- Mysql
- Docker
- Maven

## Runnning Spring Boot Applicaiton DoctorLabel Label Service Back-End Microservices "traditional way"

```bash
git clone https://github.com/igormelao/doctor-label-service.git
cd doctorlabel-label-service
# You need a instancy of Mysql running on port 3306 with
 - database: db_dev_doctor_label
# Generate JARs with Maven
 - mvn clean package
# Starting Spring Boot Projet 
 - with JAR generated, go to the folder /target and just run  command
  - java -jar doctorlabel-label-service-0.0.1-SNAPSHOT.jar
  
PS: The Spring Boot will run the file inside of project data.sql and will populate some data.  

http://localhost:8080/labels/
 
```
