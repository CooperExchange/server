package com.cooperex.cex.dao;

import com.cooperex.cex.model.Login;
import org.apache.juli.logging.Log;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginDAOTest {

    @Autowired
    LoginDAO loginDAO = new LoginDAO();

    @Autowired
    Login login = new Login("kanghyuk.lee@cooper.edu", "cooperunion");

    @Test
    public void testReturnUserId(){
        String result = loginDAO.returnUserId(login);

        Assert.assertEquals(result, "No matching record was found. Please check your email or password");
    }

}
