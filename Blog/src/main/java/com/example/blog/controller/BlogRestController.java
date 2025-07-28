package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogRestController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    // Lấy danh sách tất cả blog (sort theo createdAt DESC)
    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.findAll();
        return ResponseEntity.ok(blogs);
    }

    // Lấy chi tiết blog theo id
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return blogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Lấy danh sách blog theo categoryId
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable Long categoryId) {
        List<Blog> blogs = blogService.findByCategoryId(categoryId);
        return ResponseEntity.ok(blogs);
    }

    // Thêm blog mới
    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog created = blogService.save(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Cập nhật blog
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> existing = blogService.findById(id);
        if (existing.isPresent()) {
            blog.setId(id);
            return ResponseEntity.ok(blogService.save(blog));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa blog
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        Optional<Blog> existing = blogService.findById(id);
        if (existing.isPresent()) {
            blogService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
