package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
