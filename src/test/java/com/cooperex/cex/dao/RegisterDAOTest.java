package com.cooperex.cex.dao;

import com.cooperex.cex.model.Register;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterDAOTest {

    @Autowired
    RegisterDAO registerDAO = new RegisterDAO();

    @Autowired
    Register account = new Register("kanghyuk.lee@coopr.edu", "Chris", "Lee", "chrislee8674", "cooperunion");

    @Test
    public void testCreateAccount(){
        String result = registerDAO.createAccount(account);

        Assert.assertEquals(result, "Request success");
    }
}
