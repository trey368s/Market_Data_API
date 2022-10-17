package com.investmenttracker.enterprise;

import com.investmenttracker.enterprise.dto.MarketData;
import com.investmenttracker.enterprise.dto.investment;
import com.investmenttracker.enterprise.service.IInvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.io.IOException;
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
        investment foundInvestment = investmentService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundInvestment,headers,HttpStatus.OK);}

    @PostMapping(value = "/MarketData/", consumes = "application/json", produces = "application/json")
    public investment createInvestment(@RequestBody investment Investment) throws Exception {
        investment newInvestment;
        newInvestment = investmentService.saveInvestment(Investment);
        return newInvestment;}

    @DeleteMapping("/MarketData/{id}/")
    public ResponseEntity deleteInvestment(@PathVariable("id") String id) {
        try {
            investmentService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/MarketData/Investment/{symbol}/")
    public String fetchSymbol(@PathVariable("symbol") String symbol) {
        return "symbol";
    }

    @GetMapping("/MarketData/Investment/")
    public ResponseEntity fetchMarketData() {
        try {
            String symbol="1Min";
            List<MarketData> marketData = investmentService.fetchMarketData(symbol);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(marketData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}