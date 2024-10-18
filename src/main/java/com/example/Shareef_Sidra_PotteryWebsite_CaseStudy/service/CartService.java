package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Cart;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.CartItem;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Customer;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Product;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.CartRepository;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.CustomerRepository;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;


//     public Cart getCartByCustomerId(Long customerId) {
//         return cartRepository.findByCustomerId(customerId)
//                 .orElseGet(() -> createCartForCustomer(customerId));  // Create the cart if it doesn't exist
//     }

    public Cart getCartByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        return cartRepository.findByCustomer(customer).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCustomer(customer);
            return cartRepository.save(newCart);
        });
    }

    private Cart createCartForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        newCart.setTotalPrice(0.0);
        return cartRepository.save(newCart);
    }

    public Cart addItemToCart(Long customerId, Long productId, int quantity) {
        Cart cart = getCartByCustomerId(customerId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the product is already in the cart
        Optional<CartItem> existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getProduct_id().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // Update quantity
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Create new CartItem
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice());
            cartItem.setCart(cart);
            cart.getItems().add(cartItem);
        }

        updateCartTotals(cart);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long customerId, Long productId) {
        Cart cart = getCartByCustomerId(customerId);

        // Find the item in the cart
        Optional<CartItem> itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().getProduct_id().equals(productId))
                .findFirst();

        if (itemToRemove.isPresent()) {
            // Explicitly remove the item
            CartItem item = itemToRemove.get();
            cart.getItems().remove(item);

            // You may also need to explicitly remove the item if JPA cascading isn't working properly
            // cartItemRepository.delete(item); // Assuming you have a repository for CartItem
        }

        updateCartTotals(cart);
        return cartRepository.save(cart);
    }


    public Cart updateItemQuantity(Long customerId, Long productId, int newQuantity) {
        Cart cart = getCartByCustomerId(customerId);

        cart.getItems().stream()
                .filter(item -> item.getProduct().getProduct_id().equals(productId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(newQuantity));

        updateCartTotals(cart);
        return cartRepository.save(cart);
    }

    private void updateCartTotals(Cart cart) {
        cart.calculateTotalPrice();
    }
}
