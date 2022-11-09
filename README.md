# Crypto Recommendation Service



### Crypto Recommendation Service provide API to help to choose right Crypto.


## To run the application:
1. Run ```mvn spring-boot:run``` from directory (maven and jdk 11 are required)
2. You can run it from Docker. Firstly build by maven ```mvn package```, 
build the docker image ```docker build -t service/crypto-recomendation . ``` 
and run docker container ```docker run -p 8080:8080 service/crypto-recomendation``` (Docker is required)
3. Or to run by docker-compose ```docker-compose up --build``` after building by maven.

<br/> Service is available on the host localhost:8080

<br/>To add new crypto stocks you need to copy csv files to the directory "prices" from the root and restart application.
<br/>The application load all stocks data from files to embedded database in performance reasons.
<br/>The application can load heavy files reading and saving line by line.


* [API Documentation](http://localhost:8080/swagger-ui/index.html)

