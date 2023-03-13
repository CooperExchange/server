package com.cooperex.cex.controller;
import org.springframework.web.bind.annotation.*;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/accounts")
public class AccountPortfolioController {
    private AccountPortfolioDAO accountPortfolioDAO;

    public AccountPortfolioController() {
        System.out.println("Account Portfolilo Controller object has been initialized!");
        accountPortfolioDAO= new AccountPortfolioDAO();
    }

    @CrossOrigin
    @GetMapping(path="/{userId}/portfolio")
    public ResponseEntity<String> savePortfolioValueById(@PathVariable String userId) {
        String portfolio = accountPortfolioDAO.getPortfolioById(userId);
        if (portfolio.equals("{}")) { return new ResponseEntity(HttpStatus.NOT_FOUND) ;}
        return ResponseEntity.ok(portfolio);
    }

    @CrossOrigin
    @GetMapping(path="/{userId}/portfolio-value")
    public ResponseEntity<Double> getPortfolioValue(@PathVariable String userId) {
        Double portfolioValue = accountPortfolioDAO.getPortfolioValueById(userId);
        if (portfolioValue.equals(0)) { return new ResponseEntity(HttpStatus.NOT_FOUND) ;}
        return ResponseEntity.ok(portfolioValue);
    }
}


