package com.example.productmanagement.repository;


import java.util.List;

public interface IRepository<T>{
    List<T> findAll();
    void save(T t);
    T findById(int id);
    void update(int id, T t);
    void remove(int id);
    List<T> searchByName(String name);
}
