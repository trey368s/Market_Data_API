package com.investmenttracker.enterprise.dao;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface IMarketDataRetrofitDAO {

    @Headers({
            "APCA-API-KEY-ID: AKFOO6Z1ZA5J9L4WR4XD",
            "APCA-API-SECRET-KEY: NGCMczuSOV3LSdOE7v4azBhP1fwzFNyPHHVCH4rS"
    })
    @GET()
    Call<ResponseBody> getMarketData(@Url String symbol);
}
