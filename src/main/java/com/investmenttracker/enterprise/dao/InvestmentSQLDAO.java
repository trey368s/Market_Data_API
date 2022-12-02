package com.investmenttracker.enterprise.dao;


import com.investmenttracker.enterprise.dto.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("investmentDAO")
public class InvestmentSQLDAO implements IInvestmentDAO{

    @Autowired
    InvestmentRepository investmentRepository;

    @Override
    public Investment save(Investment investment) {
        Investment savedInvestment = investmentRepository.save(investment);
        return savedInvestment;
    }

    @Override
    public Object fetch(int id) {
        return investmentRepository.findById(id).get();
    }

    @Override
    public List<Investment> fetchAllInvestments() {
        List<Investment> allInvestments = new ArrayList<>();
        Iterable<Investment> investments = investmentRepository.findAll();
        for (Investment investment : investments) {
            allInvestments.add(investment);
        }
        return allInvestments;
    }

    @Override
    public List<Investment> fetchClosePos() {
        List<Investment> allInvestments = new ArrayList<>();
        Iterable<Investment> investments = investmentRepository.findAll();
        for (Investment investment : investments) {
            if (investment.getClosedTimestamp() != null) {
                allInvestments.add(investment);
            }
        }
        return allInvestments;
    }

    @Override
    public Investment saveInvestment(Investment investment) {
        Investment createdInvestment = investmentRepository.save(investment);
        return createdInvestment;
    }

    @Override
    public Investment fetchId(int id) {
        return investmentRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        investmentRepository.deleteById(id);
    }

    @Override
    public List<Investment> fetchOpenPos() {
        List<Investment> allInvestments = new ArrayList<>();
        Iterable<Investment> investments = investmentRepository.findAll();
        for (Investment investment : investments) {
            if (investment.getClosedTimestamp() == null) {
                allInvestments.add(investment);
            }
        }
        return allInvestments;
    }
}
