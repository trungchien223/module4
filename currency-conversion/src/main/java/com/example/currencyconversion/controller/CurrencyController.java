package com.example.currencyconversion.controller;

import com.example.currencyconversion.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/appconvert")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;
    @GetMapping
    public String showForm(){
        return "appconvert/index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("rate") double rate,
                          @RequestParam("usd") double usd,
                          Model model) {
        double vnd = currencyService.convertUsdToVnd(usd, rate);
        model.addAttribute("rate", rate);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);
        return "appconvert/result";
    }
} 