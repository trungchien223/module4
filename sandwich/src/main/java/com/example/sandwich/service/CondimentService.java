package com.example.sandwich.service;

import com.example.sandwich.entity.Condiment;
import com.example.sandwich.repository.CondimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CondimentService implements ICondimentService{
    @Autowired
    private CondimentRepository condimentRepository;

    @Override
    public List<Condiment> findAll() {
        return condimentRepository.getAllCondiments();
    }
}
