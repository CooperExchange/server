package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

public class AccountPortfolioDAO {
    private Connection connection;

    public AccountPortfolioDAO() {
        System.out.println("Account Portoflio DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    // Retrieve
    public String getPortfolioById(String userId) {
        // Step 1. Calculate Networth
        // Use user_id to get current asset counts for each
        String SQL = "SELECT asset_name, asset_symbol, asset_count " +
                "FROM portfolios WHERE user_id=?";
        Map<Integer, Map<String, String>> portfolioDict = new HashMap<>();

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet rs = statement.executeQuery();

            int count = 1;
            while (rs.next()) {
                Map<String, String> assetDict = new HashMap<>();
                String assetName = rs.getString("asset_name");
                String assetSymbol = rs.getString("asset_symbol");
                Double assetCount = rs.getDouble("asset_count");
                assetDict.put("assetName", String.valueOf(assetName));
                assetDict.put("assetSymbol", assetSymbol);
                assetDict.put("assetCount", String.valueOf(assetCount));
                portfolioDict.put(count, assetDict);
                count += 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Unexpected error has occured.";
        }

        Gson gson = new Gson();
        String json = gson.toJson(portfolioDict);
        return json;
    }

}

