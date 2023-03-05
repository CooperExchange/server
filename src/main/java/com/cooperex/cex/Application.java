package com.cooperex.cex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cooperex.cex.api.Finnhub;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Finnhub finnhub = new Finnhub();
		String[] cryptoSymbolList = {"BTC", "DOGE"};
		finnhub.getCryptoPriceList(cryptoSymbolList);
	}
}


