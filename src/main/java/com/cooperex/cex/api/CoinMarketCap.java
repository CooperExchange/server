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


public class CoinMarketCap {
    public CoinMarketCap() {
        System.out.println("CMC API initailized");
    }

    public Map<String, Double> getCryptoPriceDict(String[] symbols) {

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

        // Step 3. Price crypto prices Query CMC  symbols
        Map<String, Double> cryptoPriceDict = new HashMap();

        try {
            HttpResponse<String> response = Unirest.get(uri)
                    .header("X-CMC_PRO_API_KEY", APIKey)
                    .asString();
            JSONObject obj = new JSONObject(response.getBody());

            for (int i = 0; i < symbols.length; i++) {
                JSONArray jsonArray = obj.getJSONObject("data").getJSONArray(symbols[i]);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String symbol = jsonObject.getString("symbol");
                double cryptoPrice = jsonObject.getJSONObject("quote").getJSONObject("USD").getDouble("price");
                cryptoPriceDict.put(symbol, cryptoPrice);
            }
            return cryptoPriceDict;
        } catch (UnirestException e) {
            return null;
        }

    }



}



