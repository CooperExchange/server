

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
        System.out.println("AccountDAO object has been initialized with successful DB connection!!!!!!!!");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        this.connection = connection;
    }

    // To-do: Implement error handling if there is no user
    public String addBalanceById(String userId, Double amount) {
        System.out.println("Deposit func called");
        String SQL = "UPDATE accounts SET total_deposit " +
                "= total_deposit + ?,  remaining_cash = remaining_cash + ? where user_id = ?;";

        int userIdInt = Integer.parseInt(userId);

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setDouble(1, amount);
            statement.setDouble(2, amount);
            statement.setInt(3, userIdInt);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User balance has been increased by " + amount.toString();
    }

    // To-do: Prevent withdrawal if total_deposit > total_withdrawal
    public String withdrawBalanceById(String userId, Double amount) {

        String SQL = "UPDATE accounts SET total_withdrawal " +
                "= total_withdrawal + ?, remaining_cash = remaining_cash - ? where user_id = ?";

        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setDouble(1, amount);
            statement.setDouble(2, amount);
            statement.setInt(3, userIdInt);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User withdrawal has been increased by " + amount.toString();
    }

    public String tradeAssetBySymbol(String userId, Trade trade) {
        System.out.println("User requests asset " + trade.tradeType);

        // Parse price API result
        String text_1 = "https://alpha-vantage.p.rapidapi.com/query?from_currency=";
        String assetSymbol = trade.assetSymbol;
        String text_2 = assetSymbol.replace("\"", "");
        String text_3 = "&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";
        String query = text_1 + text_2 + text_3;

        String assetName = null;
        String assetPrice = null;

        // Parse price API result
        try {
            HttpResponse<String> response = Unirest.get(query)
                    .header("X-RapidAPI-Key", "5bd3cb0dc4msh1e1a7d40884cf61p1c068cjsn93ab111ba186")
                    .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                    .asString();

            JSONObject obj = new JSONObject(response.getBody());
            assetName = obj.getJSONObject("Realtime Currency Exchange Rate").getString("2. From_Currency Name");
            assetPrice = obj.getJSONObject("Realtime Currency Exchange Rate").getString("5. Exchange Rate");

        } catch (UnirestException e) {
            return null;
        }

        // Convert String to numbers
        int userIdInt = Integer.parseInt(userId);
        double assetPriceDouble = Double.parseDouble(assetPrice);
        double assetCountDouble = Double.parseDouble(trade.assetCount);
        String tradeType = trade.tradeType.replace("\"", "");

        String SQL = null;
        if (trade.tradeType == "Buy") {
            SQL = "INSERT INTO trades" +
                    "  (trade_type, user_id, asset_symbol, asset_name, asset_price, asset_count) VALUES " +
                    " (?, ?, ?, ?, ?, ?)";
//            SQL = "INSERT INTO trades" +
//                    "  (trade_type, user_id, asset_symbol, asset_name, asset_price, asset_count) VALUES " +
//                    " (?, ?, ?, ?, ?, ?);" +
//                    " INSERT INTO portfolios" +
//                    "  (asset_symbol, user_id, asset_name, asset_count) VALUES " +
//                    " (?, ?, ?, ?);";

// To be implemented: If there is no portoflio then create a row, if there is asset, then do not.
// "UPDATE accounts SET current_bal = current_bal - (? * ?) WHERE user_id = ?;";

        }
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL);
            statement.setString(1, trade.tradeType);
            statement.setInt(2, userIdInt);
            statement.setString(3, assetSymbol);
            statement.setString(4, assetName);
            statement.setDouble(5, assetPriceDouble);
            statement.setDouble(6, assetCountDouble);
//            statement.setString(7, assetSymbol);
//            statement.setInt(8, userIdInt);
//            statement.setString(9, assetName);
//            statement.setDouble(10, assetCountDouble);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "User request asset trading";
    }
}


