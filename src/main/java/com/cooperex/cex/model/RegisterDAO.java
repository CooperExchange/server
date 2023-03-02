
package com.cooperex.cex.model;
import com.cooperex.cex.DatabaseSQLExecutor;
import com.cooperex.cex.util.DataAccessObject;

import java.util.Random;
import java.sql.Connection;

public class RegisterDAO extends DataAccessObject {
    private DatabaseSQLExecutor databaseSQLExecutor;

    //Constructor
    public RegisterDAO(Connection connection) {
        super(connection);
        System.out.println("AccontDAO object has been initialized with successful DB connection");
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
    }

    // Create an account
    public String createAccount(Register account) {
        // Prepare SQL statement
        String SQL = "INSERT INTO accounts (first_name, last_name, username, pass_word, email) "
                    + "VALUES ('"+ account.getFirstName() +"','"+ account.getLastName() +"'," +
                "'"+ account.getUsername() +"', '"+ account.getPassword() +"', '"+ account.getEmail() +"');";
        return databaseSQLExecutor.executeSQLStatement(SQL);
    }

    // Create a random account
    public String createRandomAccount() {
        Random rand = new Random();
        int int_random = rand.nextInt(10000);
        String randomUsername = "PaulGeorge" + int_random;
        String randomEmail = "PaulGeorge" + int_random + "@cooper.edu";
        String SQL = "INSERT INTO accounts (first_name, last_name, username, pass_word, email) "
                + "VALUES ('Paul', 'Kim', '"+randomUsername+"', 'abc123', '"+randomEmail+"');";
        return databaseSQLExecutor.executeSQLStatement(SQL);
    }
}


