package com.marketdata.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiApplication {

    @RequestMapping("/")
    public String index() {
        return "start";
    }
}