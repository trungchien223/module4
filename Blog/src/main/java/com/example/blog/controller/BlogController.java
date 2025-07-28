package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    public BlogController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }
    @GetMapping
    public String displayAll(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "categories", required = false, defaultValue = "") String category,
                             @RequestParam(name = "title", required = false, defaultValue = "") String title) {
        int size = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Blog> blogPage = blogService.searchByTitle(category, title, pageable);
        if (blogPage.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy bài viết nào phù hợp.");
        }
        model.addAttribute("blogs", blogPage);
        model.addAttribute("category", category);
        model.addAttribute("title", title);
        model.addAttribute("categories", categoryService.findAll());
        return "list";
    }

//    @GetMapping
//    public String listBlogs(Model model) {
//        List<Blog> blogs = blogService.findAll();
//        model.addAttribute("blogs", blogs);
//        model.addAttribute("categories", categoryService.findAll());
//        return "list";
//    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
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
        if (blogOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bài viết.");
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blogOptional.get());
        return "view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bài viết để sửa.");
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blogOptional.get());
        model.addAttribute("categories", categoryService.findAll());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        Optional<Blog> existing = blogService.findById(id);
        if (existing.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bài viết để cập nhật.");
            return "redirect:/blogs";
        }
        blog.setId(id);
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Đã cập nhật bài viết.");
        return "redirect:/blogs";
    }

    @PostMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy bài viết để xóa.");
            return "redirect:/blogs";
        }
        blogService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Đã xóa bài viết.");
        return "redirect:/blogs";
    }
}