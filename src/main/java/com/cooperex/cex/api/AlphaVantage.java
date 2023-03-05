package com.cooperex.cex.api;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

public class AlphaVantage {

    public AlphaVantage() {
        System.out.println("AlphaVantage initailized");
    }

    public String getQueryByAssetCategory(String assetCategory, String assetSymbol) {
        String query = null;
        if (assetCategory.equals("crypto")) {
            String text_1 = "https://alpha-vantage.p.rapidapi.com/query?from_currency=";
            String text_2 = assetSymbol;
            String text_3 = "&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";
            query = text_1 + text_2 + text_3;
        }

        if (assetCategory.equals("stock")) {
            String text_1 = "https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol=";
            String text_2 = assetSymbol;
            query = text_1 + text_2;
        }
        return query;
    }

    public Double getAssetPrice(String query, String assetCategory) {
        double assetPrice = 0;
        try {
            HttpResponse<String> response = Unirest.get(query)
                    .header("X-RapidAPI-Key", "5bd3cb0dc4msh1e1a7d40884cf61p1c068cjsn93ab111ba186")
                    .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                    .asString();

            JSONObject obj = new JSONObject(response.getBody());

            if (assetCategory.equals("crypto")) {
                assetPrice = Double.parseDouble(obj
                        .getJSONObject("Realtime Currency Exchange Rate")
                        .getString("5. Exchange Rate"));
            }

            if (assetCategory.equals("stock")) {
                assetPrice = Double.parseDouble(obj
                        .getJSONObject("Global Quote")
                        .getString("05. price"));
            }
            return assetPrice;
        } catch (UnirestException e) {
            return .0;
        }
    }

}

