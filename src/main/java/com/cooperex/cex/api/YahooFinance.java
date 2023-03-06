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

public class YahooFinance {

    public YahooFinance() {
        System.out.println("YahooFinance API initailized");
    }

    public Map<String, Double> getStockPriceDict(String[] symbols) {

        String searchQuery = "";
        Map<String, Double> stockPriceDict = new HashMap();

        for (int i = 0; i < symbols.length; i++) {
            String symbol = symbols[i];
            searchQuery += (symbol + ",");
        }


        String uri = "https://yahoo-finance15.p.rapidapi.com/api/yahoo/qu/quote/" + searchQuery;

        try {
            HttpResponse<String> response = Unirest.get(uri)
                    .header("X-RapidAPI-Key", "0d817a0363mshb238e20933c8a93p16dd6fjsnc3470d352257")
                    .header("X-RapidAPI-Host", "yahoo-finance15.p.rapidapi.com")
                    .asString();

            JSONArray array = new JSONArray(response.getBody());

            for (int i = 0; i < symbols.length; i++) {
                double stockPrice = array.getJSONObject(i).getDouble("regularMarketPrice");
                String symbol = array.getJSONObject(i).getString("symbol");
                stockPriceDict.put(symbol, stockPrice);
            }

            return stockPriceDict;
        } catch (UnirestException e) {
            return null;
        }
    }


}

