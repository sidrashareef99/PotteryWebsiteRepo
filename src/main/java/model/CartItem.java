package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private int quantity;
    private String createdAt;
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "carts_id")
    private Cart cart;

    public double getTotalPrice() {
        return quantity * price;
    }
}

