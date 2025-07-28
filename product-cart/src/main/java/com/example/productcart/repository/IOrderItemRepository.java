package com.example.productcart.repository;

import com.example.productcart.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Integer> {
}

