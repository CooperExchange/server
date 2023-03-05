package com.cooperex.cex.controller;
import com.cooperex.cex.dao.AccountBalanceDAO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountBalanceController {
    private AccountBalanceDAO accountBalanceDAO;

    public AccountBalanceController() {
        System.out.println("Account Balance Controller object has been initialized!");
        accountBalanceDAO = new AccountBalanceDAO();
    }

    @PostMapping(path="/{userId}/deposit")
    public String addBalance(@PathVariable String userId, @RequestBody Double amount){
        return accountBalanceDAO.addBalanceById(userId, amount);
    }

    @PostMapping(path="/{userId}/withdrawal")
    public String withdrawBalance(@PathVariable String userId, @RequestBody Double amount){
        return accountBalanceDAO.withdrawBalanceById(userId, amount);
    }

}

