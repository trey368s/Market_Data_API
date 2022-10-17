package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dto.investment;

import java.util.List;

public interface IInvestmentService {
    investment fetchById(int id);

    void save (investment Investment);

    investment saveInvestment(investment Investment) throws Exception;

    List<investment> fetchAll();

    List<investment> fetchAllInvestments();
}
