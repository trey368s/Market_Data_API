package com.investmenttracker.enterprise.dao;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Repository
public class MarketDataDAO implements IMarketDataDAO {
    @Override
    public ResponseBody fetchMarketData(String symbol) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IMarketDataRetrofitDAO iMarketDataRetrofitDAO = retrofitInstance.create(IMarketDataRetrofitDAO.class);
        Call<ResponseBody> data = iMarketDataRetrofitDAO.getMarketData(symbol);
        Response<ResponseBody> execute = data.execute();
        ResponseBody marketData = execute.body();
        return marketData;
    }
}
