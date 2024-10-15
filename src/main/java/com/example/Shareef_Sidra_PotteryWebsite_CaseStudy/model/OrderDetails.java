package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_details_id;
    private String createdAt;
    private String updatedAt;
    private double total;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
