package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dto.investment;

import java.util.List;

public interface IInvestmentService {
    void save (investment Investment);

    List<investment> fetchAll();
}
