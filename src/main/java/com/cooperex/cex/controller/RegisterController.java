package com.cooperex.cex.controller;
import com.cooperex.cex.model.Register;
import com.cooperex.cex.dao.RegisterDAO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class RegisterController {
    private RegisterDAO registerDAO;

    public RegisterController() {
        System.out.println("Register Controller object has been initialized!");
        registerDAO = new RegisterDAO();
    }

    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody Register account) {
        if (registerDAO.createAccount(account)) {
            return ResponseEntity.ok("An acccount has been succesfully created.");
        }
        return ResponseEntity.badRequest()
                .body("The username or email has been taken.");
    }

    @PostMapping("/registration-random")
    public ResponseEntity<String> createRandomAccount() {
        if (registerDAO.createRandomAccount()) {
            return ResponseEntity.ok("A random account has been succesfully created.");
        }
        return ResponseEntity.badRequest()
                .body("The username or email has been taken.");

    }
}

