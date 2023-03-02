package com.cooperex.cex.model;
import com.cooperex.cex.DatabaseSQLExecutor;
import com.cooperex.cex.util.DataAccessObject;

import java.sql.ResultSet;
import java.sql.*;


public class LoginFormDAO extends DataAccessObject {

    //Constructor
    public LoginFormDAO(Connection connection) {
        super(connection);
        System.out.println("LoginFormDAO object has been initialized with successful DB connection");
    }

    public String returnUserId(LoginForm loginForm) {
        String GET_ONE = "SELECT user_id, username, pass_word " +
                "FROM accounts WHERE email=? and pass_word=?";

        try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE);) {
            statement.setString(1, loginForm.getEmail());
            statement.setString(2, loginForm.getPassword());
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

