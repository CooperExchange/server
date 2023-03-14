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

public class YahooFinance {

    public YahooFinance() {
        System.out.println("YahooFinance API initailized");
    }

    public ArrayList<Asset> getStockPriceDict(String[] symbols) {

        String searchQuery = "";
        Map<String, Double> stockPriceDict = new HashMap();

        for (int i = 0; i < symbols.length; i++) {
            String symbol = symbols[i];
            searchQuery += (symbol + ",");
        }

        ArrayList<Asset> assetArrayList = new ArrayList<Asset>();
        String uri = "https://yahoo-finance15.p.rapidapi.com/api/yahoo/qu/quote/" + searchQuery;

        try {
            System.out.print("Yahoo Finance API called");
            HttpResponse<String> response = Unirest.get(uri)
                    .header("X-RapidAPI-Key", "0d817a0363mshb238e20933c8a93p16dd6fjsnc3470d352257")
                    .header("X-RapidAPI-Host", "yahoo-finance15.p.rapidapi.com")
                    .asString();

            JSONArray array = new JSONArray(response.getBody());

            for (int i = 0; i < symbols.length; i++) {
                Asset asset = new Asset();

                double price = array.getJSONObject(i).getDouble("regularMarketPrice");
                double marketCap = array.getJSONObject(i).getDouble("marketCap");
                String symbol = array.getJSONObject(i).getString("symbol");
                String name = array.getJSONObject(i).getString("shortName");

                asset.setAssetCategory("stock");
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

