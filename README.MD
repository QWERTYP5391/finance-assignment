# yahoo-finance-assignment


## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the 
`com.yahoo.finance.exercise.StockQuoteApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) 
like so:

```shell
mvn spring-boot:run
```

You will see all information being printed to the console.

Within the `com.yahoo.finance.exercise.config` package is the configuration for the rest client in order to make the api call as well 
the configuration for the scheduler used to run the two separate task of polling the api ever 5 seconds and printing the highest change 
and overall change.

Within the `com.yahoo.finance.exercise.model` package are all the models I used in the project.

Within the `com.yahoo.finance.exercise.model` package is the business logic for handling the 
AAPL  api as well the scheduling we will like to have.

Within the `com.yahoo.finance.exercise.util` package are utilities that can be used to make quick calculations.

All tests can be found in the test package for your execution. 


