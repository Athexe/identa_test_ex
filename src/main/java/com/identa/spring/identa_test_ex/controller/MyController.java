package com.identa.spring.identa_test_ex.controller;

import com.identa.spring.identa_test_ex.entity.Order;
import com.identa.spring.identa_test_ex.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    OrderService orderService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/")
    public String startPage(){
        return "start-page";
    }

    @RequestMapping("/orders")
    public String showAllOrders(Model model){
        List<Order> allOrdersWithStatusWaiting = orderService.getAllOrdersWithStatusWaiting();
        List<Order> allOrdersWithStatusConfirmed = orderService.getAllOrdersWithStatusConfirmed();
        model.addAttribute("allOrdersWithStatusWaiting",allOrdersWithStatusWaiting);
        model.addAttribute("allOrdersWithStatusConfirmed",allOrdersWithStatusConfirmed);
        return "all-orders";
    }
    @RequestMapping("/make-order")
    public String makeOrder(Model model){
        Order order = new Order();
        model.addAttribute("order",order);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails authenticatedUser = (UserDetails) authentication.getPrincipal();
        model.addAttribute("authenticatedUser", authenticatedUser);
        return "make-order-info";
    }
    @RequestMapping("saveOrder")
    public String saveOrder(@ModelAttribute("order") Order order){
        orderService.saveOrder(order);
        messagingTemplate.convertAndSend("/topic/orders", "update");
        return "redirect:/";
    }
    @RequestMapping("saveAfterUpdateOrder")
    public String saveAfterUpdateOrder(@ModelAttribute("order") Order order){
        orderService.saveOrder(order);
        messagingTemplate.convertAndSend("/topic/orders", "update");
        return "redirect:/orders";
    }
    @RequestMapping("updateInfo")
    public String updateOrder(@RequestParam("orderId") int id, Model model){
        Order order = orderService.getOrder(id);
        model.addAttribute("order", order);
        return "update-order-info";
    }
    @RequestMapping("confirmOrder")
    public String confirmOrder(@RequestParam("orderId") int id){
        Order order = orderService.getOrder(id);
        orderService.confirmOrder(order);
        messagingTemplate.convertAndSend("/topic/orders", "update");
        return "redirect:/orders";
    }
    @RequestMapping("deleteOrder")
    public String deleteOrder(@RequestParam("orderId") int id){
        orderService.deleteOrder(id);
        messagingTemplate.convertAndSend("/topic/orders", "update");
        return "redirect:/orders";
    }
}
