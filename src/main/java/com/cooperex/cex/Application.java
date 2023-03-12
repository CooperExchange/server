package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cooperex.cex.dao.AccountProfileDAO;
import com.cooperex.cex.dao.AccountTradeHistoryDAO;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//AccountProfileDAO accountProfileDAO = new AccountProfileDAO();
		AccountTradeHistoryDAO accountTradeHistoryDAO = new AccountTradeHistoryDAO();
		String value = accountTradeHistoryDAO.getTradeHistoryById("1");
		System.out.println(value);
	}
}

