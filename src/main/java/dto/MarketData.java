package dto;

import lombok.Data;

public @Data
class MarketData {
    private String symbol;
    private String time;
    private double open;
    private double high;
    private double low;
    private double close;
}
