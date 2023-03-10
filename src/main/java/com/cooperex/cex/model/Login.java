package com.cooperex.cex.model;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Login {
    private String email;
    private String password;

    @JsonCreator
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

