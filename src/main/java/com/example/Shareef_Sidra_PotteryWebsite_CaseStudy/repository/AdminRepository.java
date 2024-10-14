package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUsername(String username);
}
