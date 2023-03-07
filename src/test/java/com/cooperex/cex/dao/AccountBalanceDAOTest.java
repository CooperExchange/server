package com.cooperex.cex.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class AccountBalanceDAOTest {

    @Autowired
    AccountBalanceDAO accountBalanceDAO = new AccountBalanceDAO();

    @Test
    @Rollback(true)
    public void testAddBalanceById(){
        String result = accountBalanceDAO.addBalanceById("1", 2.00);

        Assert.assertEquals(result, "User deposit has been increased by 2.0");
    }

    @Test
    public void testWithdrawBalanceById(){
        String result = accountBalanceDAO.withdrawBalanceById("1", 2.00);

        Assert.assertEquals(result, "User failed to withdraw. Please check the remaining balance.");
    }

}
