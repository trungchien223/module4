package com.example.productcart.repository;
import com.example.productcart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer>{
}
