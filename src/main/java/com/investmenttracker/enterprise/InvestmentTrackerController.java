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
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.time.LocalDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InvestmentTrackerController {

    @Autowired
    IInvestmentService investmentService;

    static int isSubstring(String s1, String s2)
    {
        int M = s1.length();
        int N = s2.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++)
                if (s2.charAt(i + j) != s1.charAt(j))
                    break;
            if (j == M)
                return i;
        }
        return -1;
    }

    @RequestMapping("/")
    public String index(Model open, Model close) {
        List<investment> openPos = investmentService.fetchOpenPos();
        ;
        open.addAttribute("openPos", openPos);
        List<investment> closePos = investmentService.fetchClosePos();
        ;
        open.addAttribute("closePos", closePos);
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
        Investment.setId(amount + 1);
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
        return investmentService.fetchAllInvestments();
    }

    @GetMapping("/MarketData/{id}/")
    public ResponseEntity fetchById(@PathVariable("id") String id) {
        investment foundInvestment = investmentService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundInvestment, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/MarketData/", consumes = "application/json", produces = "application/json")
    public investment createInvestment(@RequestBody investment Investment) throws Exception {
        investment newInvestment;
        newInvestment = investmentService.saveInvestment(Investment);
        return newInvestment;
    }

    @DeleteMapping("/MarketData/{id}/")
    public ResponseEntity deleteInvestment(@PathVariable("id") String id) {
        try {
            investmentService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/MarketData/Investment/")
    public String fetchMarketData(Model model) {
        String term = "";
        try {
            List<MarketData> marketData = investmentService.fetchMarketData(term);
            model.addAttribute("marketData", marketData);
            return "companies";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/open")
    public String openInv(Model model) {
        investment Investment = new investment();
        model.addAttribute(Investment);
        return "add";
    }

    @RequestMapping("/close")
    public String closeInv(Model model) {
        investment Investment = new investment();
        model.addAttribute(Investment);
        return "close";
    }

    @RequestMapping(value = "/Investment", method = RequestMethod.GET)
    public ModelAndView searchInvestments(String searchTerm) {
        URI location = URI.create("https://finance.yahoo.com/quote/" + searchTerm);
        return new ModelAndView("redirect:" + location);
    }

    @GetMapping("/dataAutoComplete")
    @ResponseBody
    public List<String> dataAutoComplete(@RequestParam(value = "term", required = false, defaultValue = "") String term) {
        List<String> allSymbols = new ArrayList<String>();
        try {
            List<MarketData> marketData = investmentService.fetchMarketData(term);
            for (MarketData data : marketData) {
                int substring = isSubstring(term,data.getSymbol());
                if(substring != -1){
                    allSymbols.add(data.getSymbol());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
        return allSymbols;
    }
}