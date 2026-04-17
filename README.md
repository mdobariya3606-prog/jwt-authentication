JWT Authentication API

Secure backend authentication system built with Java, Spring Boot,
Spring Security, and MySQL. Implements JWT-based authentication with
stateless session management.

Role-Based Access Control and Refresh Token are planned but not
implemented yet.

------------------------------------------------------------------------

Overview

This project demonstrates secure authentication using JSON Web Token
(JWT). Users can register and login, and receive a JWT token to access
protected APIs.

JWT removes the need for server-side sessions and improves scalability.

------------------------------------------------------------------------

Tech Stack

-   Java
-   Spring Boot
-   Spring Security
-   JWT (JSON Web Token)
-   MySQL
-   JPA / Hibernate
-   Maven
-   REST APIs

------------------------------------------------------------------------

Features

-   User Registration
-   User Login Authentication
-   Password encryption using BCrypt
-   JWT Token generation
-   JWT Token validation
-   Stateless authentication
-   Protected APIs
-   Clean layered architecture
-   Exception handling

------------------------------------------------------------------------

## Project Structure

authentication
│
├── AuthenticationApplication.java
│
├── config
│   ├── JwtFilter.java
│   ├── JWTService.java
│   └── SecurityConfig.java
│
├── controller
│   ├── HelloController.java
│   └── UserController.java
│
├── model
│   ├── Student.java
│   ├── UserPrincipal.java
│   └── Users.java
│
├── repo
│   └── UserRepo.java
│
└── service
    ├── MyUserDetailsService.java
    └── UserService.java

------------------------------------------------------------------------

API Endpoints

Register User POST /api/auth/register

Request: { “name”: “Meet”, “password”:
“123456” }

Login User POST /api/auth/login

Request: { “name”: “Meet”, “password”: “123456” }

Response: { “token”: “jwt_token_here” }

Protected API Example GET /api/test

Header: Authorization: Bearer jwt_token_here

------------------------------------------------------------------------

Authentication Flow

1.  User registers account
2.  User logs in using email and password
3.  Server validates credentials
4.  JWT token is generated
5.  Client stores token
6.  Token sent in Authorization header
7.  Spring Security filter validates token
8.  Access granted if token is valid

------------------------------------------------------------------------

Database Schema

User Table

id : Long name : String password : String

------------------------------------------------------------------------

Setup Instructions

1.  Clone repository

git clone https://github.com/your-username/jwt-authentication.git cd
jwt-authentication

2.  Configure MySQL database

spring.datasource.url=jdbc:mysql://localhost:3306/jwt_db
spring.datasource.username=root spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true

3.  Install dependencies

mvn clean install

4.  Run application

Run JwtAuthenticationApplication.java

Server runs on: http://localhost:8080

------------------------------------------------------------------------

Future Improvements

-   Role Based Access Control (ADMIN, USER)
-   Refresh Token implementation
-   Logout functionality
-   Email verification
-   Token expiration improvements
-   Better exception handling

------------------------------------------------------------------------

Learning Outcomes

-   Spring Security workflow
-   JWT token generation and validation
-   Stateless authentication
-   Password hashing with BCrypt
-   Securing REST APIs

------------------------------------------------------------------------

Author

Meet Dobariya Backend Developer | Java | Spring Boot | SQL
