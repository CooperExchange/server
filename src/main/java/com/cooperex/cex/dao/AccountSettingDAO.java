package com.cooperex.cex.dao;
import com.cooperex.cex.model.AccountSetting;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountSettingDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;
    private Connection connection;

    public AccountSettingDAO() {
        System.out.println("AccountSettingDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        this.connection = connection;
    }

    // To-do: Implement error handling for username and email duplicates
    public String updateAccountInfoById(String userId, AccountSetting accountSetting) {
        System.out.println("User requests account info update");

        String SQL = "UPDATE accounts set " +
                "email = ?, first_name = ?, last_name = ?, username = ?, pass_word = ? " +
                "where user_id = ?;";

        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setString(1, accountSetting.email);
            statement.setString(2, accountSetting.firstName);
            statement.setString(3, accountSetting.lastName);
            statement.setString(4, accountSetting.username);
            statement.setString(5, accountSetting.password);
            statement.setInt(6, userIdInt);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User info has been updated";
    }

    public String resetAccountById(String userId) {
        // Reset user information
        System.out.println("User requests account reset");
        String SQL = "update accounts set " +
                "total_deposit = ?, total_withdrawal = ? " +
                "where user_id = ?;";
        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setDouble(1, 0);
            statement.setDouble(2, 0);
            statement.setInt(3, userIdInt);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "called resetAccountById";
    }
}




