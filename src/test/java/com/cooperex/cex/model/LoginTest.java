package com.cooperex.cex.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class LoginTest {

    @Test
    public void testLogin(){
        String email = "Kanghyuk.Lee@cooper.edu";
        String password = "Cooperunion";

        Login login = new Login(email, password);

        assertSame(email, login.getEmail());
        assertSame(password, login.getPassword());
    }
}
