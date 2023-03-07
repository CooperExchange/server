package com.cooperex.cex.dao;

import com.cooperex.cex.model.AccountTrade;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;

public class AccountTradeDAOTest {

    @Autowired
    AccountTradeDAO accountTradeDAO = new AccountTradeDAO();

    @Autowired
    AccountTrade accountTrade = new AccountTrade("Buy", "Stock", "App", "Apple", "3");


    @Test
    public void testTradeAssetBySymbol(){
        String result = accountTradeDAO.tradeAssetBySymbol("1", accountTrade);

        Assert.assertEquals(result, "User request asset trading");
    }
}
