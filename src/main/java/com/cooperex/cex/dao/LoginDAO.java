package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;
import com.cooperex.cex.model.Login;

import java.sql.ResultSet;
import java.sql.*;


public class LoginDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;
    private Connection connection;

    public LoginDAO() {
        System.out.println("LoginDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        this.connection = connection;
    }

    public String returnUserId(Login login) {
        String SQL = "SELECT user_id, username, pass_word " +
                "FROM accounts WHERE email=? and pass_word=?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setString(1, login.email);
            statement.setString(2, login.password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String userId = Long.toString(rs.getLong("user_id"));
                return userId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No matching record was found. Please check your email or password";
    }
}

