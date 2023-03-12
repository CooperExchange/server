package com.cooperex.cex.controller;

import com.cooperex.cex.model.Account;
import com.cooperex.cex.dao.AccountProfileDAO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/accounts")
public class AccountProfileController {
    private AccountProfileDAO accountProfileDAO;

    public AccountProfileController() {
        System.out.println("Account Setting Controller object has been initialized!");
        this.accountProfileDAO = new AccountProfileDAO();;
    }

    @PostMapping(path="/{userId}/profile-update")
    public ResponseEntity<String> updateAccount(@PathVariable String userId, @RequestBody Account account) {
        if (accountProfileDAO.updateAccountInfoById(userId, account)) {
            return ResponseEntity.ok("Account has been reset");
        }
        return ResponseEntity.badRequest()
                .body("Username or email already exists");
    }

    @GetMapping(path="/{userId}/profile")
    public ResponseEntity<String> getAccountProfile(@PathVariable String userId) {
        String account = accountProfileDAO.getAccountProfileById(userId);
        if (account.equals("{}")) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(account);
    }



//    @DeleteMapping(path="/{userId}/rest")
//    public String resetAccount(@PathVariable String userId) {
//        return accountSettingDAO.resetAccountById(userId);
//    }

}
