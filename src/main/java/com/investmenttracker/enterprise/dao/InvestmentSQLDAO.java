package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("investmentDAO")
public class InvestmentSQLDAO implements IInvestmentDAO{

    @Autowired
    InvestmentRepository investmentRepository;

    @Override
    public Investment save(Investment investment) {
        return null;
    }

    @Override
    public Object fetch(int id) {
        return null;
    }

    @Override
    public List<Investment> fetchAllInvestments() {
        return null;
    }

    @Override
    public List<Investment> fetchClosePos() {
        return null;
    }

    @Override
    public Investment saveInvestment(Investment investment) {
        Investment createdInvestment = investmentRepository.save(investment);
        return createdInvestment;
    }

    @Override
    public Investment fetchId(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Investment> fetchOpenPos() {
        return null;
    }
}
