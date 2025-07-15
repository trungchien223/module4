package com.example.sandwich.service;

import com.example.sandwich.entity.Condiment;

import java.util.List;

public interface ICondimentService {
    List<Condiment> findAll();
}
