package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createdAt;
    private String modifiedAt;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    @OneToMany(mappedBy = "carts" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private double total;

    public void calculateTotalPrice() {
        total = items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
