package com.identa.spring.identa_test_ex.repository;

import com.identa.spring.identa_test_ex.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    public List<Order> findAllByStatus(String status);
}
