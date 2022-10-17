package com.investmenttracker.enterprise;

import com.investmenttracker.enterprise.dto.investment;
import com.investmenttracker.enterprise.service.IInvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InvestmentTrackerController {

    @Autowired
    IInvestmentService investmentService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/MarketData/")
    @ResponseBody
    public List<investment> fetchAllInvestments() {
        investmentService.fetchAllInvestments();
        return investmentService.fetchAllInvestments(); }

    @GetMapping("/MarketData/{id}/")
    public ResponseEntity fetchById(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);}

    @GetMapping("/MarketData/{symbol}/")
    public String fetchSymbol(@PathVariable("symbol") String symbol) {
        return "symbol";
    }

    @PostMapping(value = "/MarketData/", consumes = "application/json", produces = "application/json")
    public investment createInvestment(@RequestBody investment Investment) throws Exception {
        investment newInvestment = null;
        newInvestment = investmentService.saveInvestment(Investment);
        return newInvestment;}

    @DeleteMapping("/MarketData/{id}/")
    public ResponseEntity deleteInvestment(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);}
}