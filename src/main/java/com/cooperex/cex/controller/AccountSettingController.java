package com.cooperex.cex.controller;

import com.cooperex.cex.model.AccountSetting;
import com.cooperex.cex.dao.AccountSettingDAO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/accounts")
public class AccountSettingController {
    private AccountSettingDAO accountSettingDAO;

    public AccountSettingController() {
        System.out.println("Account Setting Controller object has been initialized!");
        this.accountSettingDAO = new AccountSettingDAO();;
    }

    @PostMapping(path="/{userId}/update")
    public ResponseEntity<String> updateAccount(@PathVariable String userId, @RequestBody AccountSetting accountSetting) {
        if (accountSettingDAO.updateAccountInfoById(userId, accountSetting)) {
            return ResponseEntity.ok("Account has been reset");
        }
        return ResponseEntity.badRequest()
                .body("Username or email already exists");
    }

//    @DeleteMapping(path="/{userId}/rest")
//    public String resetAccount(@PathVariable String userId) {
//        return accountSettingDAO.resetAccountById(userId);
//    }

}
