package com.investmenttracker.enterprise.dto;

import lombok.Data;

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
}
