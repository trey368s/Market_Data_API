package com.investmenttracker.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

public @Data class Bar {
    @SerializedName("t")
    private String t;
    @SerializedName("o")
    private Double o;
    @SerializedName("h")
    private Double h;
    @SerializedName("l")
    private Double l;
    @SerializedName("c")
    private Double c;
    @SerializedName("v")
    private Integer v;
    @SerializedName("n")
    private Integer n;
    @SerializedName("vw")
    private Double vw;
}
