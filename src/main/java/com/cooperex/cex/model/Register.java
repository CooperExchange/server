package com.cooperex.cex.model;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Register {
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

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

