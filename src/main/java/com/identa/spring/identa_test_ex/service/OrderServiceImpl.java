package com.identa.spring.identa_test_ex.service;

import com.identa.spring.identa_test_ex.repository.OrderRepository;
import com.identa.spring.identa_test_ex.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersWithStatusWaiting() {
        return orderRepository.findAllByStatus("Waiting");
    }

    @Override
    public List<Order> getAllOrdersWithStatusConfirmed() {
        return orderRepository.findAllByStatus("Confirmed");
    }

    @Override
    public void saveOrder(Order order) {
        order.setTimestamp(OffsetDateTime.now());
        if(order.getStatus()==null){
            order.setStatus("Waiting");
        }
        orderRepository.save(order);
    }

    @Override
    public void confirmOrder(Order order) {
        order.setTimestamp(OffsetDateTime.now());
        order.setStatus("Confirmed");
        orderRepository.save(order);
    }

    @Override
    public Order getOrder(int id) {
        return orderRepository.getById(id);
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
