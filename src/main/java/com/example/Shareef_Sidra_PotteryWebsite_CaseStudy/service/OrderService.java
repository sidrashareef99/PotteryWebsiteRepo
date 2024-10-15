package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Order;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                    order.setCustomer(updatedOrder.getCustomer());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
