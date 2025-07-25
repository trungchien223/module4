package com.example.borrowbook.service;

import com.example.borrowbook.model.Book;
import com.example.borrowbook.model.BorrowCode;
import com.example.borrowbook.repository.IBookRepository;
import com.example.borrowbook.repository.IBorrowCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class BorrowService implements IBorrowService{
    @Autowired
    private IBorrowCodeRepository borrowCodeRepository;
    @Autowired
    private IBookRepository bookRepository;
    @Override
    public String borrowBook(Book book) {
        if (book == null || book.getQuantity() == 0) {
            return null;
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        String code = String.format("%05d", new Random().nextInt(100000));
        borrowCodeRepository.save(new BorrowCode(code, book));
        return code;
    }

    @Override
    public boolean returnBook(String code) {
        Optional<BorrowCode> borrow = borrowCodeRepository.findById(code);
        if (borrow.isPresent()) {
            Book book = borrow.get().getBook();
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
            borrowCodeRepository.deleteById(code);
            return true;
        }
        return false;
    }
}
