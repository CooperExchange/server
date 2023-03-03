package com.cooperex.cex.controller;
import com.cooperex.cex.dao.AccountTradeDAO;
import com.cooperex.cex.model.Trade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountTradeController {
    private AccountTradeDAO accountTradeDAO;

    public AccountTradeController() {
        System.out.println("Account Controller object has been initialized!");
        accountTradeDAO = new AccountTradeDAO();
    }

    @PostMapping(path="/{userId}/trade")
    public String buyAsset(@PathVariable String userId, @RequestBody Trade trade)  {
        return accountTradeDAO.tradeAssetBySymbol(userId, trade);
    }
}

