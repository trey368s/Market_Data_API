package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.investment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvestmentDAOStub implements IInvestmentDAO {

    List<investment> allInvestments = new ArrayList<investment>();

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
        return allInvestments;
    }

    @Override
    public investment saveInvestment(investment Investment) {
        allInvestments.add(Investment);
        return Investment;
    }
}
