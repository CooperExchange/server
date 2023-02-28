package com.cooperex.cex.model;
import com.fasterxml.jackson.annotation.JsonCreator;

public class LoginForm {
    public String email;
    public String password;

    @JsonCreator
    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

