package com.cooperex.cex.model;

import com.cooperex.cex.util.DataTransferObject;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Trade implements DataTransferObject {
    private String tradeType;
    private String assetSymbol;
    private String assetCount;

    @JsonCreator
    public Trade(String tradeType, String assetSymbol, String assetCount) {
        this.tradeType = tradeType;
        this.assetSymbol = assetSymbol;
        this.assetCount = assetCount;
    }

    //Print attributes as strings
    @Override
    public String toString() {
        return "Trade{" +
                "tradeType='" + tradeType + '\'' +
                ", assetSymbol='" + assetSymbol + '\'' +
                ", assetCount='" + assetCount + '\'' +
                '}';
    }

    //Getters and Setters

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    public String getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(String assetCount) {
        this.assetCount = assetCount;
    }
}
