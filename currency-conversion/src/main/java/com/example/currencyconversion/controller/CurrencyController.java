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
    public String convert(@RequestParam("rate") String rateStr,
                          @RequestParam("usd") String usdStr,
                          Model model) {
        try {
            double rate = Double.parseDouble(rateStr);
            double usd = Double.parseDouble(usdStr);

            if (rate <= 0 || usd < 0) {
                model.addAttribute("error", "Tỉ giá và số USD phải là số dương.");
                return "appconvert/index";
            }

            double vnd = currencyService.convertUsdToVnd(usd, rate);
            model.addAttribute("rate", rate);
            model.addAttribute("usd", usd);
            model.addAttribute("vnd", vnd);
            return "appconvert/result";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Vui lòng nhập số.");
            return "appconvert/index";
        }
    }


} 