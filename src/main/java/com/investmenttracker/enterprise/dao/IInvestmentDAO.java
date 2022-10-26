package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.investment;

import java.util.List;

public interface IInvestmentDAO {
    investment save(investment investment);

    Object fetch(int id);

    List<investment> fetchAllInvestments();

    List<investment> fetchClosePos();

    investment saveInvestment(investment investment);

    investment fetchId(int id);

    void delete(int id);

    List<investment> fetchOpenPos();
}
