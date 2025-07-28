package com.example.productcart.service;

import com.example.productcart.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService implements IOrderService{

    @Override
    public void saveOrder(Map<Integer, CartItem> cart) {
    }
}
