package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.controller;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Cart;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Order;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.User;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Customer;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.CartService;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.CustomerService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    // Display the cart for the logged-in customer
    @GetMapping
    public String viewCart(Model model) {
        Customer customer = getAuthenticatedCustomer();
        if (customer == null) {
            return "redirect:/login"; // Redirect to login page if user is not authenticated
        }

        Cart cart = cartService.getCartByCustomerId(customer.getId());
        model.addAttribute("cart", cart);
        return "cart"; // Returns the view for the cart page
    }

    // Add an item to the cart
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        Customer customer = getAuthenticatedCustomer();
        if (customer == null) {
            return "redirect:/login"; // Redirect to login page if user is not authenticated
        }

        cartService.addItemToCart(customer.getId(), productId, quantity);

        return "redirect:/productspage"; // Redirect to the cart view after adding the item
    }

    // Remove an item from the cart
    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        Customer customer = getAuthenticatedCustomer();
        if (customer == null) {
            return "redirect:/login"; // Redirect to login page if user is not authenticated
        }

        cartService.removeItemFromCart(customer.getId(), productId);
        return "redirect:/cart"; // Redirect to the cart view after removing the item
    }

    // Update the quantity of an item in the cart
    @PostMapping("/update")
    public String updateCartItemQuantity(@RequestParam Long productId, @RequestParam int quantity) {
        Customer customer = getAuthenticatedCustomer();
        if (customer == null) {
            return "redirect:/login"; // Redirect to login page if user is not authenticated
        }

        cartService.updateItemQuantity(customer.getId(), productId, quantity);
        return "redirect:/cart"; // Redirect to the cart view after updating the quantity
    }

    // Handle checkout process for the cart
    @PostMapping("/checkout")
    public String checkout(Model model) {
        Customer customer = getAuthenticatedCustomer();
        if (customer == null) {
            return "redirect:/login"; // Redirect to login page if user is not authenticated
        }

        Cart cart = cartService.getCartByCustomerId(customer.getId());
        model.addAttribute("customer", customer);
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());

        return "checkout-receipt"; // Thymeleaf template for displaying the receipt
    }
//
//    @PostMapping("/newCart")
//    public String checkoutCart( Model model) {
//        Customer customer = getAuthenticatedCustomer();
//        if (customer == null) {
//            return "redirect:/login"; // Redirect to login page if user is not authenticated
//        }
//        Cart cart1 = cartService.getCartByCustomerId(customer.getId());
//
//        Order order = new Order();
//        order.setCustomer(customer);
//        order.setCart(cart1);
//        orderService.save(order);
//
//        System.out.println("order deatils displayed");
//
//
//        model.addAttribute("customer", customer);
//        model.addAttribute("cart", cart1);
//        model.addAttribute("totalPrice", cart1.getTotalPrice());
//
//        cartService.createNewCartForCustomer(customer.getId());
//        return "redirect:/cart"; // Thymeleaf template for displaying the receipt
//    }


    // Helper method to get the authenticated customer from the security context
    private Customer getAuthenticatedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null; // Return null if user is not authenticated
        }

        String username = authentication.getName(); // Get the username of the authenticated user
        return customerService.findByUsername(username).orElse(null); // Find the customer by username
    }
}
