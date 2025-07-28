package com.example.productcart.controller;

import com.example.productcart.model.CartItem;
import com.example.productcart.model.Order;
import com.example.productcart.model.OrderItem;
import com.example.productcart.model.Product;
import com.example.productcart.repository.IOrderItemRepository;
import com.example.productcart.repository.IOrderRepository;
import com.example.productcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("cart")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Khởi tạo giỏ hàng trong session
    @ModelAttribute("cart")
    public Map<Integer, CartItem> cart() {
        return new HashMap<>();
    }

    // Trang danh sách sản phẩm
    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product_list";
    }

    // Trang chi tiết sản phẩm
    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "product_detail";
    }

    // Thêm sản phẩm vào giỏ
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Integer productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            @ModelAttribute("cart") Map<Integer, CartItem> cart) {
        Product product = productService.findById(productId).orElseThrow();
        CartItem item = cart.getOrDefault(productId, new CartItem(product, 0));
        item.setQuantity(item.getQuantity() + quantity);
        cart.put(productId, item);
        return "redirect:/cart/view";
    }

    // Xem giỏ hàng
    @GetMapping("/cart/view")
    public String viewCart(@ModelAttribute("cart") Map<Integer, CartItem> cart, Model model) {
        model.addAttribute("cart", cart);
        double total = cart.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("total", total);
        return "cart_view";
    }

    // Cập nhật số lượng sản phẩm trong giỏ
    @PostMapping("/cart/update")
    public String updateCart(@RequestParam Integer productId,
                             @RequestParam int quantity,
                             @ModelAttribute("cart") Map<Integer, CartItem> cart) {
        if (cart.containsKey(productId)) {
            CartItem item = cart.get(productId);
            item.setQuantity(quantity);
        }
        return "redirect:/cart/view";
    }

    // Xóa sản phẩm khỏi giỏ
    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Integer productId,
                                 @ModelAttribute("cart") Map<Integer, CartItem> cart) {
        cart.remove(productId);
        return "redirect:/cart/view";
    }

    // Trang thanh toán
    @GetMapping("/cart/checkout")
    public String checkout(@ModelAttribute("cart") Map<Integer, CartItem> cart, Model model) {
        double total = cart.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "checkout";
    }

    // Lưu đơn hàng sau khi thanh toán
    @PostMapping("/cart/checkout/save")
    public String saveCheckout(@ModelAttribute("cart") Map<Integer, CartItem> cart,
                               SessionStatus status) {
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart/view?error=empty";
        }

        double total = cart.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setTotal(total);
        orderRepository.save(order);

        for (CartItem cartItem : cart.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(cartItem.getProduct().getName());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }

        // Xóa session giỏ hàng
        status.setComplete();
        return "redirect:/products?success";
    }

    // Xóa toàn bộ giỏ hàng
    @PostMapping("/cart/clear")
    public String clearCart(SessionStatus status) {
        status.setComplete();
        return "redirect:/products";
    }
}
