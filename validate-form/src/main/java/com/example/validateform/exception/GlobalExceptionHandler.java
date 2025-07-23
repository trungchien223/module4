package com.example.validateform.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý lỗi 404 - Không tìm thấy trang
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Trang bạn tìm kiếm không tồn tại.");
        return "error/404";
    }

    // Xử lý lỗi 500 - Lỗi nội bộ
//    @ExceptionHandler(Exception.class)
//    public String handle500(Exception ex, Model model) {
//        model.addAttribute("errorMessage", "Đã xảy ra lỗi trong hệ thống: " + ex.getMessage());
//        return "error/500";
//    }
}
