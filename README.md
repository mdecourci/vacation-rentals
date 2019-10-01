# Vacation Rental
* Uses Spring Boot Eureka framework for scalability and resilience.
* Unzip vacation-rentals.zip

## Modules

Maven parent that contains 2 modules
* registration-service
* listings-service

### listings-service
* Defines endpoints to create and return listing
* Configured as a Eureka client (port: 2222) to register with the discovery service:  registration-service
* Spring Boot uber jar
* Swagger http://localhost:2222/swagger-ui.html#/

#### Build and run listings-service
To build (not need to run the registration-service service)
```
cd vacation-rentals
cd listings-service
mvn clean install
```
then run
```
java -jar target/listings-service-1.0-SNAPSHOT.jar
```
 
### registration-service
* Eureka registry service on port: 1111
* Spring Boot uber jar

#### Build and run registration-service
To build
```
cd vacation-rentals
cd registration-service
mvn clean install
```
then run
```
java -jar target/registration-service-1.0-SNAPSHOT.jar
```

## Eureka Dashboard
On http://localhost:1111/
 
 