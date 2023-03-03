
package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;
import com.cooperex.cex.model.AccountTrade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountTradeDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;
    private Connection connection;

    public AccountTradeDAO() {
        System.out.println("AccountTradeDAO object has been initialized with successful DB connection!");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        this.connection = connection;
    }

    public String tradeAssetBySymbol(String userId, AccountTrade accountTrade) {
        System.out.println("User requests asset " + accountTrade.tradeType);
        String assetCategory = accountTrade.assetCategory.replace("\"", "");
        String assetSymbol = accountTrade.assetSymbol.replace("\"", "");
        String assetName = accountTrade.assetName.replace("\"", "");
        String tradeType = accountTrade.tradeType.replace("\"", "");
        double assetCount = Double.parseDouble(accountTrade.assetCount);
        double assetPrice = 0;
        double remainingCash = 0;
        double assetTotalValue = 0;
        String query = null;

        // Step 1. Determine API query based on asset type
        if (assetCategory.equals("crypto")) {
            String text_1 = "https://alpha-vantage.p.rapidapi.com/query?from_currency=";
            String text_2 = assetSymbol;
            String text_3 = "&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";
            query = text_1 + text_2 + text_3;
        }

        if (assetCategory.equals("stock")) {
            String text_1 = "https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol=";
            String text_2 = assetSymbol;
            query = text_1 + text_2;
        }

        // Step 2. Request Alpha Vantage API and parse the price
        try {
            HttpResponse<String> response = Unirest.get(query)
                    .header("X-RapidAPI-Key", "5bd3cb0dc4msh1e1a7d40884cf61p1c068cjsn93ab111ba186")
                    .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                    .asString();

            JSONObject obj = new JSONObject(response.getBody());

            if (assetCategory.equals("crypto")) {
                assetPrice = Double.parseDouble(obj
                        .getJSONObject("Realtime Currency Exchange Rate")
                        .getString("5. Exchange Rate"));
            }

            if (assetCategory.equals("stock")) {
                assetPrice = Double.parseDouble(obj
                        .getJSONObject("Global Quote")
                        .getString("05. price"));
            }
            // Determine the total value of the user's trade
            assetTotalValue = assetCount * assetPrice;

        } catch (UnirestException e) {
            return "Unexpected error has occured.";
        }

        // Step 3. Determine whether user can buy or sell
        // For "buy", check whether user has enough remaining balance
        if (tradeType.equals("buy")) {
            String GET_REMAINING_CASH = "SELECT remaining_cash " +
                    "FROM accounts WHERE user_id=?";

            try (PreparedStatement statement = this.connection.prepareStatement(GET_REMAINING_CASH);) {
                statement.setInt(1, Integer.parseInt(userId));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    remainingCash = rs.getDouble("remaining_cash");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("The total value of the trade is "+ assetTotalValue);

            if (remainingCash < assetTotalValue) {
                return "Failed to buy. User remaining cash is NOT enough";
            }
        }

        // For "sell", check whether user has enough assets
        if (tradeType.equals("sell")) {
            String GET_ASSET_COUNT = "SELECT asset_count " +
                    "FROM portfolios WHERE user_id=? and asset_symbol=?";
            double currentAssetCount = 0;

            try (PreparedStatement statement = this.connection.prepareStatement(GET_ASSET_COUNT);) {
                statement.setInt(1, Integer.parseInt(userId));
                statement.setString(2, assetSymbol);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    currentAssetCount = rs.getDouble("asset_count");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (assetCount > currentAssetCount) {
                return "Failed to sell. User does not have enough assets to sell";
            }
        }

        // Step 4. Prepare SQL statements
        String SQL_1 = null;
        String SQL_2 = null;
        String SQL_3 = null;
        String SQL_4 = null;
        String SQL_5 = null;

        SQL_1 = "INSERT INTO trades" +
                "  (trade_type, user_id, asset_symbol, asset_name, asset_price, asset_count) VALUES " +
                " (?, ?, ?, ?, ?, ?)";

        SQL_2 = "SELECT portfolio_id " +
                "FROM portfolios WHERE user_id=? and asset_name=?";


        SQL_4 = "INSERT INTO portfolios" +
                "  (asset_symbol, user_id, asset_name, asset_count) VALUES " +
                " (?, ?, ?, ?)";

        if (tradeType.equals("buy")) {
            SQL_3 = "UPDATE portfolios SET " +
                    "asset_count = asset_count + ? " +
                    "where portfolio_id = ?;";

            SQL_5 = "UPDATE accounts SET " +
                    "remaining_cash = remaining_cash - ? " +
                    "where user_id = ?;";


        } else {
            SQL_3 = "UPDATE portfolios SET " +
                    "asset_count = asset_count - ? " +
                    "where portfolio_id = ?;";

            SQL_5 = "UPDATE accounts SET " +
                    "remaining_cash = remaining_cash + ? " +
                    "where user_id = ?;";
        }

        // Also update the remaining balance
        // For "sell", increase the remaining balance
        // For "buy", decrease the reamining balance

        // Step 5. Execute SQL commands
        try {
            PreparedStatement statement_1 = this.connection.prepareStatement(SQL_1);
            PreparedStatement statement_2 = this.connection.prepareStatement(SQL_2);
            PreparedStatement statement_3 = this.connection.prepareStatement(SQL_3);
            PreparedStatement statement_4 = this.connection.prepareStatement(SQL_4);
            PreparedStatement statement_5 = this.connection.prepareStatement(SQL_5);

            // Insert a trade row
            statement_1.setString(1, tradeType);
            statement_1.setInt(2, Integer.parseInt(userId));
            statement_1.setString(3, assetSymbol);
            statement_1.setString(4, assetName);
            statement_1.setDouble(5, assetPrice);
            statement_1.setDouble(6, assetCount);
            statement_1.executeUpdate();
            statement_1.close();

            // Get portfolio_id
            statement_2.setInt(1, Integer.parseInt(userId));
            statement_2.setString(2, assetName);
            ResultSet rs = statement_2.executeQuery();
            statement_2.close();

            if (rs.next()) {
                // Increase asset_count if portfolio_id exists
                String portfolios_id = Long.toString(rs.getLong("portfolio_id"));
                System.out.println("portfolio_id: " + portfolios_id);
                System.out.println("portfoliio_id is found. Update asset_count");
                statement_3.setDouble(1, assetCount);
                statement_3.setInt(2, Integer.parseInt(portfolios_id));
                statement_3.executeUpdate();
                statement_3.close();

            } else {
                // Insert a row if portfolio_id does NOT exists
                System.out.println("portfoliio_id is NOT found. Insert a row");
                statement_4.setString(1, assetSymbol);
                statement_4.setInt(2, Integer.parseInt(userId));
                statement_4.setString(3, assetName);
                statement_4.setDouble(4, assetCount);
                statement_4.executeUpdate();
                statement_4.close();
            }

            // Update remaining cash in the accounts table
            statement_5.setDouble(1, assetTotalValue);
            statement_5.setInt(2, Integer.parseInt(userId));
            statement_5.executeUpdate();
            statement_5.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User request asset trading";
    }
}




