package com.identa.spring.identa_test_ex.controller;

import com.identa.spring.identa_test_ex.entity.Order;
import com.identa.spring.identa_test_ex.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MyControllerTest {
    int orderId = 2;

    @Mock
    private OrderService orderService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private MyController myController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void myControllerDeleteOrderTest() {
        doNothing().when(orderService).deleteOrder(orderId);

        String result = myController.deleteOrder(orderId);

        verify(orderService).deleteOrder(orderId);
        verify(messagingTemplate).convertAndSend("/topic/orders", "update");
        assertEquals("redirect:/orders", result);
    }
    @Test
    public void myControllerConfirmOrderTest() {
        Order mockOrder = new Order();
        when(orderService.getOrder(orderId)).thenReturn(mockOrder);
        doNothing().when(orderService).confirmOrder(mockOrder);

        String result = myController.confirmOrder(orderId);

        verify(orderService).getOrder(orderId);
        verify(orderService).confirmOrder(mockOrder);
        verify(messagingTemplate).convertAndSend("/topic/orders", "update");
        assertEquals("redirect:/orders", result);
    }
    @Test
    public void myControllerGetUpdateOrderInfoPageTest() {
        Order mockOrder = new Order();
        when(orderService.getOrder(orderId)).thenReturn(mockOrder);
        Model mockModel = mock(Model.class);

        String result = myController.updateOrder(orderId, mockModel);

        verify(orderService).getOrder(orderId);
        verify(mockModel).addAttribute("order", mockOrder);
        assertEquals("update-order-info", result);
    }
    @Test
    public void myControllerSaveAfterUpdateOrderTest() {
        Order mockOrder = new Order();
        doNothing().when(orderService).saveOrder(mockOrder);

        String result = myController.saveAfterUpdateOrder(mockOrder);

        verify(orderService).saveOrder(mockOrder);
        verify(messagingTemplate).convertAndSend("/topic/orders", "update");
        assertEquals("redirect:/orders", result);
    }
    @Test
    public void myControllerSaveOrderTest() {
        Order mockOrder = new Order(); // Create a mock Order instance
        doNothing().when(orderService).saveOrder(mockOrder);

        String result = myController.saveOrder(mockOrder);

        verify(orderService).saveOrder(mockOrder);
        verify(messagingTemplate).convertAndSend("/topic/orders", "update");
        assertEquals("redirect:/", result);
    }
    @Test
    public void myControllerGetAllOrdersPageTest() {
        List<Order> mockOrdersWaiting = new ArrayList<>();
        List<Order> mockOrdersConfirmed = new ArrayList<>();
        when(orderService.getAllOrdersWithStatusWaiting()).thenReturn(mockOrdersWaiting);
        when(orderService.getAllOrdersWithStatusConfirmed()).thenReturn(mockOrdersConfirmed);
        Model mockModel = mock(Model.class);

        String result = myController.showAllOrders(mockModel);

        verify(orderService).getAllOrdersWithStatusWaiting();
        verify(orderService).getAllOrdersWithStatusConfirmed();
        verify(mockModel).addAttribute("allOrdersWithStatusWaiting", mockOrdersWaiting);
        verify(mockModel).addAttribute("allOrdersWithStatusConfirmed", mockOrdersConfirmed);
        assertEquals("all-orders", result);
    }
    @Test
    public void myControllerGetStartPageTest() {
        String result = myController.startPage();

        assertEquals("start-page", result);
    }
}
