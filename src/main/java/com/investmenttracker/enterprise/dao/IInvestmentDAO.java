package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.investment;

public interface IInvestmentDAO {
    investment save(investment investment) throws Exception;

    Object fetch(int id);
}
