package com.microservice.trainning;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.model.PactFragment;
import com.microservice.trainning.model.Order;
import com.microservice.trainning.service.OrderService;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 8080, this);

    OrderService orderClient = new OrderService("http://localhost:8080");

    List<Order> orderResults = Arrays.asList(new Order(24, "order 24"), new Order(50, "order 50"));


    @Pact(state="WhenOrderListAvailable", provider="orderProvider", consumer="orderConsumer")
    public PactFragment createorderAvaliableFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder.uponReceiving("a request to retrieve orders")
                .path("/orders")
                .method("GET")

                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("[{\"id\":24,\"title\":\"order 24\"}, {\"id\":50,\"title\":\"order 50\"}]").toFragment();
    }

    @Pact(state="WhenOrderDetailAvailable", provider="orderProvider", consumer="orderConsumer")
    public PactFragment createorderNotAvaliableFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder.uponReceiving("a request to get order with id 24")
                .path("/orders/24")
                .method("GET")

                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("{\"id\":24,\"title\":\"order 24\"}").toFragment();
    }

    @Test
    @PactVerification("WhenOrderListAvailable")
    public void testGetAvaliableorders() {
        assertEquals(orderClient.getOrders(),
                     Arrays.asList(new Order(1),
                                   new Order(24),
                                   new Order(50),
                                   new Order(100)));
    }

    @Test
    @PactVerification("WhenOrderDetailAvailable")
    public void testGetAvaliableorder() {
        assertEquals(orderClient.getOrder(24), new Order(24));

    }
}