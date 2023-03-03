package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;
import com.cooperex.cex.model.LoginForm;

import java.sql.ResultSet;
import java.sql.*;


public class LoginFormDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;
    private Connection connection;

    public LoginFormDAO() {
        System.out.println("LoginFormDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        this.connection = connection;
    }

    public String returnUserId(LoginForm loginForm) {
        String GET_ONE = "SELECT user_id, username, pass_word " +
                "FROM accounts WHERE email=? and pass_word=?";

        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE);) {
            statement.setString(1, loginForm.email);
            statement.setString(2, loginForm.password);
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

