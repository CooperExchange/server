package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import org.json.JSONObject;;
import com.google.gson.Gson;
import com.cooperex.cex.api.CoinMarketCap;
import com.cooperex.cex.api.YahooFinance;
import com.cooperex.cex.model.Asset;

public class AssetPriceDAO {
    private Connection connection;

    public AssetPriceDAO() {
        System.out.println("Asset Price DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
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
        return json;
    }

}