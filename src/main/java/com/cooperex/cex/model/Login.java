package com.cooperex.cex.model;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Login {
    public String email;
    public String password;

    @JsonCreator
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

