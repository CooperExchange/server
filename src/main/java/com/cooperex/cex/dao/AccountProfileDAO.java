package com.cooperex.cex.dao;
import com.cooperex.cex.model.Account;
import com.cooperex.cex.DatabaseExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AccountProfileDAO {
    private Connection connection;

    public AccountProfileDAO() {
        System.out.println("AccountDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public boolean updateAccountInfoById(String userId, Account account) {

        // Create an account
        String SQL = "UPDATE accounts set " +
                "email = ?, first_name = ?, last_name = ?, username = ?, pass_word = ? " +
                "where user_id = ?;";

        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getFirstName());
            statement.setString(3, account.getLastName());
            statement.setString(4, account.getUsername());
            statement.setString(5, account.getPassword());
            statement.setInt(6, userIdInt);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getAccountProfileById(String userId) {
        String SQL = "SELECT email, username, pass_word, first_name, last_name, total_deposit, total_withdrawal, remaining_cash " +
                "FROM accounts WHERE user_id=?";

        Account account = new Account();
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setInt(1, Integer.valueOf(userId));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                account.setEmail(rs.getString("email"));
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("pass_word"));
                account.setTotalDeposit(rs.getDouble("total_deposit"));
                account.setTotalWithdrawal(rs.getDouble("total_withdrawal"));
                account.setRemainingCash(rs.getDouble("remaining_cash"));
            } else {
                return "{}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account.toJson();
    }
}




