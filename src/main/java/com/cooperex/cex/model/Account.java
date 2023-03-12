package com.cooperex.cex.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.json.JSONObject;
import com.google.gson.Gson;

public class Account {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private double totalDeposit;
    private double totalWithdrawal;
    private double remainingCash;

    @JsonCreator
    public Account(String email,
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

    public Account() {}

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

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

    public double getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(double totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public double getTotalWithdrawal() {
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(double totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public double getRemainingCash() {
        return remainingCash;
    }

    public void setRemainingCash(double remainingCash) {
        this.remainingCash = remainingCash;
    }

}


