package com.cooperex.cex.controller;
import com.cooperex.cex.dao.AccountTradeDAO;
import com.cooperex.cex.model.Trade;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/accounts")
public class AccountTradeController {
    private AccountTradeDAO accountTradeDAO;

    public AccountTradeController() {
        System.out.println("Account Controller object has been initialized!");
        accountTradeDAO = new AccountTradeDAO();
    }

    @CrossOrigin
    @PostMapping(path="/{userId}/trade")
    public  ResponseEntity<String> buyAsset(@PathVariable String userId, @RequestBody Trade trade)  {

        if (accountTradeDAO.tradeAssetBySymbol(userId, trade)) {
            return ResponseEntity.ok("Trade successful");
        }
        return ResponseEntity.badRequest()
                .body("Trade failed. Check your remaining balance for buying or your asset shares for selling.");
    }
}

