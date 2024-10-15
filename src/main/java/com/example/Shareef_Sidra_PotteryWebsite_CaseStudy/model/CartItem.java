package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_item_id;
    private double price;
    private int quantity;
    private String createdAt;
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public double getTotalPrice() {
        return quantity * price;
    }
}

