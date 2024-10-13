package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    public Customer() {
        super();
        setRole(Role.CUSTOMER);
    }
}
