package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy;


import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Cart;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Customer;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.CustomerRepository;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.CartRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceTest {


    @Mock private CustomerRepository customerRepository;
    @Mock private CartRepository cartRepository;

    @InjectMocks CustomerService customerService;

    private Customer customer;
    private Cart cart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

            customer = new Customer();
            customer.setUsername("john_doe");
            customer.setPassword("password");

            cart = new Cart();
            cart.setCustomer(customer);
        }

//        @Test
//        void testRegisterCustomer_ShouldReturnCustomer_WhenSuccessfullyRegistered() {
//            // Arrange
//            when(customerRepository.save(any(Customer.class))).thenReturn(customer);
//            when(cartRepository.save(any(Cart.class))).thenReturn(cart);
//
//            // Act
//            Customer result = customerService.registerCustomer(customer);
//
//            // Assert
//            assertEquals(customer, result);
//            verify(customerRepository, times(1)).save(customer);
//            verify(cartRepository, times(1)).save(any(Cart.class));
//        }

        @Test
        void testFindByUsername_ShouldReturnCustomer_WhenUsernameExists() {
            // Arrange
            when(customerRepository.findByUsername("john_doe")).thenReturn(Optional.of(customer));

            // Act
            Optional<Customer> result = customerService.findByUsername("john_doe");

            // Assert
            assertTrue(result.isPresent());
            assertEquals("john_doe", result.get().getUsername());
            verify(customerRepository, times(1)).findByUsername("john_doe");
        }

        @Test
        void testFindByUsername_ShouldReturnEmpty_WhenUsernameDoesNotExist() {
            // Arrange
            when(customerRepository.findByUsername("unknown_user")).thenReturn(Optional.empty());

            // Act
            Optional<Customer> result = customerService.findByUsername("unknown_user");

            // Assert
            assertFalse(result.isPresent());
            verify(customerRepository, times(1)).findByUsername("unknown_user");
        }
    }
