package com.cooperex.cex.model;
import com.fasterxml.jackson.annotation.JsonCreator;

public class AccountSetting {
    public String email;
    public String firstName;
    public String lastName;
    public String username;
    public String password;

    @JsonCreator
    public AccountSetting(String email,
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


