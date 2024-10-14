package repository;

import jakarta.persistence.Entity;
import lombok.Data;
import model.Cart;
import model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long>{

    Optional<Cart> findByCustomer(Customer customer);
    Optional<Cart> findByCustomerId(Long customerId);
}
