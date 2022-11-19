package com.investmenttracker.enterprise.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public @Data
class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
