package com.example.currencyconversion.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService implements ICurrencyService{
    @Override
    public double convertUsdToVnd(double usd, double rate) {
        return usd * rate;
    }
} 