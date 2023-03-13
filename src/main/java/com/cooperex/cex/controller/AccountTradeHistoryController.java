package com.cooperex.cex.controller;
import com.cooperex.cex.dao.AccountTradeHistoryDAO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/accounts")
public class AccountTradeHistoryController {
    private AccountTradeHistoryDAO accountTradeHistoryDAO;

    public AccountTradeHistoryController() {
        System.out.println("Account Trade History Controller object has been initialized!");
        accountTradeHistoryDAO = new AccountTradeHistoryDAO();
    }

    @CrossOrigin
    @GetMapping(path = "/{userId}/trade-history")
    public ResponseEntity<String> getTradeHistory(@PathVariable String userId) {
        String tradeHistory = accountTradeHistoryDAO.getTradeHistoryById(userId);

        if (tradeHistory.equals("{}")) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(tradeHistory);
    }
}