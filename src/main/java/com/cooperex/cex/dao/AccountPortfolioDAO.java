package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;
import com.google.gson.Gson;
import com.cooperex.cex.model.Portfolio;

public class AccountPortfolioDAO {
    private Connection connection;

    public AccountPortfolioDAO() {
        System.out.println("Account Portoflio DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public String getPortfolioById(String userId) {

        // Modification - no need to use CMC and Yahoo Finance
        // Step 1. Retrieve assets held by the user
        String GET_PORTFOLIO = "SELECT asset_name, asset_symbol, asset_category, asset_count  " +
                "FROM portfolios WHERE user_id=?";

        // Step 1. Query the asset symbol
        String GET_ASSET_INFO = "SELECT asset_name, asset_category, asset_price " +
                "FROM assets WHERE asset_symbol=?";

        // Step 2. Query the asset count and the symbol from the portfolio

        ArrayList<Portfolio> portfolioArrayList = new ArrayList<Portfolio>();


        try (PreparedStatement statement = this.connection.prepareStatement(GET_PORTFOLIO);) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet rs = statement.executeQuery();
            int count = 1;
            while (rs.next()) {
                Portfolio portfolio = new Portfolio();
                portfolio.setAssetSymbol(rs.getString("asset_symbol"));
                portfolio.setAssetCount(rs.getDouble("asset_count"));
                portfolioArrayList.add(portfolio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Unexpected error has occured.";
        }

        for (int i = 0; i < portfolioArrayList.size(); i++) {
            Portfolio portoflio = portfolioArrayList.get(i);
            try (PreparedStatement statement = this.connection.prepareStatement(GET_ASSET_INFO);) {
                statement.setString(1, portoflio.getAssetSymbol());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    portoflio.setAssetPrice(rs.getDouble("asset_price"));
                    portoflio.setAssetCategory(rs.getString("asset_category"));
                    portoflio.setAssetName(rs.getString("asset_name"));
                    double assetTotalValue = portoflio.getAssetCount() * rs.getDouble("asset_price");
                    portoflio.setAssetTotalValue(assetTotalValue);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Step 3. Turn into an array of JSON
        Gson gson = new Gson();
        String json = gson.toJson(portfolioArrayList);
        System.out.println(json);
        return json;
    }

    public double getPortfolioValueById(String userId) {
        // Step 1. Determine the remaining cash
        String GET_REMAINING_CASH = "SELECT remaining_cash " +
                "FROM accounts WHERE user_id=?";
        double portfolio_total_value = 0;

        try (PreparedStatement statement = this.connection.prepareStatement(GET_REMAINING_CASH);) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                portfolio_total_value += rs.getDouble("remaining_cash");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Step 2. Determine the valuation of the stocks/cryptos
//        AccountPortfolioDAO accountPortfolioDAO = new AccountPortfolioDAO();
        String json = getPortfolioById(userId);
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            double assetTotalValue = jsonObject.getDouble("assetTotalValue");
            portfolio_total_value += assetTotalValue;

        }

        return portfolio_total_value;
    }
}