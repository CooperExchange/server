package com.cooperex.cex.model;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Account {
    public String email;
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public Double total_deposit;
    public Double total_withdrawal;
    public Long userId;

    @JsonCreator
    public Account(Long userId,
                   String email,
                   String firstName,
                   String lastName,
                   String username,
                   String password,
                   Double total_deposit,
                   Double total_withdrawn) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.total_deposit = total_deposit;
        this.total_withdrawal = total_withdrawal;
    }
}

