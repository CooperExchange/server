package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import com.cooperex.cex.dao.AccountPortfolioHistoryDAO;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		AccountPortfolioHistoryDAO accountPortfolioHistoryDAO = new AccountPortfolioHistoryDAO();
		accountPortfolioHistoryDAO.savePortfolioHistoryById("1");

	}
}


