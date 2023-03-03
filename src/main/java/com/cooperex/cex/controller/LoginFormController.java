package com.cooperex.cex.controller;
import com.cooperex.cex.dao.LoginFormDAO;
import com.cooperex.cex.model.LoginForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginFormController {
    private LoginFormDAO loginFormDAO;

    public LoginFormController() {
        System.out.println("LoginForm Controller object has been initialized!");
        loginFormDAO = new LoginFormDAO();
    }

    @GetMapping("/")
    public String welcomeAPIUser() {
        return "Hello World! The Cooper Union Backend Server is running!";
    }

    @PostMapping("/login")
    public String returnUserId(@RequestBody LoginForm loginForm) {
        return loginFormDAO.returnUserId(loginForm);
    }
}

