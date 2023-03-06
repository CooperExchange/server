package com.cooperex.cex.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ControllerAdvice;

public class AccountPortfolioDAOTest {

    @Autowired
    AccountPortfolioDAO accountPortfolioDAO = new AccountPortfolioDAO();

    @Test
    @Rollback(true)
    public void testGetPortfolioById(){
        String result = accountPortfolioDAO.getPortfolioById("1");

        Assert.assertEquals(result, "Unexpected error has occurred.");
    }

    @Test
    @Rollback(true)
    public void testGetPortfolioValueById(){
        String result = accountPortfolioDAO.getPortfolioById("1");

        Assert.assertEquals(result, "Unexpected error has occurred.");
    }
}
