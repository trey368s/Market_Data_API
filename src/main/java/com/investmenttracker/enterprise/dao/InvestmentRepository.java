package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.investment;
import org.springframework.data.repository.CrudRepository;

public interface InvestmentRepository extends CrudRepository<investment, Integer> {
}
