package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import org.json.JSONObject;;
import com.google.gson.Gson;
import com.cooperex.cex.api.CoinMarketCapTest;
import com.cooperex.cex.api.YahooFinanceTest;
import com.cooperex.cex.model.Asset;

public class AssetPriceDAO {
    private Connection connection;

    public AssetPriceDAO() {
        System.out.println("Asset Price DAO object has been initialized with successful DB connection");
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
        CoinMarketCapTest coinMarketCapTest = new CoinMarketCapTest();
        YahooFinanceTest yahooFinanceTest = new YahooFinanceTest();
        ArrayList<Asset> cryptoAssetArrayList = coinMarketCapTest.getCryptoPriceDict(cryptoSymbols);
        ArrayList<Asset> stockAssetArrayList = yahooFinanceTest.getStockPriceDict(stockSymbols);

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

    public String getAssetPrice() {
        // Step 1. Retrieve all assets tracked in the database 
        String SQL = "SELECT asset_symbol, asset_name, asset_category, asset_category, asset_price, asset_market_cap, date " +
                "FROM assets";

        ArrayList<Asset> assetArrayList = new ArrayList<Asset>();
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {;
                Asset asset = new Asset();
                asset.setAssetSymbol(rs.getString("asset_symbol"));
                asset.setAssetName(rs.getString("asset_name"));
                asset.setAssetCategory(rs.getString("asset_category"));
                asset.setAssetPrice(rs.getDouble("asset_price"));
                asset.setAssetMarketCap(rs.getDouble("asset_market_cap"));
                assetArrayList.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        String json = new Gson().toJson(assetArrayList);
        System.out.println(json);
        return "Hello world";
    }

}