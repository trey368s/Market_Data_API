package dto;

import lombok.Data;

public @Data
class trades {
    private String type;
    private String symbol;
    private int id;
    private String exchange;
    private double price;
    private int size;
    private String timestamp;
    private String[] condition;
    private String tape;
}