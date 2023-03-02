package com.cooperex.cex.model;

import com.cooperex.cex.DatabaseSQLExecutor;
import com.cooperex.cex.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountSettingDAO extends DataAccessObject {

    //Constructor
    public AccountSettingDAO(Connection connection) {
        super(connection);
        System.out.println("AccountSettingDAO object has been initialized with successful DB connection");
    }

    // To-do: Implement error handling for username and email duplicates
    public String updateAccountInfoById(String userId, AccountSetting accountSetting) {
        System.out.println("User requests account info update");

        String UPDATE_ACCOUNT_INFO_BY_ID = "UPDATE accounts set " +
                "email = ?, first_name = ?, last_name = ?, username = ?, pass_word = ? " +
                "where user_id = ?;";

        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_ACCOUNT_INFO_BY_ID);) {
            statement.setString(1, accountSetting.getEmail());
            statement.setString(2, accountSetting.getFirstName());
            statement.setString(3, accountSetting.getLastName());
            statement.setString(4, accountSetting.getUsername());
            statement.setString(5, accountSetting.getPassword());
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
        String RESET_ACCOUNT_BY_ID = "update accounts set " +
                "total_deposit = ?, total_withdrawal = ? " +
                "where user_id = ?;";
        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(RESET_ACCOUNT_BY_ID);) {
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




