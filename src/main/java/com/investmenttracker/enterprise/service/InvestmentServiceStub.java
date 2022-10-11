package com.investmenttracker.enterprise.service;

import com.investmenttracker.enterprise.dto.investment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvestmentServiceStub implements IInvestmentService{

    List<investment> allEntries = new ArrayList<>();

    @Override
    public void save(investment Investment) {
        allEntries.add(Investment);
    }

    @Override
    public List<investment> fetchAll() {
        return allEntries;
    }
}
