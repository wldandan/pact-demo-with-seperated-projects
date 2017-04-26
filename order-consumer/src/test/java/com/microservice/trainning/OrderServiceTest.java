package com.microservice.trainning;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.model.PactFragment;
import com.microservice.trainning.model.Order;
import com.microservice.trainning.service.OrderService;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 8080, this);

    OrderService orderService = new OrderService("http://localhost:8080");

    private static Map<String, String> getHTTPHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        return headers;
    }

    @Pact(state="WhenOrdersAvailable", provider="orderProvider", consumer="orderConsumer")
    public PactFragment createOrdersFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder)
    {
        return builder.uponReceiving("a request to retrieve orders")
                .path("/orders")
                .method("GET")
                .willRespondWith()
                .headers(getHTTPHeaders())
                .status(200)
                .body("[{\"id\":36,\"title\":\"The order is created with ID [36]\"}, {\"id\":50,\"title\":\"The order is created with ID [50]\"}]").toFragment();
    }

    @Pact(state="WhenOneOrderExists", provider="orderProvider", consumer="orderConsumer")
    public PactFragment createOrderFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {

        PactDslJsonBody body = new PactDslJsonBody()
                .integerType("id",36)
                .stringValue("title","The order is created with ID [36]")
                .asBody();

        return builder.uponReceiving("a request to get one order with id 36")
                .path("/orders/36")
                .method("GET")
                .willRespondWith()
                .headers(getHTTPHeaders())
                .status(200)
                .body(body).toFragment();
    }

    @Test
    @PactVerification("WhenOrdersAvailable")
    public void testGetOrders() {
        assertEquals(orderService.getOrders(),
                     Arrays.asList(new Order(36),
                                   new Order(50)));
    }

    @Test
    @PactVerification("WhenOneOrderExists")
    public void testOneOrderExist() {
        assertEquals(orderService.getOrder(36), new Order(36));
    }
}