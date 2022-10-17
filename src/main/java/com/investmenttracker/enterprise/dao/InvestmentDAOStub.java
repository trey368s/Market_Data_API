package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.investment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InvestmentDAOStub implements IInvestmentDAO {

    Map<Integer, investment> allInvestments = new HashMap<>();

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
        List<investment> returnInvestments = new ArrayList(allInvestments.values());
        return returnInvestments;
    }

    @Override
    public investment saveInvestment(investment Investment) {
        Integer investmentID = Investment.getId();
        allInvestments.put(investmentID, Investment);
        return Investment;
    }

    @Override
    public investment fetchId(int id) {
        return allInvestments.get(id);
    }

    @Override
    public void delete(int id) {
        allInvestments.remove(id);
    }
}
