package com.example.borrowbook.repository;

import com.example.borrowbook.model.BorrowCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBorrowCodeRepository extends JpaRepository<BorrowCode, String> {
}
