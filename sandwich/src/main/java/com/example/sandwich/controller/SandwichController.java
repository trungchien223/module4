package com.example.sandwich.controller;

import com.example.sandwich.entity.Condiment;
import com.example.sandwich.service.ICondimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SandwichController {
    @Autowired
    private ICondimentService condimentService;

    @GetMapping("/appsandwich")
    public String showForm(Model model) {
        List<Condiment> condiments = condimentService.findAll();
        model.addAttribute("condiments", condiments);
        return "form";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiment, Model model) {
        model.addAttribute("selectedCondiments", condiment);
        return "result";
    }
}
