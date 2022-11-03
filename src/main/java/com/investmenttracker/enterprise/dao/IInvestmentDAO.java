package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.Investment;

import java.util.List;

public interface IInvestmentDAO {
    Investment save(Investment investment);

    Object fetch(int id);

    List<Investment> fetchAllInvestments();

    List<Investment> fetchClosePos();

    Investment saveInvestment(Investment investment);

    Investment fetchId(int id);

    void delete(int id);

    List<Investment> fetchOpenPos();
}
