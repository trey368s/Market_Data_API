package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dao.IInvestmentDAO;
import com.investmenttracker.enterprise.dto.investment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvestmentServiceStub implements IInvestmentService{

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
    public investment save(investment Investment) throws Exception {

        allEntries.add(Investment);
        return investmentDAO.save(Investment);
    }
    @Override
    public void saveInvestment(investment Investment) {

        allEntries.add(Investment);
    }

    @Override
    public List<investment> fetchAll() {
        return allEntries;
    }
}
