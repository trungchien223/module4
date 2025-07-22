package com.example.blog.service;

import com.example.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    Blog save(Blog blog);
    void delete(Long id);
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> searchByTitle(String category,String title, Pageable pageable);
}
