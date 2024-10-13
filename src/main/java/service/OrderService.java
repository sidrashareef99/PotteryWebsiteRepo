package service;

import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderRepository;
import repository.ProductRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void update(Long id, Order updatedOrder) {
        orderRepository.findById(id)
                .map(order -> {
                    order.setUser(updatedOrder.getUser());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
