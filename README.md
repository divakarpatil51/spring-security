
# Authentication mechanisms implementations using Spring Security:

This repo contains implementation for following authentication mechanisms using Spring boot and Spring security:
1. In-memory Authentication.
2. JDBC Authentication using H2 database
3. JPA Authentication using PostgreSQL
4. LDAP Authentication (Not yet implemented)
5. JWT Token Authentication (Not yet implemented)
6. OAuth2.0 Authentication (Not yet implemented)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

-  Java 8+
-  Maven
- Docker and Docker Compose

### Installing

* Clone the repo using following command: 

```
    git clone https://github.com/divakarpatil51/spring-security.git

```

**Running application from command line**

*  Build the application using following command:

```
	mvn clean install
```

* Run the application from command line:

```
	mvn spring-boot:run
```

* Application Deployed URL

```
	http://localhost:8080/login
```

**Running application using Docker:**

*  Start the application using following command:

```
	docker-compose up -d
```

* Application deployed URL:

```
	http://localhost:8080/login
```

* Stop the application:

```
	docker-compose down -v
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security) 
* [Maven](https://maven.apache.org/)
* [PostgreSQL](https://rometools.github.io/rome/) 
* [Docker](https://www.docker.com/) 
* [Jenkins](https://jenkins.io/)


## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/divakarpatil51/spring-security/blob/master/LICENSE) file for details
