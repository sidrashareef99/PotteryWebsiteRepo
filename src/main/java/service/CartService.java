package service;

import model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CartRepository;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
    }
//    public Cart getCart() {
//        return cartRepository.getOne(cartRepository.count());
//    }
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    public Cart getById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
    public Cart update(Long id, Cart updatedCart) {
        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setId(updatedCart.getId());
                    cart.setCreatedAt(updatedCart.getCreatedAt());
                    cart.setModifiedAt(updatedCart.getModifiedAt());
                    cartRepository.save(cart);
                    return cart;
                })
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
