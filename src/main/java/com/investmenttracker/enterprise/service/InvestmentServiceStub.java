package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dao.IInvestmentDAO;
import com.investmenttracker.enterprise.dao.IMarketDataDAO;
import com.investmenttracker.enterprise.dto.MarketData;
import com.investmenttracker.enterprise.dto.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentServiceStub implements IInvestmentService{
    @Autowired
    private IMarketDataDAO marketDataDAO;
    @Autowired
    private IInvestmentDAO investmentDAO;
    List<Investment> allEntries = new ArrayList<>();

    public InvestmentServiceStub(){

    }

    public InvestmentServiceStub(IInvestmentDAO investmentDAO){

        this.investmentDAO = investmentDAO;
    }

    @Override
    public Investment fetchById(int id){
        return investmentDAO.fetchId(id);
    }

    @Override
    public void delete(int id) {
        investmentDAO.delete(id);
    }
    @Override
    public void save(Investment Investment) {
        allEntries.add(Investment);
    }
    @Override
    public Investment saveInvestment(Investment investment) {
        return investmentDAO.saveInvestment(investment);
    }

    @Override
    public List<Investment> fetchAll() {
        return allEntries;
    }

    @Override
    public List<Investment> fetchAllInvestments() {
        return investmentDAO.fetchAllInvestments();
    }

    @Override
    public List<Investment> fetchOpenPos() {
        return investmentDAO.fetchOpenPos();
    }

    @Override
    public List<Investment> fetchClosePos() {
        return investmentDAO.fetchClosePos();
    }

    @Override
    public List<MarketData> fetchMarketData(String term) throws IOException {
        return marketDataDAO.fetchMarketData(term);
    }
}
