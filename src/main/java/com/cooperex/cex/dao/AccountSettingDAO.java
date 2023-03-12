package com.cooperex.cex.dao;
import com.cooperex.cex.model.AccountSetting;
import com.cooperex.cex.DatabaseExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountSettingDAO {
    private Connection connection;

    public AccountSettingDAO() {
        System.out.println("AccountSettingDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    // To-do: Implement error handling for username and email duplicates
    public boolean updateAccountInfoById(String userId, AccountSetting accountSetting) {

        // Create an account
        String SQL = "UPDATE accounts set " +
                "email = ?, first_name = ?, last_name = ?, username = ?, pass_word = ? " +
                "where user_id = ?;";

        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setString(1, accountSetting.getEmail());
            statement.setString(2, accountSetting.getFirstName());
            statement.setString(3, accountSetting.getLastName());
            statement.setString(4, accountSetting.getUsername());
            statement.setString(5, accountSetting.getPassword());
            statement.setInt(6, userIdInt);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    public String resetAccountById(String userId) {
//        // Reset user information
//        System.out.println("User requests account reset");
//        String SQL = "update accounts set " +
//                "total_deposit = ?, total_withdrawal = ? " +
//                "where user_id = ?;";
//        int userIdInt = Integer.parseInt(userId);
//        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
//            statement.setDouble(1, 0);
//            statement.setDouble(2, 0);
//            statement.setInt(3, userIdInt);
//            statement.executeUpdate();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "called resetAccountById";
//    }
}




