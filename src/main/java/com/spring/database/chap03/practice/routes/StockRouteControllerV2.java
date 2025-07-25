package com.spring.database.chap03.practice.routes;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockRouteControllerV2 {

    @GetMapping("/stock-page")
    public String stockPage(){
        return "stock-page";
    }

}
