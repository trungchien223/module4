package com.example.borrowbook.controller;

import com.example.borrowbook.model.Book;
import com.example.borrowbook.service.IBookService;
import com.example.borrowbook.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBorrowService borrowService;

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    // Mượn sách
    @PostMapping("/books/{id}/borrow")
    public String borrowBook(@PathVariable Long id, RedirectAttributes redirect) {
        Book book = bookService.findById(id);
        if (book == null) {
            redirect.addFlashAttribute("error", "Không tìm thấy sách");
            return "redirect:/books";
        }
        String code = borrowService.borrowBook(book);
        if (code == null) {
            redirect.addFlashAttribute("error", "Sách đã hết, không thể mượn");
        } else {
            redirect.addFlashAttribute("message", "Mượn sách thành công! Mã mượn của bạn là: " + code);
        }
        return "redirect:/books";
    }

    @GetMapping("/return")
    public String returnForm() {
        return "return";
    }

    // trả sách
    @PostMapping("/return")
    public String returnBook(@RequestParam String code, RedirectAttributes redirect) {
        boolean success = borrowService.returnBook(code);
        if (success) {
            redirect.addFlashAttribute("message", "Trả sách thành công!");
        } else {
            redirect.addFlashAttribute("error", "Mã mượn không hợp lệ");
        }
        return "redirect:/books";
    }
}
