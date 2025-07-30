package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ajax")
public class BlogAjaxController {

    @Autowired
    private BlogService blogService;

    // Tìm kiếm bài viết bằng AJAX
    @GetMapping("/search")
    public ResponseEntity<List<Blog>> searchBlogs(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String category) {

        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Blog> blogPage = blogService.searchByTitle(category, title, pageable);

        return ResponseEntity.ok(blogPage.getContent());
    }

    // Tải thêm bài viết (load more)
    @GetMapping("/load-more")
    public ResponseEntity<List<Blog>> loadMoreBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String category) {

        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Blog> blogPage = blogService.searchByTitle(category, title, pageable);

        return ResponseEntity.ok(blogPage.getContent());
    }

    // Lấy danh sách bài viết theo category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable Long categoryId) {
        List<Blog> blogs = blogService.findByCategoryId(categoryId);
        return ResponseEntity.ok(blogs);
    }
}