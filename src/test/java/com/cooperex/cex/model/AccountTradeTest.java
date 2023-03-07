package com.cooperex.cex.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertSame;


public class AccountTradeTest {

    @Test
    public void testAccountTrade(){
        String tradeType = "Buy";
        String assetCategory = "Stock";
        String assetSymbol = "Apl";
        String assetName = "Apple";
        String assetCount = "4.0";

        AccountTrade accountTrade = new AccountTrade(tradeType, assetCategory, assetSymbol, assetName, assetCount);

        Assertions.assertSame(tradeType, accountTrade.getTradeType());
        Assertions.assertSame(assetCategory, accountTrade.getAssetCategory());
        Assertions.assertSame(assetSymbol, accountTrade.getAssetSymbol());
        Assertions.assertSame(assetName, accountTrade.getAssetName());
        Assertions.assertEquals(4.0, accountTrade.getAssetCount());
    }
}
