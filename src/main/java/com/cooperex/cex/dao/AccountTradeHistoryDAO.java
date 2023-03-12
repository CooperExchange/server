package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import com.cooperex.cex.model.Trade;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.json.JSONObject;
import com.google.gson.Gson;

public class AccountTradeHistoryDAO {
    private Connection connection;

    public AccountTradeHistoryDAO() {
        System.out.println("Account Trade History DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public String getTradeHistoryById(String userId) {

        // Step 1. Retrieve all the portfolio data.
        String SQL = "SELECT trade_type, trade_date, asset_symbol, asset_name, asset_category, asset_price, asset_count, total_price " +
                "FROM trades WHERE user_id=?";

        JSONObject tradeJson = new JSONObject();

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {;
                Trade trade = new Trade();
                trade.setTradeType(rs.getString("trade_type"));
                trade.setAssetSymbol(rs.getString("asset_symbol"));
                trade.setAssetName(rs.getString("asset_name"));
                trade.setAssetCategory(rs.getString("asset_category"));
                trade.setAssetCount(rs.getDouble("asset_count"));
                trade.setAssetPrice(rs.getDouble("asset_price"));
                trade.setTotalPrice(rs.getDouble("total_price"));
                String tradeDate = rs.getString("trade_date");
                String json = trade.toJson();
                JSONObject jsonObject = new JSONObject(json);
                tradeJson.put(tradeDate, jsonObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return tradeJson.toString();
    }
}