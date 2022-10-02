package com.marketdata.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarketDataController {

    /**
     * index page
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     *
     * @param symbol
     * @return returns ticker symbol
     */
    @GetMapping("/MarketData/{symbol}")
    public String fetchSymbol(@PathVariable("symbol") String symbol) {
        return symbol;
    }
}