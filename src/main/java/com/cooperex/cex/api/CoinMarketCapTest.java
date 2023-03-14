package com.cooperex.cex.api;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import com.google.gson.Gson;
import com.cooperex.cex.model.Asset;


public class CoinMarketCapTest {
    public CoinMarketCapTest() {
        System.out.println("CMC initailized");
    }

    public ArrayList<Asset> getCryptoPriceDict(String[] symbols) {

        // Step 1. Retrive crypto asset counts from user's portfolio
        String searchQuery = "";

        // Step 2. Parse symbols
        for (int i = 0; i < symbols.length; i++) {
            String symbol = symbols[i];
            searchQuery += (symbol + ",");
        }
        searchQuery = StringUtils.substring(searchQuery, 0, searchQuery.length() - 1);

        String APIKey = "06e9d858-d7c0-479d-93fc-672bc5937895";
        String uri = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest?symbol=" + searchQuery;
        ArrayList<Asset> assetArrayList = new ArrayList<Asset>();

        // Step 3. Price crypto prices Query CMC symbols
        try {
            System.out.print("CMC API called");
            HttpResponse<String> response = Unirest.get(uri)
                    .header("X-CMC_PRO_API_KEY", APIKey)
                    .asString();
            JSONObject obj = new JSONObject(response.getBody());

            for (int i = 0; i < symbols.length; i++) {
                Asset asset = new Asset();
                JSONArray jsonArray = obj.getJSONObject("data").getJSONArray(symbols[i]);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                String symbol = jsonObject.getString("symbol");
                String name = jsonObject.getString("name");
                double price = jsonObject.getJSONObject("quote").getJSONObject("USD").getDouble("price");
                double marketCap = jsonObject.getJSONObject("quote").getJSONObject("USD").getDouble("market_cap");

                asset.setAssetCategory("crypto");
                asset.setAssetSymbol(symbol);
                asset.setAssetName(name);
                asset.setAssetMarketCap(marketCap);
                asset.setAssetPrice(price);
                assetArrayList.add(asset);
            }
            return assetArrayList;
        } catch (UnirestException e) {
            return null;
        }
    }
}



