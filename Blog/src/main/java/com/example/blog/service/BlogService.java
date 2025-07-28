package com.example.blog.service;

import com.example.blog.model.Blog;
import com.example.blog.repository.IBlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class BlogService implements IBlogService{
    private final IBlogRepository blogRepository;
    public BlogService(IBlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }
    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        blog.setCreatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> searchByTitle(String category,String title, Pageable pageable) {
        return blogRepository.search(category,title, pageable);
    }
    @Override
    public List<Blog> findByCategoryId(Long categoryId) {
        return blogRepository.findByCategoryId(categoryId);
    }

}
