package com.example.borrowbook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="borrows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowCode {
    @Id
    private String code;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
