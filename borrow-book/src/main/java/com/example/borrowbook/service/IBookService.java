package com.example.borrowbook.service;

import com.example.borrowbook.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book findById(Long id);
}
