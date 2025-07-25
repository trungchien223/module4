package com.example.borrowbook.service;

import com.example.borrowbook.model.Book;

public interface IBorrowService {
    String borrowBook(Book book);
    boolean returnBook(String code);
}
