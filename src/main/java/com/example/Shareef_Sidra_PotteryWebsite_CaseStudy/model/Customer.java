package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer() {
        super();
        setRole(Role.CUSTOMER);
    }
}
