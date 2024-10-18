package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Product;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {

        return productRepository.findAll();
    }

    public Optional<Product> getById(Long id) {

        return productRepository.findById(id);
    }

    public Product save(Product product) {

        return productRepository.save(product);
    }

    public void delete(Long id) {

        productRepository.deleteById(id);
    }

    public Product update(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    product.setSku(updatedProduct.getSku());
                    product.setLength(updatedProduct.getLength());
                    product.setWidth(updatedProduct.getWidth());
                    product.setHeight(updatedProduct.getHeight());
                    product.setImgUrl(updatedProduct.getImgUrl());
                    product.setDescription(updatedProduct.getDescription());
                    product.setProductType(updatedProduct.getProductType());
                    product.setStyle(updatedProduct.getStyle());

                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
