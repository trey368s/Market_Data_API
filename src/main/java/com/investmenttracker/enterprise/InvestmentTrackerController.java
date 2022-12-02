package com.investmenttracker.enterprise;

import com.investmenttracker.enterprise.dto.LabelValue;
import com.investmenttracker.enterprise.dto.MarketData;
import com.investmenttracker.enterprise.dto.Investment;
import com.investmenttracker.enterprise.service.IInvestmentService;
import org.hibernate.annotations.Entity;
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

    static int isSubstring(String s1, String s2) {
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
        List<Investment> openPos = investmentService.fetchOpenPos();
        ;
        open.addAttribute("openPos", openPos);
        List<Investment> closePos = investmentService.fetchClosePos();
        ;
        open.addAttribute("closePos", closePos);
        return "index";
    }

    @RequestMapping("/Investment/{symbol}/")
    public String fetchSymbol(@PathVariable String symbol) {
        return "symbol";
    }

    @RequestMapping("/openInvestment")
    public String openInvestment(Investment investment) throws Exception {
        LocalDateTime timestamp = LocalDateTime.now();
        List<com.investmenttracker.enterprise.dto.Investment> idSize = investmentService.fetchAllInvestments();
        int amount = idSize.size();
        investment.setId(amount + 1);
        investment.setOpenedTimestamp(timestamp.toString());
        investmentService.saveInvestment(investment);
        return "index";
    }

    @RequestMapping("/closeInvestment")
    public String closeInvestment(Investment investment, @RequestParam(value = "id", required = false, defaultValue = "0") String id) throws Exception {
        com.investmenttracker.enterprise.dto.Investment foundInvestment = investmentService.fetchById(Integer.parseInt(id));
        investment.setSymbol(foundInvestment.getSymbol());
        investment.setShares(foundInvestment.getShares());
        investment.setPriceOpened(foundInvestment.getPriceOpened());
        investment.setOpenedTimestamp(foundInvestment.getOpenedTimestamp());
        LocalDateTime timestamp = LocalDateTime.now();
        investment.setClosedTimestamp(timestamp.toString());
        double profit = (investment.getPriceClosed() * investment.getShares()) - (investment.getPriceOpened() * investment.getShares());
        investment.setProfit(profit);
        investmentService.saveInvestment(investment);
        return "index";
    }

    @GetMapping("/MarketData/")
    @ResponseBody
    public List<Investment> fetchAllInvestments() {
        investmentService.fetchAllInvestments();
        return investmentService.fetchAllInvestments();
    }

    @GetMapping("/MarketData/{id}/")
    public ResponseEntity fetchById(@PathVariable("id") String id) {
        Investment foundInvestment = investmentService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundInvestment, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/MarketData/", consumes = "application/json", produces = "application/json")
    public Investment createInvestment(@RequestBody Investment investment) throws Exception {
        Investment newInvestment;
        newInvestment = investmentService.saveInvestment(investment);
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
        Investment Investment = new Investment();
        model.addAttribute(Investment);
        return "add";
    }

    @RequestMapping("/close")
    public String closeInv(Model model) {
        Investment Investment = new Investment();
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
        List<String> allSymbols = new ArrayList<>();
        try {
            List<MarketData> marketData = investmentService.fetchMarketData(term);
            for (MarketData data : marketData) {
                int substring = isSubstring(term, data.getSymbol());
                if (substring != -1) {
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