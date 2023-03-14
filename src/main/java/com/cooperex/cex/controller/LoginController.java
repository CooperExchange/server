
package com.cooperex.cex.controller;
import com.cooperex.cex.dao.LoginDAO;
import com.cooperex.cex.model.Login;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class LoginController {
    private LoginDAO loginDAO;

    public LoginController() {
        System.out.println("LoginForm Controller object has been initialized!");
        loginDAO = new LoginDAO();
    }

    @CrossOrigin
    @GetMapping("/")
    public String welcomeAPIUser() {
        return "Hello World! The Cooper Union Backend Server is running!";
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> getUserId(@RequestBody Login login) {
        String userId = loginDAO.getUserIdByEmailAndPassword(login);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}



