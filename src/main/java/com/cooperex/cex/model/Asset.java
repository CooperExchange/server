package com.cooperex.cex.model;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Asset {
    private String assetCategory;
    private String assetSymbol;
    private String assetName;
    private Double assetPrice;
    private Double assetMarketCap;

    public Asset() {
    }

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public String getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Double getAssetPrice() {
        return assetPrice;
    }

    public void setAssetPrice(Double assetPrice) {
        this.assetPrice = assetPrice;
    }

    public Double getAssetMarketCap() {
        return assetMarketCap;
    }

    public void setAssetMarketCap(Double assetMarketCap) {
        this.assetMarketCap = assetMarketCap;
    }

}