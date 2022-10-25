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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;
import java.time.LocalDateTime;

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

    @RequestMapping("/Investment/{symbol}/")
    public String fetchSymbol(@PathVariable String symbol) {
        return "symbol";
    }

    @RequestMapping("/openInvestment")
    public String openInvestment(investment Investment) throws Exception {
        LocalDateTime timestamp = LocalDateTime.now();
        List<investment> idSize = investmentService.fetchAllInvestments();
        int amount = idSize.size();
        Investment.setId(amount+1);
        Investment.setOpenedTimestamp(timestamp.toString());
        investmentService.saveInvestment(Investment);
        return "index";
    }

    @RequestMapping("/closeInvestment")
    public String closeInvestment(investment Investment, @RequestParam(value = "id", required = false, defaultValue = "0") String id) throws Exception {
        investment foundInvestment = investmentService.fetchById(Integer.parseInt(id));
        Investment.setSymbol(foundInvestment.getSymbol());
        Investment.setShares(foundInvestment.getShares());
        Investment.setPriceOpened(foundInvestment.getPriceOpened());
        Investment.setOpenedTimestamp(foundInvestment.getOpenedTimestamp());
        LocalDateTime timestamp = LocalDateTime.now();
        Investment.setClosedTimestamp(timestamp.toString());
        double profit = (Investment.getPriceClosed() * Investment.getShares()) - (Investment.getPriceOpened() * Investment.getShares());
        Investment.setProfit(profit);
        investmentService.saveInvestment(Investment);
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
    public ResponseEntity fetchMarketData(@PathVariable("symbol") String symbol) {
        try {
            String query="v2/stocks/" + symbol + "/bars?timeframe=1Min";
            okhttp3.ResponseBody marketData = investmentService.fetchMarketData(query);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(marketData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/open")
    public String openInv(Model model){
        investment Investment = new investment();
        model.addAttribute(Investment);
        return "add";
    }

    @RequestMapping("/close")
    public String closeInv(Model model){
        investment Investment = new investment();
        model.addAttribute(Investment);
        return "close";
    }

    @GetMapping("/Investment")
    public ResponseEntity searchInvestments(@RequestParam(value = "searchTerm", required = false, defaultValue = "None") String searchTerm) {
        return new ResponseEntity(HttpStatus.OK);
    }
}