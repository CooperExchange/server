package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cooperex.cex.api.CoinMarketCap;
import com.cooperex.cex.api.YahooFinance;

import com.cooperex.cex.dao.AssetPriceDAO;
import com.cooperex.cex.dao.AccountTradeDAO;
import com.cooperex.cex.dao.AccountPortfolioDAO;

import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		AccountPortfolioDAO accountPortfolioDAO = new AccountPortfolioDAO();
//		String res = accountPortfolioDAO.getPortfolioById("1");
//		double res_1 = accountPortfolioDAO.getPortfolioValueById("1");
//
//		System.out.println(res);
//		System.out.println(res_1);
	}

	@Scheduled(fixedRate = 15000)
	public void reportCurrentTime() {
		AssetPriceDAO assetPriceDAO = new AssetPriceDAO();
		assetPriceDAO.updateDatabaseAssetPrice();
	}
}

