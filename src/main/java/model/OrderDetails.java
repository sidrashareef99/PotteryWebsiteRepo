package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String createdAt;
    private String updatedAt;
    private double total;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;
}
