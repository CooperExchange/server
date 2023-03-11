package com.cooperex.cex.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AccountTrade {
    private String tradeType;
    private String assetCategory;
    private String assetSymbol;
    private String assetName;
    private Double assetCount;
    private Double totalPrice;
    private Double assetPrice;


    public AccountTrade() {}
    @JsonCreator
    public AccountTrade(String tradeType,  String assetCategory, String assetSymbol, String assetName, Double assetCount) {
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

    public Double getAssetCount() { return assetCount; }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setAssetCount(Double assetCount) {
        this.assetCount = assetCount;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAssetPrice(Double assetPrice) {
        this.assetPrice = assetPrice;
    }

}
