
package com.cooperex.cex.controller;
import com.cooperex.cex.dao.LoginDAO;
import com.cooperex.cex.model.Login;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class LoginController {
    private LoginDAO loginDAO;

    public LoginController() {
        System.out.println("LoginForm Controller object has been initialized!");
        loginDAO = new LoginDAO();
    }

    @GetMapping("/")
    public String welcomeAPIUser() {
        return "Hello World! The Cooper Union Backend Server is running!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> returnUserId(@RequestBody Login login) {
        String userId = loginDAO.returnUserId(login);
        if (userId == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userId);
    }
}



