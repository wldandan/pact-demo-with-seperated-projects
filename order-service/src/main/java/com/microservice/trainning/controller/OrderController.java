package com.microservice.trainning.controller;

import com.microservice.trainning.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> orders() {
        return new ResponseEntity<>(Arrays.asList(new Order(36), new Order(50)), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> order(@PathVariable("id") int id) {
        return new ResponseEntity<>(new Order(id), HttpStatus.OK);
    }

}
