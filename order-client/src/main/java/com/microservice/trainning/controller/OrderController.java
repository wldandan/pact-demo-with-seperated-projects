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
    OrderService eventClient;

    @RequestMapping("/events")
    public List<Order> events() {
        return eventClient.getOrders();
    }

    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }

}
