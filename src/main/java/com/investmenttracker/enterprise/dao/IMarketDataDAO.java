package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.MarketData;
import org.yaml.snakeyaml.error.Mark;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

public interface IMarketDataDAO  {
    List<MarketData> fetchMarketData(String symbol) throws IOException;

}
