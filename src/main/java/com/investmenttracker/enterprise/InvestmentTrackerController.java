package com.investmenttracker.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvestmentTrackerController {

    @RequestMapping("/")
    public String index() {
        return "symbol";
    }

    @GetMapping("/MarketData/{symbol}")
    public String fetchSymbol(@PathVariable("symbol") String symbol) {
        return "symbol";
    }
}