package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.MarketData;

import java.io.IOException;
import java.util.List;

public interface IMarketDataDAO  {
    List<MarketData> fetchMarketData(String combinedName) throws IOException;

}
