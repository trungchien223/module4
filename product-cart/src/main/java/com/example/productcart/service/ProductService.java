package com.example.productcart.service;

import com.example.productcart.model.Product;
import com.example.productcart.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    private final IProductRepository productRepository;
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
}
