package com.cooperex.cex.controller;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountPortfolioController {
    private AccountPortfolioDAO accountPortfolioDAO;

    public AccountPortfolioController() {
        System.out.println("Account Portfolilo Controller object has been initialized!");
        accountPortfolioDAO= new AccountPortfolioDAO();
    }

    @GetMapping(path="/{userId}/portfolio")
    public String getPortfolio(@PathVariable String userId){
        return accountPortfolioDAO.getPortfolioById(userId);
    }
}

