package com.example.borrowbook.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookAspect {
    private int visitCount = 0;

    // Đếm tất cả các hành động người dùng
    @Before("execution(* com.example.borrowbook.controller.*.*(..))")
    public void countVisit(JoinPoint joinPoint) {
        visitCount++;
        System.out.println("[VISITOR] Lượt truy cập thư viện: " + visitCount + " - Method: " + joinPoint.getSignature().getName());
    }

    // Ghi log sau khi mượn hoặc trả sách
    @After("execution(* com.example.borrowbook.service.BorrowService.borrowBook(..)) || execution(* com.example.borrowbook.service.BorrowService.returnBook(..))")
    public void logBookChange(JoinPoint joinPoint) {
        System.out.println("[BOOK CHANGE] Hành động: " + joinPoint.getSignature().getName());
    }
}
