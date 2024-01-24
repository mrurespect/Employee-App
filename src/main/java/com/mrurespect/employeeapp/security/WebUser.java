package com.mrurespect.employeeapp.security;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WebUser {
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    @Size(min = 1, message = "is required")
    private String password ;

    @NotNull(message = "firstName is required")
    private String firstName;
    @NotNull(message = "lastName is required")
    private String lastName;
    @NotNull(message = "email is required")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WebUser(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WebUser() {
    }

    @Override
    public String toString() {
        return "WebUser{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
