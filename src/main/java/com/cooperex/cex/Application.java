
package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;


@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);




//		// Parse price API result
//		String text_1 = "https://alpha-vantage.p.rapidapi.com/query?from_currency=";
//		String assetSymbol = trade.assetSymbol;
//		String text_2 = assetSymbol.replace("\"", "");
//		String text_3 = "&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";
//		String query = text_1 + text_2 + text_3;
//
//		String assetName = trade.assetName;
//		String assetPrice = null;
//
//		// Parse price API result
//		try {
//			HttpResponse<String> response = Unirest.get(query)
//					.header("X-RapidAPI-Key", "5bd3cb0dc4msh1e1a7d40884cf61p1c068cjsn93ab111ba186")
//					.header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
//					.asString();
//
//			JSONObject obj = new JSONObject(response.getBody());
//			assetPrice = obj.getJSONObject("Realtime Currency Exchange Rate").getString("5. Exchange Rate");
//
//		} catch (UnirestException e) {
//			System.out.println(e);
//		}
//
//
//		try {
//			HttpResponse<String> response = Unirest.get("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol=TSLA")
//					.header("X-RapidAPI-Key", "5bd3cb0dc4msh1e1a7d40884cf61p1c068cjsn93ab111ba186")
//					.header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
//					.asString();
//
//			JSONObject obj = new JSONObject(response.getBody());
//
//			System.out.println(obj);
//			String assetPrice = obj.getJSONObject("Global Quote").getString("05. price");
//			System.out.println(assetPrice);
//
//		} catch (UnirestException e) {
//			System.out.println(e);
//		}
//

//		// Parse price API result
//		String text_1 = "https://alpha-vantage.p.rapidapi.com/query?from_currency=";
//		String assetSymbol = "BTC";
//		String text_2 = assetSymbol.replace("\"", "");
//		String text_3 = "&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";
//		String query = text_1 + text_2 + text_3;
//
//		String assetName = null;
//		String assetPrice = null;
//
//		// Parse price API result
//		try {
//			HttpResponse<String> response = Unirest.get(query)
//					.header("X-RapidAPI-Key", "5bd3cb0dc4msh1e1a7d40884cf61p1c068cjsn93ab111ba186")
//					.header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
//					.asString();
//
//			JSONObject obj = new JSONObject(response.getBody());
//
//			System.out.println(obj);
//
//			assetName = obj.getJSONObject("Realtime Currency Exchange Rate").getString("2. From_Currency Name");
//			assetPrice = obj.getJSONObject("Realtime Currency Exchange Rate").getString("5. Exchange Rate");
//
//		} catch (UnirestException e) {
//			System.out.println(e);
//		}


	}

}


