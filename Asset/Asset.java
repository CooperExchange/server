/*
ECE-366: CooperExchange
Code written by: Chris Lee
Description: Asset class with general properties of an asset available on CooperExchange
 */

package Asset;

public class Asset {
    //private attributes
    private String name;
    private String ticker;
    private Double currentCost;
    private Double dividendYield;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double marketCap;

    //Constructor
    public Asset(String name, String ticker, Double currentCost, Double dividendYield, Double openPrice, Double highPrice, Double lowPrice, Double marketCap){
        setName(name);
        setTicker(ticker);
        setCurrentCost(currentCost);
        setDividendYield(dividendYield);
        setOpenPrice(openPrice);
        setHighPrice(highPrice);
        setLowPrice(lowPrice);
        setMarketCap(marketCap);
    }

    //Getters and Setters for private attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(Double currentCost) {
        this.currentCost = currentCost;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }
}
