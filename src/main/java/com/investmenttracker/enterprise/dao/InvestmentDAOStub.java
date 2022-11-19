package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.Investment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InvestmentDAOStub implements IInvestmentDAO {

    Map<Integer, Investment> allInvestments = new HashMap<>();

    @Override
    public Investment save(Investment investment){
        Integer investmentId = investment.getId();
        allInvestments.put(investmentId, investment);
        return investment;
    }

    @Override
    public Object fetch(int id) {
        return null;
    }

    @Override
    public List<Investment> fetchAllInvestments() {
        List<Investment> returnInvestments = new ArrayList(allInvestments.values());
        return returnInvestments;
    }

    @Override
    public List<Investment> fetchOpenPos() {
        List<Investment> returnOpenInv = new ArrayList(allInvestments.values());
        for(int i=0; i< returnOpenInv.size();i++)
        {
            Investment inv = returnOpenInv.get(i);
            var closed = inv.getClosedTimestamp();
            if (closed != null){
                returnOpenInv.remove(i);
            }
        }
        return returnOpenInv;
    }

    @Override
    public List<Investment> fetchClosePos() {
        List<Investment> returnCloseInv = new ArrayList(allInvestments.values());
        for(int i=0; i< returnCloseInv.size();i++)
        {
            Investment inv = returnCloseInv.get(i);
            var closed = inv.getClosedTimestamp();
            if (closed == null){
                returnCloseInv.remove(i);
                i--;
            }
        }
        return returnCloseInv;
    }

    @Override
    public Investment saveInvestment(Investment investment) {
        Integer investmentID = investment.getId();
        allInvestments.put(investmentID, investment);
        return investment;
    }

    @Override
    public Investment fetchId(int id) {
        return allInvestments.get(id);
    }

    @Override
    public void delete(int id) {
        allInvestments.remove(id);
    }
}
