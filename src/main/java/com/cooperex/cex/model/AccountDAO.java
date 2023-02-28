

package com.cooperex.cex.model;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

public class AccountDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;
    private Connection connection;

    public AccountDAO() {
        System.out.println("AccountDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        // To be refactored - dev purposes
        this.connection = connection;
    }

    // To-do: Implement error handling if there is no user.
    public String addBalanceById(String userId, Double amount) {
        String SQL = "update accounts set total_deposit = total_deposit + ? where user_id = ?;";
        int userIdInt = Integer.parseInt(userId);

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setDouble(1, amount);
            statement.setInt(2, userIdInt);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User balance has been increased by " + amount.toString();
    }

    // To-do: Prevent withdrawing if total_deposit > total_withdrawal
    public String withdrawBalanceById(String userId, Double amount) {
        String SQL = "update accounts set total_withdrawal = total_withdrawal + ? where user_id = ?;";
        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setDouble(1, amount);
            statement.setInt(2, userIdInt);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User withdrawal has been increased by " + amount.toString();
    }

    public String tradeAssetBySymbol(String userId, Trade trade) {
        System.out.println("User requests asset " + trade.tradeType);
        // To-Do: There is an issue with "BTC" parsing from the String asset.
        // It is only querying BTC for now.
        // I found the bug. I will fix it soon - Bob.
        String text_1 = "https://alpha-vantage.p.rapidapi.com/query?from_currency=";
        String text_2 = "BTC";
        String text_3 = "&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";
        String query = text_1 + text_2 + text_3;

        String assetSymbol = null;
        String assetName = null;
        String assetPrice = null;

        try {
            HttpResponse<String> response = Unirest.get(query)
                    .header("X-RapidAPI-Key", "5bd3cb0dc4msh1e1a7d40884cf61p1c068cjsn93ab111ba186")
                    .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                    .asString();

            JSONObject obj = new JSONObject(response.getBody());
            assetSymbol = obj.getJSONObject("Realtime Currency Exchange Rate").getString("1. From_Currency Code");
            assetName = obj.getJSONObject("Realtime Currency Exchange Rate").getString("2. From_Currency Name");
            assetPrice = obj.getJSONObject("Realtime Currency Exchange Rate").getString("5. Exchange Rate");

        } catch (UnirestException e) {
            return null;
        }

        // Convert String to numbers
        int userIdInt = Integer.parseInt(userId);
        Double assetPriceDouble = Double.parseDouble(assetPrice);
        Double assetCountDoucle = Double.parseDouble(trade.assetCount);

        String SQL = "INSERT INTO trades" +
                "  (trade_type, user_id, asset_symbol, asset_name, asset_price, asset_count) VALUES " +
                " (?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL);
            statement.setString(1, trade.tradeType);
            statement.setInt(2, userIdInt);
            statement.setString(3, assetSymbol);
            statement.setString(4, assetName);
            statement.setDouble(5, assetPriceDouble);
            statement.setDouble(6, assetCountDoucle);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "User request asset trading";
    }
}


