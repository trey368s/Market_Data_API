package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dto.investment;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.List;

public interface IInvestmentService {
    investment fetchById(int id);

    void delete(int id) throws Exception;

    void save (investment Investment);

    investment saveInvestment(investment Investment) throws Exception;

    List<investment> fetchAll();

    List<investment> fetchAllInvestments();

    ResponseBody fetchMarketData(String symbol) throws IOException;
}
