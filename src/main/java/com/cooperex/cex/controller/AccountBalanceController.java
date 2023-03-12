package com.cooperex.cex.controller;
import com.cooperex.cex.dao.AccountBalanceDAO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/accounts")
public class AccountBalanceController {
    private AccountBalanceDAO accountBalanceDAO;

    public AccountBalanceController() {
        System.out.println("Account Balance Controller object has been initialized!");
        accountBalanceDAO = new AccountBalanceDAO();
    }

    @PostMapping(path="/{userId}/deposit")
    public ResponseEntity<String> addBalance(@PathVariable String userId, @RequestBody Double amount) {

        if (accountBalanceDAO.addBalanceById(userId, amount)) {
            return ResponseEntity.ok("Deposit has been increased by $" + String.valueOf(amount));
        }
        return ResponseEntity.badRequest()
                .body("Check whether you are withdrawing a zero or negative amount");
    }

    @PostMapping(path="/{userId}/withdrawal")
    public ResponseEntity<String> withdrawBalance(@PathVariable String userId, @RequestBody Double amount){
        if (accountBalanceDAO.withdrawBalanceById(userId, amount)) {
            return ResponseEntity.ok("You have successfully withdrawan $" + String.valueOf(amount));
        }
        return ResponseEntity.badRequest()
                .body("Check your remaining balance.");
    }

}

