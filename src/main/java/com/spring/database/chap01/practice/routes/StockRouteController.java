package com.spring.database.chap01.practice.routes;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockRouteController {

    @GetMapping("/stock-page")
    public String stockPage(){
        return "stock-page";
    }

}
