
package com.cooperex.cex.controller;
import com.cooperex.cex.DatabaseConnectionManager;
import com.cooperex.cex.model.LoginFormDAO;
import com.cooperex.cex.model.LoginForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class LoginFormController {


    //Instantiate dcm - parameters subject to change
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("db", "cooper_exchange", "postgres", "password");
    Connection connection = dcm.getConnection(); //connection with database through DCM
    LoginFormDAO loginFormDAO = new LoginFormDAO(connection);

    public LoginFormController() throws SQLException {
        System.out.println("LoginForm Controller object has been initialized!");
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

