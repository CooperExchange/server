package com.cooperex.cex.controller;

import com.cooperex.cex.model.AccountSetting;
import com.cooperex.cex.dao.AccountSettingDAO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountSettingController {
    private AccountSettingDAO accountSettingDAO;

    public AccountSettingController() {
        System.out.println("Account Setting Controller object has been initialized!");
        AccountSettingDAO accountSettingDAO = new AccountSettingDAO();
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
