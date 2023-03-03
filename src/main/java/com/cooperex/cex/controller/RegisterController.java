package com.cooperex.cex.controller;
import com.cooperex.cex.model.Register;
import com.cooperex.cex.dao.RegisterDAO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private RegisterDAO registerDAO;

    public RegisterController() {
        System.out.println("Register Controller object has been initialized!");
        registerDAO = new RegisterDAO();
    }

    @PostMapping("/registration")
    public String createAccount(@RequestBody Register account) {
        return registerDAO.createAccount(account);
    }

    @PostMapping("/registration-random")
    public String createRandomAccount() {
        return registerDAO.createRandomAccount();
    }

}

