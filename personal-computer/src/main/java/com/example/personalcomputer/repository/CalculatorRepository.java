package com.example.personalcomputer.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CalculatorRepository {
    public double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/":
                if (num2 == 0) throw new ArithmeticException("Không thể chia cho 0");
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Toán tử không hợp lệ: " + operator);
        }
    }
}
