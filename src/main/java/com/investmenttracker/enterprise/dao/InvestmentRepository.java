package com.investmenttracker.enterprise.dao;

import com.investmenttracker.enterprise.dto.Investment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvestmentRepository extends CrudRepository<Investment, Integer> {

    List<Investment> findByUserId(int userId);

}
