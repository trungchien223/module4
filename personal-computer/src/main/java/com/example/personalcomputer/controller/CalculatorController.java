package com.example.personalcomputer.controller;

import com.example.personalcomputer.service.ICalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @Autowired
    private ICalculatorService calculatorService;

    @GetMapping("/appcalculate")
    public String showForm() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operator") String operator,
            Model model
    ) {
        double result = calculatorService.calculate(num1, num2, operator);
        model.addAttribute("result", result);
        model.addAttribute("operation", getOperationName(operator));
        return "calculator";
    }

    @ExceptionHandler({ArithmeticException.class, IllegalArgumentException.class})
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "calculator";
    }

    private String getOperationName(String op) {
        String result;
        switch (op) {
            case "+":
                result = "Cộng";
                break;
            case "-":
                result = "Trừ";
                break;
            case "*":
                result = "Nhân";
                break;
            case "/":
                result = "Chia";
                break;
            default:
                result = "Unknown";
        }
        return result;
    }
}
