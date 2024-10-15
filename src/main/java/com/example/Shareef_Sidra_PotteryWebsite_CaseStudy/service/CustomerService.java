package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Cart;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Product;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.CartRepository;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.CustomerRepository;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.ProductRepository;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public Customer registerCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        // Create and save a new Cart for the registered Customer
        Cart cart = new Cart();
        cart.setCustomer(savedCustomer);
        cartRepository.save(cart);

        return savedCustomer;
    }

    public List<Product> getProductsByStyle(String style) {
        return productRepository.findByStyle(style);
    }


    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
