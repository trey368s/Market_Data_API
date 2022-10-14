package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dto.investment;

import java.util.List;

public interface IInvestmentService {
    investment fetchById(int id);

    investment save (investment Investment) throws Exception;

    void saveInvestment(investment Investment);

    List<investment> fetchAll();
}
