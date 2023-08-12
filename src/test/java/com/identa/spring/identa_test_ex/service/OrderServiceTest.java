package com.identa.spring.identa_test_ex.service;

import com.identa.spring.identa_test_ex.repository.OrderRepository;
import com.identa.spring.identa_test_ex.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void orderRepositoryGetAllOrdersTest() {
        List<Order> mockOrders = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Order> result = orderService.getAllOrders();

        assertSame(mockOrders, result);
    }

    @Test
    public void orderRepositoryGetAllOrdersWithStatusWaitingTest() {
        List<Order> mockOrders = new ArrayList<>();
        when(orderRepository.findAllByStatus("Waiting")).thenReturn(mockOrders);

        List<Order> result = orderService.getAllOrdersWithStatusWaiting();

        assertSame(mockOrders, result);
    }

    @Test
    public void orderRepositoryGetAllOrdersWithStatusConfirmedTest() {
        List<Order> mockOrders = new ArrayList<>();
        when(orderRepository.findAllByStatus("Confirmed")).thenReturn(mockOrders);

        List<Order> result = orderService.getAllOrdersWithStatusConfirmed();

        assertSame(mockOrders, result);
    }

    @Test
    public void orderRepositorySaveOrderTest() {
        Order mockOrder = new Order();
        mockOrder.setId(0);
        mockOrder.setDescription("Test");

        orderService.saveOrder(mockOrder);

        verify(orderRepository).save(mockOrder);
        assertNotNull(orderRepository.findById(0));

        orderRepository.deleteById(0);
    }

    @Test
    public void orderRepositoryConfirmOrderTest() {
        Order mockOrder = new Order();
        mockOrder.setId(0);
        mockOrder.setDescription("Test");

        orderService.confirmOrder(mockOrder);

        verify(orderRepository).save(mockOrder);
        assertEquals("Confirmed", mockOrder.getStatus());
        orderRepository.deleteById(0);
    }

    @Test
    public void orderRepositoryGetOrderTest() {
        int orderId = 2;
        Order mockOrder = new Order();
        when(orderRepository.getById(orderId)).thenReturn(mockOrder);

        Order result = orderService.getOrder(orderId);

        assertSame(mockOrder, result);
    }

    @Test
    public void orderRepositoryDeleteOrderTest() {
        int orderId = 2;
        doNothing().when(orderRepository).deleteById(orderId);

        orderService.deleteOrder(orderId);

        verify(orderRepository).deleteById(orderId);
    }
}