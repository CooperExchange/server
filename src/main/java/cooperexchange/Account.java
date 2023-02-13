package cooperexchange;

import java.sql.*;


public class Account {
    String username;
    String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void createAccount(String username, String password) {

    }

}
