package com.investmenttracker.enterprise.dto;

import lombok.Data;

public @Data
class investment {
    private int id;
    private String symbol;
    private double shares;
    private double priceOpened;
    private double priceClosed;
}
