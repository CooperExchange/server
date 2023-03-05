
package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cooperex.cex.dao.AccountPortfolioDAO;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		AccountPortfolioDAO accountPortfolioDAO = new AccountPortfolioDAO();
		accountPortfolioDAO.getPortfolioValueById("1");
	}
}


