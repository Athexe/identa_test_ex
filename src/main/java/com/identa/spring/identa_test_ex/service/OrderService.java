package com.identa.spring.identa_test_ex.service;

import com.identa.spring.identa_test_ex.entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();
    public List<Order> getAllOrdersWithStatusWaiting();
    public List<Order> getAllOrdersWithStatusConfirmed();
    public void saveOrder(Order order);
    public void confirmOrder(Order order);
    public Order getOrder(int id);
    public void deleteOrder(int id);

}
