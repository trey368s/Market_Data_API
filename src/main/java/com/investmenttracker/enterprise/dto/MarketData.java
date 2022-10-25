package com.investmenttracker.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

public @Data class MarketData {
    @SerializedName("Name")
    private String name;
    @SerializedName("Sector")
    private String sector;
    @SerializedName("Symbol")
    private String symbol;
}
