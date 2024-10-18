package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Admin;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Customer;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Role;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.AdminRepository;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.CustomerRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;


    @Autowired
    public CustomUserDetailsService(AdminRepository adminRepository, CustomerRepository customerRepository, AdminRepository adminRepositor, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find the user as a Customer
        Customer customer = customerRepository.findByUsername(username).orElseThrow();
        if (customer.getRole().equals(Role.CUSTOMER)) {
            return new org.springframework.security.core.userdetails.User(
                    customer.getUsername(),
                    customer.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"))  // Correct role
            );
        }

        // Try to find the user as an Admin
        if (customer.getRole().equals(Role.ADMIN)) {
            return new org.springframework.security.core.userdetails.User(
                    customer.getUsername(),
                    customer.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))  // Correct role
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}