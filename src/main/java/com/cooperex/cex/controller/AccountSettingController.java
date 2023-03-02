package com.cooperex.cex.controller;

import com.cooperex.cex.DatabaseConnectionManager;
import com.cooperex.cex.model.AccountSetting;
import com.cooperex.cex.model.AccountSettingDAO;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/accounts")
public class AccountSettingController {

    //Instantiate dcm - parameters subject to change
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("db", "cooper_exchange", "postgres", "password");
    Connection connection = dcm.getConnection(); //connection with database through DCM
    AccountSettingDAO accountSettingDAO = new AccountSettingDAO(connection);

    public AccountSettingController() throws SQLException {
        System.out.println("Account Setting Controller object has been initialized!");
        this.accountSettingDAO = accountSettingDAO;
    }

    @PostMapping(path="/{userId}/update")
    public String updateAccount(@PathVariable String userId, @RequestBody AccountSetting accountSetting) {
        return accountSettingDAO.updateAccountInfoById(userId, accountSetting);
    }

    @DeleteMapping(path="/{userId}/rest")
    public String resetAccount(@PathVariable String userId) {
        return accountSettingDAO.resetAccountById(userId);
    }

}
