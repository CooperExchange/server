package com.cooperex.cex.scheduler;

import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.api.CoinMarketCap;
import com.cooperex.cex.api.YahooFinance;
import com.cooperex.cex.model.Asset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssetPriceScheduler {
    private Connection connection;

    public AssetPriceScheduler() {
        System.out.println("Asset Price Schedular object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public boolean updateDatabaseAssetPrice() {
        // Step 1. Get a list of stocks
        Map<String, Map<String, String>> crpytoDict = new HashMap<>();
        Map<String, Map<String, String>> stockDict = new HashMap<>();
        String cryptoSymbols[] = {"BTC", "ETH", "DOGE", "BCH", "EOS", "XRP", "BNB", "MATIC", "ADA", "DOT", "AVAX", "SOL"};
        String stockSymbols[] = {"TSLA", "AMZN", "AAPL", "MSFT", "NFLX", "HOOD"};

        // Step 3. Get crypto prices from CoinMarketCap
        CoinMarketCap coinMarketCap = new CoinMarketCap();
        YahooFinance yahooFinance = new YahooFinance();
        ArrayList<Asset> cryptoAssetArrayList = coinMarketCap.getCryptoPriceDict(cryptoSymbols);
        ArrayList<Asset> stockAssetArrayList = yahooFinance.getStockPriceDict(stockSymbols);

        // Delete the rows and re-insert
        String SQL_1 = "DELETE FROM assets";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL_1);) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Save to the local database
        String SQL_2 = "INSERT INTO assets" +
                "  (asset_symbol, asset_name, asset_category, asset_price, asset_market_cap) VALUES " +
                " (?, ?, ?, ?, ?)";
        for (int i = 0; i < cryptoAssetArrayList.size(); i++) {
            Asset asset = cryptoAssetArrayList.get(i);
            try (PreparedStatement statement = this.connection.prepareStatement(SQL_2);) {
                statement.setString(1, asset.getAssetSymbol());
                statement.setString(2, asset.getAssetName());
                statement.setString(3, asset.getAssetCategory());
                statement.setDouble(4, asset.getAssetPrice());
                statement.setDouble(5, asset.getAssetMarketCap());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        for (int i = 0; i < stockAssetArrayList.size(); i++) {
            Asset asset = stockAssetArrayList.get(i);
            try (PreparedStatement statement = this.connection.prepareStatement(SQL_2);) {
                statement.setString(1, asset.getAssetSymbol());
                statement.setString(2, asset.getAssetName());
                statement.setString(3, asset.getAssetCategory());
                statement.setDouble(4, asset.getAssetPrice());
                statement.setDouble(5, asset.getAssetMarketCap());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
