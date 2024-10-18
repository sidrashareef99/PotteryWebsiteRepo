package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class CustomProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custom_product_id;
    private String name;
    private String productType;
    private String length;
    private String width;
    private String height;
    private String sku;
    private String description;
    private String imgUrl;
    private double price;
    private String style;
    private String color;

//    @ManyToOne
//    @JoinColumn(name = "product_type_id")
//    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetails> details = new ArrayList<>();
}
