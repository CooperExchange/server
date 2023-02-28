package com.cooperex.cex.controller;
import com.cooperex.cex.model.AccountDAO;
import com.cooperex.cex.model.Trade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private AccountDAO accountDAO;

    public AccountController() {
        System.out.println("Account Controller object has been initialized!");
        accountDAO = new AccountDAO();
    }

    @PostMapping(path="/{userId}/deposit")
    public String addBalance(@PathVariable String userId, @RequestBody Double amount){
        return accountDAO.addBalanceById(userId, amount);
    }

    @PostMapping(path="/{userId}/withdrawal")
    public String withdrawBalance(@PathVariable String userId, @RequestBody Double amount){
        return accountDAO.withdrawBalanceById(userId, amount);
    }

    @PostMapping(path="/{userId}/trade")
    public String buyAsset(@PathVariable String userId, @RequestBody Trade trade)  {
        return accountDAO.tradeAssetBySymbol(userId, trade);
    }
}

