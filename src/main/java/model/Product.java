package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sku;
    private String description;
    private String imgUrl;
    private double price;

    @ManyToOne
    @JoinColumn(name = "product_types_id")
    private ProductType productType;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetails> details = new ArrayList<>();
}
