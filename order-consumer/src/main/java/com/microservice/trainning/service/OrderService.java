package com.microservice.trainning.service;

import com.microservice.trainning.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderService {

    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public OrderService(@Value("${order_service_url}") String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
    }

    public Order getOrder(int id) {
        return getRemoteOrder(id);
    }

    public List<Order> getOrders(){
        return getRemoteOrders();
    }

    private List<Order> getRemoteOrders() {
        ParameterizedTypeReference<List<Order>> responseType = new ParameterizedTypeReference<List<Order>>() {};
        return restTemplate.exchange(url + "/orders", HttpMethod.GET, null, responseType).getBody();
    }

    private Order getRemoteOrder(int id) {
        return restTemplate.getForObject(url + "/orders/" + id, Order.class);
    }

}
