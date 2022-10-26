package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dao.IInvestmentDAO;
import com.investmenttracker.enterprise.dao.IMarketDataDAO;
import com.investmenttracker.enterprise.dto.MarketData;
import com.investmenttracker.enterprise.dto.investment;
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
    List<investment> allEntries = new ArrayList<>();

    public InvestmentServiceStub(){

    }

    public InvestmentServiceStub(IInvestmentDAO investmentDAO){

        this.investmentDAO = investmentDAO;
    }

    @Override
    public investment fetchById(int id){
        return investmentDAO.fetchId(id);
    }

    @Override
    public void delete(int id) {
        investmentDAO.delete(id);
    }
    @Override
    public void save(investment Investment) {
        allEntries.add(Investment);
    }
    @Override
    public investment saveInvestment(investment Investment) {
        return investmentDAO.saveInvestment(Investment);
    }

    @Override
    public List<investment> fetchAll() {
        return allEntries;
    }

    @Override
    public List<investment> fetchAllInvestments() {
        return investmentDAO.fetchAllInvestments();
    }

    @Override
    public List<investment> fetchOpenPos() {
        return investmentDAO.fetchOpenPos();
    }

    @Override
    public List<investment> fetchClosePos() {
        return investmentDAO.fetchClosePos();
    }

    @Override
    public List<MarketData> fetchMarketData() throws IOException {
        return marketDataDAO.fetchMarketData();
    }
}
