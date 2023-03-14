package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cooperex.cex.dao.AssetPriceDAO;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import com.cooperex.cex.scheduler.PortfolioHistoryScheduler;
import com.cooperex.cex.scheduler.AssetPriceScheduler;

import org.springframework.scheduling.annotation.Scheduled;
@SpringBootApplication
@EnableScheduling
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		AccountPortfolioDAO accountPortfolioDAO = new AccountPortfolioDAO();
	}

	@Scheduled(fixedRate = 300000, initialDelay = 1000)
	public void updateDatabaseAssetPrice() {
		AssetPriceScheduler assetPriceScheduler = new AssetPriceScheduler();
		assetPriceScheduler.updateDatabaseAssetPrice();
	}

	// 6 hours = 21600 seconds
	@Scheduled(fixedRate = 21600000)
	public void saveUserPortfolioValue() {
		PortfolioHistoryScheduler portfolioHistoryScheduler = new PortfolioHistoryScheduler();
		portfolioHistoryScheduler.savePortfolioValue();
	}

}

