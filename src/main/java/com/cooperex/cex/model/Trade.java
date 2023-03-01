package com.cooperex.cex.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Trade {
    public String tradeType;
    public String assetSymbol;
    public String assetCount;
    public String assetCategory;

    @JsonCreator
    public Trade(String tradeType, String assetSymbol, String assetCount, String assetCategory) {
        this.tradeType = tradeType;
        this.assetSymbol = assetSymbol;
        this.assetCount = assetCount;
        this.assetCategory = assetCategory;
    }
}
