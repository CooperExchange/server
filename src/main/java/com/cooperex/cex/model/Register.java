package com.cooperex.cex.model;
import com.cooperex.cex.util.DataTransferObject;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Register implements DataTransferObject {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @JsonCreator
    public Register(String email,
                    String firstName,
                    String lastName,
                    String username,
                    String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    //Print attributes as strings


    @Override
    public String toString() {
        return "Register{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}

