package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("investmentDAO")
public class InvestmentSQLDAO implements IInvestmentDAO{

    @Autowired
    InvestmentRepository investmentRepository;

    @Override
    public investment save(investment investment) {
        return null;
    }

    @Override
    public Object fetch(int id) {
        return null;
    }

    @Override
    public List<investment> fetchAllInvestments() {
        return null;
    }

    @Override
    public List<investment> fetchClosePos() {
        return null;
    }

    @Override
    public investment saveInvestment(investment investment) {
        investment createdInvestment = investmentRepository.save(investment);
        return createdInvestment;
    }

    @Override
    public investment fetchId(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<investment> fetchOpenPos() {
        return null;
    }
}
