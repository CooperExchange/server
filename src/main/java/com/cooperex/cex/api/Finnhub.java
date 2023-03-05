package com.cooperex.cex.api;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import com.google.gson.Gson;
public class Finnhub {

    public Finnhub() {
        System.out.println("Finnhub initailized");
    }

    public String getCryptoPriceList(String[] symbolList) {

        // Step 1. Retrive crypto asset counts from user's portfolio
        String searchQuery = "";
        // Step 2. Parse symbols
        for (int i = 0; i < symbolList.length; i++) {
            String symbol = symbolList[i];
            searchQuery += (symbol + ",");
        }
        searchQuery = StringUtils.substring(searchQuery, 0, searchQuery.length() - 1);

        String APIKey = "06e9d858-d7c0-479d-93fc-672bc5937895";
        String uri = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest?symbol=" + searchQuery;

        // Step 3. Price crypto prices Query CMC  symbols
        Map<String, double> cryptoPriceDict = new Hashtable();

        try {
            HttpResponse<String> response = Unirest.get(uri)
                    .header("X-CMC_PRO_API_KEY", APIKey)
                    .asString();
            JSONObject obj = new JSONObject(response.getBody());
            // Parse based on the symbol list

            for (int i = 0; i < symbolList.length; i++) {
                JSONArray jsonArray = obj.getJSONObject("data").getJSONArray(symbolList[i]);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String symbol = jsonObject.getString("symbol"); // works
                System.out.println(symbol);
                double cryptoPrice = jsonObject.getJSONObject("quote").getJSONObject("USD").getDouble("price");
                System.out.println(cryptoPrice);
                // Export as a dictionary {"BTC": 12313, "DOGE": 12313}
                cryptoPriceDict.put(symbol, cryptoPrice);
            }

            System.out.println(cryptoPriceDict);
            
            return "Success";
        } catch (UnirestException e) {
            return "Failed";
        }

    }



}



