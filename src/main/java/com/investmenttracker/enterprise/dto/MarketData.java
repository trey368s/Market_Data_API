package com.investmenttracker.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

public @Data class MarketData {
    @SerializedName("bars")
    private List<Bar> bars;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("next_page_token")
    private String nextPageToken;
}
