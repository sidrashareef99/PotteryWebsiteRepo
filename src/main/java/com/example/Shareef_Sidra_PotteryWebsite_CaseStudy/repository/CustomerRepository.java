package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
