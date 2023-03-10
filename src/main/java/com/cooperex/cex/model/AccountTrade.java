package com.cooperex.cex.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AccountTrade {
    private String tradeType;
    private String assetCategory;
    private String assetSymbol;
    private String assetName;
    private String assetCount;

    @JsonCreator
    public AccountTrade(String tradeType,  String assetCategory, String assetSymbol, String assetName, String assetCount) {
        this.tradeType = tradeType;
        this.assetCategory = assetCategory;
        this.assetSymbol = assetSymbol;
        this.assetName = assetName;
        this.assetCount = assetCount;
    }

    public String getTradeType() {
        return tradeType.replace("\"", "");
    }

    public String getAssetCategory() {
        return assetCategory.replace("\"", "");
    }

    public String getAssetSymbol() {
        return assetSymbol.replace("\"", "");
    }

    public String getAssetName() {
        return assetName.replace("\"", "");
    }

    public Double getAssetCount() {
        return Double.parseDouble(assetCount);
    }

}
