package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cooperex.cex.dao.AccountProfileDAO;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		AccountProfileDAO accountProfileDAO = new AccountProfileDAO();
		String value = accountProfileDAO.getAccountProfileById("4");
		System.out.println(value);
	}
}


