package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.MarketData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface IMarketDataRetrofitDAO {

    @Headers({
            "APCA-API-KEY-ID: AKFOO6Z1ZA5J9L4WR4XD",
            "APCA-API-SECRET-KEY: NGCMczuSOV3LSdOE7v4azBhP1fwzFNyPHHVCH4rS"
    })
    @GET("v2/stocks/AAPL/bars")
    Call<List<MarketData>> getMarketData(@Query(value="Combined_Name") String symbol);
}
