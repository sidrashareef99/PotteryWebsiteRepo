package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String name;
    private String productType;
    private String length;
    private String width;
    private String height;
    private String sku;
    private String description;
    private String imgUrl;
    private double price;

    @Column(name = "style")
    private String style;

//    @ManyToOne
//    @JoinColumn(name = "product_type_id")
//    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetails> details = new ArrayList<>();
}
