package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import com.cooperex.cex.dao.AccountPortfolioHistoryDAO;
import com.cooperex.cex.dao.AccountTradeHistoryDAO;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		AccountPortfolioHistoryDAO accountPortfolioHistoryDAO = new AccountPortfolioHistoryDAO();
		AccountTradeHistoryDAO accountTradeHistoryDAO = new AccountTradeHistoryDAO();
		accountPortfolioHistoryDAO.savePortfolioHistoryById("1");
		accountPortfolioHistoryDAO.getPortfolioHistoryById("1");
		accountTradeHistoryDAO.getTradeHistoryById("1");
	}
}


