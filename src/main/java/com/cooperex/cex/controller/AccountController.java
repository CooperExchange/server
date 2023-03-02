package com.cooperex.cex.controller;
import com.cooperex.cex.DatabaseConnectionManager;
import com.cooperex.cex.model.AccountDAO;
import com.cooperex.cex.model.Trade;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    //Instantiate dcm - parameters subject to change
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("db", "cooper_exchange", "postgres", "password");
    Connection connection = dcm.getConnection(); //connection with database through DCM
    AccountDAO accountDAO = new AccountDAO(connection);

    public AccountController() throws SQLException {
        System.out.println("Account Controller object has been initialized!");
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

