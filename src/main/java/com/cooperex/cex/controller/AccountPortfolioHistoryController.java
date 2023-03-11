package com.cooperex.cex.controller;
import org.springframework.web.bind.annotation.*;
import com.cooperex.cex.dao.AccountPortfolioHistoryDAO;

@RestController
@RequestMapping("/accounts")
public class AccountPortfolioHistoryController {
    private AccountPortfolioHistoryDAO accountPortfolioHistoryDAO;

    public AccountPortfolioHistoryController() {
        System.out.println("Account Portfolilo History Controller object has been initialized!");
        accountPortfolioHistoryDAO = new AccountPortfolioHistoryDAO();
    }

    @PostMapping(path="/{userId}/portfolio-history")
    public String postPortfolioValue(@PathVariable String userId){
        return accountPortfolioHistoryDAO.savePortfolioHistoryById(userId);
    }
    @GetMapping(path="/{userId}/portfolio-history")
    public String getPortfolio(@PathVariable String userId){
        return accountPortfolioHistoryDAO.getPortfolioHistoryById(userId);
    }
}

