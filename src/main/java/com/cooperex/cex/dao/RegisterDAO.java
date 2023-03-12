
package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;
import com.cooperex.cex.model.Register;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

public class RegisterDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;
    private Connection connection;

    // AccountDAO Initialization & DB Connection
    public RegisterDAO() {
        System.out.println("AccontDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        this.connection = connection;
    }

    // Create an account
    public boolean createAccount(Register account) {
        // Prepare SQL statement
        String SQL = "INSERT INTO accounts (first_name, last_name, username, pass_word, email) "
                    + "VALUES ('"+account.getFirstName()+"','"+account.getLastName()+"'," +
                "'"+account.getUsername()+"', '"+account.getPassword()+"', '"+account.getEmail()+"');";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            statement.close();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Create a random account
    public boolean createRandomAccount() {
        Random rand = new Random();
        int int_random = rand.nextInt(10000);
        String randomUsername = "PaulGeorge" + int_random;
        String randomEmail = "PaulGeorge" + int_random + "@cooper.edu";
        String SQL = "INSERT INTO accounts (first_name, last_name, username, pass_word, email) "
                + "VALUES ('Paul', 'Kim', '"+randomUsername+"', 'abc123', '"+randomEmail+"');";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            statement.close();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


