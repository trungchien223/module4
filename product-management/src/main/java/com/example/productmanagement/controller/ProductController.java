package com.example.productmanagement.controller;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    public String list(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "list";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }
    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirectAttributes) {
        //product.setId((int)(Math.random()*1000));
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Product has been saved successfully");
        return "redirect:/products";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model,RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "edit";
    }
    @PostMapping("/edit")
    public String edit(Product product, RedirectAttributes redirectAttributes) {
        Product existingProduct = productService.findById(product.getId());
        if (existingProduct == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found");
            return "redirect:/products";
        }
        productService.update(product.getId(), product);
        redirectAttributes.addFlashAttribute("message", "Product has been updated successfully");
        return "redirect:/products";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.remove(id);
            redirectAttributes.addFlashAttribute("message", "Product has been deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Product not found");
        }
        return "redirect:/products";
    }
    @GetMapping("/{id}/detail")
    public String view(@PathVariable int id, Model model,RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "detail";
    }
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        if (keyword == null || keyword.isEmpty()) {
            return "redirect:/products";
        }
        List<Product> products = productService.searchByName(keyword);
        if(products.size()==0){
            model.addAttribute("error", "Product not found");
        }
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "list";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "ID sản phẩm không hợp lệ!");
        return "redirect:/products";
    }
}

