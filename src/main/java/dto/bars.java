package dto;

import lombok.Data;

public @Data
class bars {
    private String symbol;
    private String time;
    private double open;
    private double high;
    private double low;
    private double close;
    private int volume;
    private String timestamp;
}