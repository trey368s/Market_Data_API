package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.MarketData;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface IMarketDataRetrofitDAO {
    @GET("core/s-and-p-500-companies/constituents_json/data/64dd3e9582b936b0352fdd826ecd3c95/constituents_json.json")
    Call<List<MarketData>> getMarketData();
}
