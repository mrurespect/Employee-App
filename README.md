# Employee Management System with Spring Security

This Spring Boot application implements a basic Employee Management System with role-based access control using Spring Security.

## Table of Contents

- [Introduction](#introduction)
- [Security Configuration](#security-configuration)
- [Authentication and Authorization](#authentication-and-authorization)
- [HTTP Security Configuration](#http-security-configuration)
- [User Roles](#user-roles)
- [User Registration and Login](#user-registration-and-login)
- [User Validation](#user-validation)
- [Logging Simplified with AOP](#logging-simplified-with-aop)
- [Exception Handling](#exception-handling)
- [Project Structure](#project-structure)
- [Usage](#usage)
- [Dependencies](#dependencies)

## Introduction

This application provides functionality for user registration, login, and CRUD operations on employees. Different roles (EMPLOYEE, MANAGER, ADMIN) have different levels of access to various parts of the application.

## Security Configuration

The security configuration is managed by the `SecurityConfig` class. This class includes the following key components:

- **Authentication Provider**: Configures a `DaoAuthenticationProvider` bean that uses a custom `UserService` to load user details from a data source.

- **Password Encoding**: Passwords are securely encoded using the BCryptPasswordEncoder.

- **HTTP Security Configuration**: Defines access rules based on HTTP methods and paths for different user roles.

## Authentication and Authorization

Authentication is the process of verifying user identity, while authorization controls what actions an authenticated user is allowed to perform. This application uses Spring Security to manage both authentication and authorization.

## HTTP Security Configuration

The `HttpSecurity` object is configured to handle different HTTP methods and paths with specific role-based access controls. For example:

- Employees and managers can view the list of employees.
- Managers and admins can add new employees.
- Admins have exclusive access to update and delete employee records.

## User Roles

The application defines three roles:

- **EMPLOYEE**: Basic employee role.
- **MANAGER**: Managerial role with additional privileges.
- **ADMIN**: Administrative role with full access to all features.

## User Registration and Login

The application supports user registration and login with a custom JDBC table. The `UserService` interacts with this table to manage user details and authentication.

## User Validation

User input is validated using Spring Boot Starter Validation. This ensures that the data submitted through forms meets the required criteria.

## Logging Simplified with AOP
Using Aspect-Oriented Programming (AOP). The `LoggingAspect` class captures key points like method calls and exceptions, streamlining logging. This approach minimizes code repetition, simplifies maintenance, and seamlessly integrates customizable logging into the application.
![Capture d’écran 2024-02-09 005155](https://github.com/mrurespect/Employee-App/assets/121578147/1a1fcc58-a40c-4447-8edb-55c3ced60509)


## Exception Handling

Exception handling is implemented throughout the application to provide a robust and user-friendly experience. The `SecurityConfig` class also handles access-denied exceptions.

## Project Structure

The project is organized into the following packages:

- **service**: Contains service classes handling business logic.
- **controller**: Houses controllers for processing user requests and managing views.
- **dao**: Includes data access objects responsible for interacting with the database.
- **security**: Holds security-related configurations and components.
- **entity**: Defines entity classes representing data models.

## Usage

To use this application:

1. Configure your database and update the `UserService` accordingly.
2. Customize the security configuration in the `SecurityConfig` class based on your requirements.
3. Run the application and access it through the defined URLs.

## Dependencies

This project uses the following dependencies:

- **Spring Boot Starter Data JPA**: Provides support for data access using Spring Data JPA.
- **Spring Boot Starter Web**: Includes the necessary dependencies for building web applications with Spring MVC.
- **Spring Boot Starter Thymeleaf**: Integrates Thymeleaf as the template engine for rendering views.
- **Spring Boot Starter Security**: Adds Spring Security for handling authentication and authorization.
- **Spring Boot Starter Validation**: Includes validation support for form data.
- **Thymeleaf Extras Spring Security 6**: Thymeleaf integration for Spring Security.
- **MySQL Connector/J**: MySQL database driver for connecting to the database.
- **Spring Boot DevTools**: Provides additional development-time features.
