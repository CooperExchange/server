package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.cooperex.cex.api.CoinMarketCap;
import com.cooperex.cex.api.YahooFinance;

public class AccountPortfolioDAO {
    private Connection connection;

    public AccountPortfolioDAO() {
        System.out.println("Account Portoflio DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public String getPortfolioById(String userId) {



        // Step 1. Retrieve assets held by the user
        String SQL = "SELECT asset_name, asset_symbol, asset_category, asset_count  " +
                "FROM portfolios WHERE user_id=?";

        Map<String, Map<String, String>> crpytoDict = new HashMap<>();
        Map<String, Map<String, String>> stockDict = new HashMap<>();
        String cryptoSymbols[] = {};
        String stockSymbols[] = {};
        ArrayList<String> cryptoSymbolList = new ArrayList<String>(Arrays.asList(cryptoSymbols));
        ArrayList<String> stockSymbolList = new ArrayList<String>(Arrays.asList(stockSymbols));

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet rs = statement.executeQuery();
            int count = 1;
            while (rs.next()) {
                Map<String, String> assetDict = new HashMap<>();
                String assetSymbol = rs.getString("asset_symbol");
                String assetCategory = rs.getString("asset_category");
                assetDict.put("assetName", rs.getString("asset_name"));
                assetDict.put("assetCategory", rs.getString("asset_category"));
                assetDict.put("assetCount", String.valueOf(rs.getDouble("asset_count")));

                // Step 2. Filter cryptos only
                if (assetCategory.equals("crypto")) {
                    cryptoSymbolList.add(assetSymbol);
                    crpytoDict.put(assetSymbol, assetDict);
                    count += 1;
                }

                if (assetCategory.equals("stock")) {
                    stockSymbolList.add(assetSymbol);
                    stockDict.put(assetSymbol, assetDict);
                    count += 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Unexpected error has occured.";
        }

        cryptoSymbols = cryptoSymbolList.toArray(cryptoSymbols);
        stockSymbols = stockSymbolList.toArray(stockSymbols);


        // Step 3. Get crypto prices from CMC
        CoinMarketCap coinMarketCap = new CoinMarketCap();
        Map<String, Double> cryptoPriceDict = coinMarketCap.getCryptoPriceDict(cryptoSymbols);
        crpytoDict.forEach((k, v) -> crpytoDict
                .get(k).put("assetPrice", String.valueOf(cryptoPriceDict.get(k))));


        // Step 4. Get stock prices from Yahoo
        YahooFinance yahooFinance = new YahooFinance();
        Map<String, Double> stockPriceDict = yahooFinance.getStockPriceDict(stockSymbols);
        System.out.println(stockPriceDict);
        stockDict.forEach((k, v) -> stockDict
                .get(k).put("assetPrice", String.valueOf(stockPriceDict.get(k))));

        Gson gson = new Gson();
        String stockJson = gson.toJson(stockDict);
        String cryptoJson = gson.toJson(crpytoDict);

        String json = stockJson + cryptoJson;
        System.out.println(json);
        return json;
    }
}