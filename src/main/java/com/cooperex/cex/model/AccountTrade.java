package com.cooperex.cex.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AccountTrade {
    public String tradeType;
    public String assetCategory;
    public String assetSymbol;
    public String assetName;
    public String assetCount;

    @JsonCreator
    public AccountTrade(String tradeType,  String assetCategory, String assetSymbol, String assetName, String assetCount) {
        this.tradeType = tradeType;
        this.assetCategory = assetCategory;
        this.assetSymbol = assetSymbol;
        this.assetName = assetName;
        this.assetCount = assetCount;
    }
}
