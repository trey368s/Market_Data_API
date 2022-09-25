package dto;

import lombok.Data;

public @Data
class quotes {
    private String type;
    private String symbol;
    private String askExchange;
    private double askPrice;
    private int askSize;
    private String bidExchange;
    private double bidPrice;
    private int bidSize;
    private String timestamp;
    private String condition[];
    private String tape;
}