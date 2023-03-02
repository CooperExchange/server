package com.cooperex.cex.model;
import com.cooperex.cex.util.DataTransferObject;
import com.fasterxml.jackson.annotation.JsonCreator;

public class LoginForm implements DataTransferObject {
    private String email;
    private String password;

    @JsonCreator
    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Print attributes as strings
    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

