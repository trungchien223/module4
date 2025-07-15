package com.example.personalcomputer.service;

import com.example.personalcomputer.repository.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements ICalculatorService{
    @Autowired
    private CalculatorRepository calculatorRepository;

    @Override
    public double calculate(double num1, double num2, String operator) {
        return calculatorRepository.calculate(num1, num2, operator);
    }
}
