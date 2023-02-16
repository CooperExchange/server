/*
ECE-366: CooperExchange
Code written by: Chris Lee
Description: Account with common properties of a financial exchange account
 */

package account;

public class Account {
    //Private attributes
    private int userID;
    private String firstName;
    private String lastName;
    private int ssn;
    private String username;
    private String password;
    private String email;
    private int numOfTrades;
    private double totalDeposit;
    private double totalWithdrawn;
    private double currentBalance;
    private double netProfit;

    //Constructor
    public Account (int userID, String firstName, String lastName, int ssn, String username, String password, String email){
        setUserID(userID);
        setFirstName(firstName);
        setLastName(lastName);
        setSsn(ssn);
        setUsername(username);
        setPassword(password);
        setEmail(email);

        //Initialized to zero
        numOfTrades = 0;
        totalDeposit = 0;
        totalWithdrawn = 0;
        currentBalance = 0;
        netProfit = 0;
    }

    //Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    public int getNumOfTrades() {
        return numOfTrades;
    }

    public void setNumOfTrades(int numOfTrades) {
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
