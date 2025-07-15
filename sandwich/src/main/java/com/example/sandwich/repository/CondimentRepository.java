package com.example.sandwich.repository;

import com.example.sandwich.entity.Condiment;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CondimentRepository {
    public List<Condiment> getAllCondiments() {
        return Arrays.asList(
                new Condiment("Lettuce"),
                new Condiment("Tomato"),
                new Condiment("Mustard"),
                new Condiment("Sprouts")
        );
    }
}
