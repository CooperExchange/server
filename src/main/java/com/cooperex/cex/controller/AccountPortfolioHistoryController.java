package com.cooperex.cex.controller;
import org.springframework.web.bind.annotation.*;
import com.cooperex.cex.dao.AccountPortfolioHistoryDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/accounts")
public class AccountPortfolioHistoryController {
    private AccountPortfolioHistoryDAO accountPortfolioHistoryDAO;

    public AccountPortfolioHistoryController() {
        System.out.println("Account Portfolilo History Controller object has been initialized!");
        accountPortfolioHistoryDAO = new AccountPortfolioHistoryDAO();
    }

    @PostMapping(path="/{userId}/portfolio-history")
    public ResponseEntity<String> savePortfolioValue(@PathVariable String userId){
        if (accountPortfolioHistoryDAO.savePortfolioValueById(userId)) {
            return ResponseEntity.ok("Current portfolio value has been saved.");
        }
        return ResponseEntity
                .badRequest()
                .body("User does not exist.");
    }
    @GetMapping(path="/{userId}/portfolio-history")
    public ResponseEntity<String> getPortfolio(@PathVariable String userId){
        String portfolioHistory = accountPortfolioHistoryDAO.getPortfolioHistoryById(userId);
        if (portfolioHistory.equals("{}")) { return new ResponseEntity(HttpStatus.NOT_FOUND);}
        return ResponseEntity.ok(portfolioHistory);
    }
}

