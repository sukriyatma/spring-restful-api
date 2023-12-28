# Spring Boot 3.2 Learning Project with Spring Security 6 and JWT Authentication

This repository serves as a learning project for those interested in exploring Spring Boot 3.2 along with the latest Spring Security version (6) and JWT (JSON Web Token) authentication. The project is designed to provide a hands-on experience for developers seeking to understand the fundamentals of Spring Boot and secure web application development.

## Introduction

This project is a simple Spring Boot application that demonstrates the integration of Spring Security for authentication and authorization, along with JWT for secure token-based authentication. It is specifically crafted for individuals who are learning Spring Boot and want to explore the latest features introduced in version 3.2.

## Features

- **Spring Boot 3.2**: Utilizing the latest version of Spring Boot for building robust and efficient applications.
- **Spring Security 6**: Incorporating Spring Security for handling authentication and authorization.
- **JWT Authentication**: Implementing JSON Web Tokens for secure and stateless authentication.
- **Learning Focus**: Structured as a learning project, emphasizing clarity and simplicity.

## Getting Started

### Prerequisites

Before you begin, make sure you have the following installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

## API Endpoints

The project includes basic endpoints for user management and authentication. Refer to the [API documentation](http://localhost:8080/swagger-ui.html) for details on each endpoint.

## Authentication

Secure your API by obtaining a JWT token through the authentication endpoint:

```bash
curl --location 'localhost:8080/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--data '{
    "username" : "sukri",
    "password" : "rahasia",
    "name"     : "Muhammad Sukriyatma"
}'```

```bash
curl --location 'localhost:8080/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username" : "sukriyatma",
    "password" : "matamu"
}'
```
