package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cooperex.cex.api.CoinMarketCap;
import com.cooperex.cex.api.YahooFinance;
import com.cooperex.cex.dao.AssetPriceDAO;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		AssetPriceDAO assetPriceDAO = new AssetPriceDAO();
		assetPriceDAO.updateDatabaseAssetPrice();
		assetPriceDAO.getAssetPrice();

		// Here implement the details

	}
}

