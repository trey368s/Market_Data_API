package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dto.MarketData;
import com.investmenttracker.enterprise.dto.Investment;

import java.io.IOException;
import java.util.List;

public interface IInvestmentService {
    Investment fetchById(int id);

    void delete(int id) throws Exception;

    void save (Investment investment);

    Investment saveInvestment(Investment investment) throws Exception;

    List<Investment> fetchAll();

    List<Investment> fetchAllInvestments();

    List<MarketData> fetchMarketData(String term) throws IOException;

    List<Investment> fetchOpenPos();

    List<Investment> fetchClosePos();
}
