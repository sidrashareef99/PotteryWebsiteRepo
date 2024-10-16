//package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.controller;
//
//import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Cart;
//import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.User;
//import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.CartService;
//import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.CustomerService;
//
//@Controller
//@RequestMapping("/cart")
//public class CartController {
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private CustomerService customerService;
//
//    @GetMapping
//    public String viewCart(Model model) {
//        Customer customer = getAuthenticatedUser();
//        Cart cart = cartService.getCartByCustomerId(customer.getId());
//        model.addAttribute("cart", cart);
//        return "cart";
//    }
//
//    @PostMapping("/add")
//    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
//        Customer customer = getAuthenticatedUser();
//        cartService.addItemToCart(customer.getId(), productId, quantity);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/remove/{productId}")
//    public String removeFromCart(@PathVariable Long productId) {
//        Customer customer = getAuthenticatedUser();
//        cartService.removeItemFromCart(customer.getId(), productId);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/update")
//    public String updateCartItemQuantity(@RequestParam Long productId, @RequestParam int quantity) {
//        Customer customer = getAuthenticatedUser();
//        cartService.updateItemQuantity(customer.getId(), productId, quantity);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/checkout")
//    public String checkout(Model model, Authentication authentication) {
//        // Get the authenticated user's username
//        String username = authentication.getName();
//
//        // Find the customer using their username
//        Customer customer = customerService.findByUsername(username).orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        // Retrieve the customer's cart
//        Cart cart = cartService.getCartByCustomerId(customer.getId());
//
//        // Prepare the receipt model attributes
//        model.addAttribute("customer", customer);
//        model.addAttribute("cart", cart);
//        model.addAttribute("totalPrice", cart.getTotal());
//
//        return "checkout-receipt"; // A Thymeleaf template that displays the receipt
//    }
//
//
//    private Customer getAuthenticatedUser() {
//        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//        return customerService.findByUsername(username).orElseThrow(() -> new RuntimeException("Customer not found"));
//    }
//
//}
