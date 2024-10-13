package repository;

import jakarta.persistence.Entity;
import lombok.Data;
import model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long>{
}
