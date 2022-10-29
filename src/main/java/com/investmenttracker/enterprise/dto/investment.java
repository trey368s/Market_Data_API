package com.investmenttracker.enterprise.dto;

import lombok.Data;

/**
 * Represents investment data
 * id: Investment ID
 * symbol: Interactive Ticker
 * shares: Number of Shares
 * priceOpened: Price that the share opened
 * priceClosed: Price that the share closed
 * openedTimestamp: Timestamp for the stock market open
 * closedTimestamp: Timestamp for the stock market close
 * profit: Profit from shares
 */
public @Data
class investment {
    private int id;
    private String symbol;
    private int shares;
    private double priceOpened;
    private double priceClosed;
    private String openedTimestamp;
    private String closedTimestamp;
    private double profit;

    public String toString(){
        if(closedTimestamp != null) {
            return "ID: " + id + ", Symbol: " + symbol + ", Shares: " + shares + ", Price Opened: $" + priceOpened +
                    ", Opened Timestamp: " + openedTimestamp + ", Price Closed: $" + priceClosed + ", Closed Timestamp: "
                    + closedTimestamp + ", Profit: $" + profit;
        }
        else{
            return "ID: " + id + ", Symbol: " + symbol + ", Shares: " + shares + ", Price Opened: $" + priceOpened +
                    ", Opened Timestamp: " + openedTimestamp;
        }
    }
}
