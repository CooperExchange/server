package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;

import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.google.gson.Gson;
public class AccountPortfolioHistoryDAO {
    private Connection connection;

    public AccountPortfolioHistoryDAO() {
        System.out.println("Account Portoflio DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public Boolean savePortfolioValueById(String userId) {

        // Step 1. Retrieve the value of the portfolio net-worth
        AccountPortfolioDAO accountPortfolioDAO = new AccountPortfolioDAO();
        double portfolio_value = accountPortfolioDAO.getPortfolioValueById(userId);

        // Step 2. Check whether the user exists in the database
        String SQL1 = "SELECT " +
                "FROM accounts WHERE user_id=?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL1);) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet rs = statement.executeQuery();
            if (rs.next() == false) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String SQL2 = "INSERT INTO portfolio_history" +
                "  (user_id, portfolio_value) VALUES " +
                " (?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL2)) {
            statement.setInt(1, Integer.parseInt(userId));
            statement.setDouble(2, portfolio_value);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getPortfolioHistoryById(String userId) {

        Map<String, Double> portfolioHistoryDict = new HashMap<>();
        // Step 1. Retrieve all the portfolio data.
        String SQL = "SELECT portfolio_value, date_balance " +
                "FROM portfolio_history WHERE user_id=?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Double portfolio_value = rs.getDouble("portfolio_value");
                String date = rs.getString("date_balance");
                portfolioHistoryDict.put(date, portfolio_value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(portfolioHistoryDict);
        return json;
    }
}