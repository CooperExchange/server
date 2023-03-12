package com.cooperex.cex.model;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Trade {
    private String tradeType;
    private String assetCategory;
    private String assetSymbol;
    private String assetName;
    private Double assetCount;
    private Double totalPrice;
    private Double assetPrice;

    @JsonCreator
    public Trade(String tradeType,  String assetCategory, String assetSymbol, String assetName, Double assetCount) {
        this.tradeType = tradeType;
        this.assetCategory = assetCategory;
        this.assetSymbol = assetSymbol;
        this.assetName = assetName;
        this.assetCount = assetCount;
    }

    public Trade() {}

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
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
