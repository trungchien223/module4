package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String listBlogs(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "create";
    }

    @PostMapping
    public String createBlog(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Đã tạo bài viết thành công!");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            model.addAttribute("blog", blogOptional.get());
            return "view";
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bài viết.");
            return "redirect:/blogs";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            model.addAttribute("blog", blogOptional.get());
            return "edit";
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bài viết để sửa.");
            return "redirect:/blogs";
        }
    }

    @PostMapping("/{id}/edit")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        blog.setId(id);
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Đã cập nhật bài viết.");
        return "redirect:/blogs";
    }

    @PostMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Đã xóa bài viết.");
        return "redirect:/blogs";
    }
}
