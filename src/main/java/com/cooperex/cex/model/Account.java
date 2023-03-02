/*
ECE-366: CooperExchange
Description: Account with common properties of a financial exchange.
 */

package com.cooperex.cex.model;
import com.cooperex.cex.util.DataTransferObject;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Account implements DataTransferObject {
    private long userId;
    private String email;
    private String firstName;
    private String lastName;
    private int ssn;
    private String username;
    private String password;
    private double total_deposit;
    private double total_withdrawal;
    private long numOfTrades;
    private double currentBalance;
    private double netProfit;

    //Constructor
    @JsonCreator
    public Account(long userId, String email, String firstName, String lastName, int ssn, String username, String password, double total_deposit, double total_withdrawal, long numOfTrades, double currentBalance, double netProfit) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.username = username;
        this.password = password;
        this.total_deposit = total_deposit;
        this.total_withdrawal = total_withdrawal;
        this.numOfTrades = numOfTrades;
        this.currentBalance = currentBalance;
        this.netProfit = netProfit;
    }


    //abstract method implementation
    public long getId(){
        return userId;
    }

    //Attributes as Strings
    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", total_deposit=" + total_deposit +
                ", total_withdrawal=" + total_withdrawal +
                ", numOfTrades=" + numOfTrades +
                ", currentBalance=" + currentBalance +
                ", netProfit=" + netProfit +
                '}';
    }

    //Setters and Getters

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
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

    public double getTotal_deposit() {
        return total_deposit;
    }

    public void setTotal_deposit(double total_deposit) {
        this.total_deposit = total_deposit;
    }

    public double getTotal_withdrawal() {
        return total_withdrawal;
    }

    public void setTotal_withdrawal(double total_withdrawal) {
        this.total_withdrawal = total_withdrawal;
    }

    public long getNumOfTrades() {
        return numOfTrades;
    }

    public void setNumOfTrades(long numOfTrades) {
        this.numOfTrades = numOfTrades;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(double netProfit) {
        this.netProfit = netProfit;
    }
}

