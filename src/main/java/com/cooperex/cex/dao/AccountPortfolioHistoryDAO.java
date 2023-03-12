package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.json.JSONObject;
import com.google.gson.Gson;
public class AccountPortfolioHistoryDAO {
    private Connection connection;

    public AccountPortfolioHistoryDAO() {
        System.out.println("Account Portoflio DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public String savePortfolioHistoryById(String userId) {
        System.out.println("We are going to save portfolio history");

        // Step 1. Retrieve the value of the portfolio net-worth
        AccountPortfolioDAO accountPortfolioDAO = new AccountPortfolioDAO();
        double portfolio_value = accountPortfolioDAO.getPortfolioValueById(userId);

        String SQL = "INSERT INTO portfolio_history" +
                "  (user_id, portfolio_value) VALUES " +
                " (?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, Integer.parseInt(userId));
            statement.setDouble(2, portfolio_value);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Portfolio history saved";
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
        System.out.println(json);

        // Step 2. Return as a JSON file.
        return json;
    }
}



// Bug 1: Must check whether the user exists in the first place