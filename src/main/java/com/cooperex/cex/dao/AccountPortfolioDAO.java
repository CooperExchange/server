package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.cooperex.cex.api.AlphaVantage;

public class AccountPortfolioDAO {
    private Connection connection;

    public AccountPortfolioDAO() {
        System.out.println("Account Portoflio DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    // Retrieve
    public
        String getPortfolioById(String userId) {
            // Step 1. Calculate Networth
            // Use user_id to get current asset counts for each
            String SQL = "SELECT asset_name, asset_symbol, asset_category, asset_count  " +
                    "FROM portfolios WHERE user_id=?";
            Map<Integer, Map<String, String>> portfolioDict = new HashMap<>();

            try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
                statement.setInt(1, Integer.parseInt(userId));
                ResultSet rs = statement.executeQuery();

                int count = 1;
                while (rs.next()) {
                    Map<String, String> assetDict = new HashMap<>();
                    assetDict.put("assetName", rs.getString("asset_name"));
                    assetDict.put("assetSymbol", rs.getString("asset_symbol"));
                    assetDict.put("assetCategory", rs.getString("asset_category"));
                    assetDict.put("assetCount", String.valueOf(rs.getDouble("asset_count")));
                    portfolioDict.put(count, assetDict);
                    count += 1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "Unexpected error has occurred.";
            }
        Gson gson = new Gson();
        String json = gson.toJson(portfolioDict);
        return json;
    }

    public double getPortfolioValueById(String userId) {
        // Get user's all assets held
        String json = getPortfolioById(userId);
        JSONObject jsonObject = new JSONObject(json);

        double portfolioValue = 0;
        Iterator<String> keys = jsonObject.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                // do something with jsonObject here
                System.out.println(key);
                String assetSymbol = jsonObject.getJSONObject(key).getString("assetSymbol");
                String assetCategory = jsonObject.getJSONObject(key).getString("assetCategory");
                double assetCount = Double.parseDouble(jsonObject.getJSONObject(key).getString("assetCount"));

                // Use Alpha Vantage get the price of each asset owned
                AlphaVantage alphaVantage = new AlphaVantage();
                String query = alphaVantage.getQueryByAssetCategory(assetCategory, assetSymbol);
                double assetPrice = alphaVantage.getAssetPrice(query, assetCategory);
                double totalPrice = assetCount * assetPrice;
                portfolioValue += (assetCount * assetPrice);
            }
        }

        System.out.println("Current asset portfolio value ($): " + String.valueOf(portfolioValue));
        return portfolioValue;
    }

}

