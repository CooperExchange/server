package com.cooperex.cex.controller;
import com.cooperex.cex.DatabaseConnectionManager;
import com.cooperex.cex.model.Register;
import com.cooperex.cex.model.RegisterDAO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class RegisterController {

    //Instantiate dcm - parameters subject to change
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("db", "cooper_exchange", "postgres", "password");
    Connection connection = dcm.getConnection(); //connection with database through DCM
    RegisterDAO registerDAO = new RegisterDAO(connection);

    public RegisterController() throws SQLException {
        System.out.println("Register Controller object has been initialized!");
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

