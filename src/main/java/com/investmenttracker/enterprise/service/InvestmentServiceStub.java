package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dao.IInvestmentDAO;
import com.investmenttracker.enterprise.dto.investment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentServiceStub implements IInvestmentService{
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
        investment Investment = new investment();
        Investment.setId(333);
        Investment.setSymbol("MSFT");
        return Investment;
    }

    @Override
    public void save(investment Investment) {
        allEntries.add(Investment);
    }
    @Override
    public investment saveInvestment(investment Investment) throws Exception {
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
}
