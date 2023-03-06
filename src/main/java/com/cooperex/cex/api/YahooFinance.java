package com.cooperex.cex.api;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

public class YahooFinance {

    public YahooFinance() {
        System.out.println("YahooFinance API initailized");
    }

    public String getStockPriceDict() {

        String query = null;

        if (assetCategory.equals("stock")) {
            String text_1 = "https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol=";
            String text_2 = assetSymbol;
            query = "https://yahoo-finance15.p.rapidapi.com/api/yahoo/qu/quote/AAPL,MSFT"
        }
        double assetPrice = 0;

        try {
            HttpResponse<String> response = Unirest.get(query)
                    .header("X-RapidAPI-Key", "5bd3cb0dc4msh1e1a7d40884cf61p1c068cjsn93ab111ba186")
                    .header("X-RapidAPI-Host", "yahoo-finance15.p.rapidapi.com")
                    .asString();

            JSONObject obj = new JSONObject(response.getBody());
//            assetPrice = Double.parseDouble(obj
//                        .getJSONObject("Global Quote")
//                        .getString("05. price"));
            System.out.println(obj);

            return "hello";
        } catch (UnirestException e) {
            return "hellow";
        }
    }

}

