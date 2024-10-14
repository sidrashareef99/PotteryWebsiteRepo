package service;

import model.Admin;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.AdminRepository;
import repository.CustomerRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find the user as a Customer
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    customer.get().getUsername(),
                    customer.get().getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"))  // Correct role
            );
        }

        // Try to find the user as an Admin
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    admin.get().getUsername(),
                    admin.get().getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))  // Correct role
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}