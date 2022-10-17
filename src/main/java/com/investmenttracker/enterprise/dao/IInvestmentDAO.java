package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.investment;

import java.util.List;

public interface IInvestmentDAO {
    investment save(investment investment);

    Object fetch(int id);

    List<investment> fetchAllInvestments();

    investment saveInvestment(investment investment);
}