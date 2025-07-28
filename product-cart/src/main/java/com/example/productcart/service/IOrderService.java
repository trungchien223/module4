package com.example.productcart.service;

import com.example.productcart.model.CartItem;

import java.util.Map;

public interface IOrderService {
    void saveOrder(Map<Integer, CartItem> cart);
}
