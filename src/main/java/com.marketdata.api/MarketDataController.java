package com.marketdata.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarketDataController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/MarketData/{symbol}")
    public ResponseEntity fetchSymbol(@PathVariable("symbol") String symbol) {
        return new ResponseEntity(HttpStatus.OK);
    }
}