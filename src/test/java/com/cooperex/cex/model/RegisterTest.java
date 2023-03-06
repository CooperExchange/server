package com.cooperex.cex.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class RegisterTest {

    @Test
    public void testRegister(){
        String email = "kanghyuk.lee@cooper.edu";
        String firstName = "Chris";
        String lastName = "Lee";
        String username = "chrislee8684";
        String password = "cooperunion";

        Register register = new Register(email, firstName, lastName, username, password);

        assertSame(email, register.getEmail());
        assertSame(firstName, register.getFirstName());
        assertSame(lastName, register.getLastName());
        assertSame(username, register.getUsername());
        assertSame(password, register.getPassword());
    }
}
