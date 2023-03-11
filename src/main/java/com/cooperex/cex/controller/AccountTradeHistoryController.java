package com.cooperex.cex.controller;
import com.cooperex.cex.dao.AccountTradeHistoryDAO;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/accounts")
public class AccountTradeHistoryController {
    private AccountTradeHistoryDAO accountTradeHistoryDAO;
    public AccountTradeHistoryController() {
        System.out.println("Account Trade History Controller object has been initialized!");
        accountTradeHistoryDAO = new AccountTradeHistoryDAO();
    }

    @GetMapping(path="/{userId}/trade-history")
    public String getTradeHistory(@PathVariable String userId)  {
        return accountTradeHistoryDAO.getTradeHistoryById(userId);
    }
}

