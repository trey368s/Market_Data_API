package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dto.MarketData;
import com.investmenttracker.enterprise.dto.Investment;

import java.io.IOException;
import java.util.List;

public interface IInvestmentService {
    Investment fetchById(int id);

    void delete(int id) throws Exception;

    void save (Investment Investment);

    Investment saveInvestment(Investment investment) throws Exception;

    List<Investment> fetchAll();

    List<Investment> fetchAllInvestments();

    List<MarketData> fetchMarketData(String term) throws IOException;

    List<Investment> fetchOpenPos();

    List<Investment> fetchClosePos();

    static int isSubstring(String s1, String s2)
    {
        int M = s1.length();
        int N = s2.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++)
                if (s2.charAt(i + j) != s1.charAt(j))
                    break;
            if (j == M)
                return i;
        }
        return -1;
    }
}
