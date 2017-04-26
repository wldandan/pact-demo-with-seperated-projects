package com.microservice.trainning.controller;

import com.microservice.trainning.model.Order;
import com.microservice.trainning.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> orders() {
        return orderService.getOrders();
    }


    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }

}
