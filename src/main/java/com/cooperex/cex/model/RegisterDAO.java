
package com.cooperex.cex.model;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;

import java.util.Random;
import java.sql.Connection;

public class RegisterDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;

    // AccountDAO Initialization & DB Connection
    public RegisterDAO() {
        System.out.println("AccontDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
    }

    // Create an account
    public String createAccount(Register account) {
        // Prepare SQL statement
        String SQL = "INSERT INTO accounts (first_name, last_name, username, pass_word, email) "
                    + "VALUES ('"+account.firstName+"','"+account.lastName+"'," +
                "'"+account.username+"', '"+account.password+"', '"+account.email+"');";
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


