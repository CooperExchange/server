/*
ECE-366: CooperExchange
Description: Account with common properties of a financial exchange.
 */

package CooperExchangeJDBC.Util.Account;

import CooperExchangeJDBC.Util.DataTransferObject;

public class Account implements DataTransferObject {
    //Private attributes
    private long userID;
    private String firstName;
    private String lastName;
    private int ssn;
    private String username;
    private String password;
    private String email;
    private long numOfTrades;
    private double totalDeposit;
    private double totalWithdrawn;
    private double currentBalance;
    private double netProfit;

    //abstract method implementation
    public long getId(){
        return userID;
    }

    //Attributes as Strings
    @Override
    public String toString() {
        return "Account{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn=" + ssn +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", numOfTrades=" + numOfTrades +
                ", totalDeposit=" + totalDeposit +
                ", totalWithdrawn=" + totalWithdrawn +
                ", currentBalance=" + currentBalance +
                ", netProfit=" + netProfit +
                '}';
    }

    //Getters and Setters
    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumOfTrades() {
        return numOfTrades;
    }

    public void setNumOfTrades(long numOfTrades) {
        this.numOfTrades = numOfTrades;
    }

    public double getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(double totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public double getTotalWithdrawn() {
        return totalWithdrawn;
    }

    public void setTotalWithdrawn(double totalWithdrawn) {
        this.totalWithdrawn = totalWithdrawn;
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
