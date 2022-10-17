package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.MarketData;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

@Repository
public class MarketDataDAO implements IMarketDataDAO {
    @Override
    public List<MarketData> fetchMarketData(String symbol) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IMarketDataRetrofitDAO iMarketDataRetrofitDAO = retrofitInstance.create(IMarketDataRetrofitDAO.class);
        Call<List<MarketData>> data = iMarketDataRetrofitDAO.getMarketData(symbol);
        Response<List<MarketData>> execute = data.execute();
        List<MarketData> marketData = execute.body();
        return marketData;
    }
}
