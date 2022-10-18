package com.investmenttracker.enterprise.dao;

import okhttp3.ResponseBody;

import java.io.IOException;

public interface IMarketDataDAO  {
    ResponseBody fetchMarketData(String symbol) throws IOException;

}
