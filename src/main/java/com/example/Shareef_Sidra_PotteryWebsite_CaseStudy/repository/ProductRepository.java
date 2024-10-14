package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStyle(String style);
}
