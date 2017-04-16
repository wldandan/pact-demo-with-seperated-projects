package com.microservice.trainning;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.model.PactFragment;
import com.microservice.trainning.service.EventService;
import com.microservice.trainning.model.Event;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EventServiceTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 8080, this);

    EventService eventClient = new EventService("http://localhost:8080");

    List<Event> eventResults = Arrays.asList(new Event(24, "event 24"), new Event(50, "event 50"));


    @Pact(state="WhenEventListAvailable", provider="EventProvider", consumer="EventConsumer")
    public PactFragment createEventAvaliableFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder.uponReceiving("a request to retrieve events")
                .path("/events")
                .method("GET")

                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("[{\"id\":24,\"title\":\"event 24\"}, {\"id\":50,\"title\":\"event 50\"}]").toFragment();
    }

    @Pact(state="WhenEventDetailAvailable", provider="EventProvider", consumer="EventConsumer")
    public PactFragment createEventNotAvaliableFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder.uponReceiving("a request to get event with id 24")
                .path("/events/24")
                .method("GET")

                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("{\"id\":24,\"title\":\"event 24\"}").toFragment();
    }

    @Test
    @PactVerification("WhenEventListAvailable")
    public void testGetAvaliableEvents() {
        assertEquals(eventClient.getEvents(),
                     Arrays.asList(new Event(1),
                                   new Event(24),
                                   new Event(50),
                                   new Event(100)));
    }

    @Test
    @PactVerification("WhenEventDetailAvailable")
    public void testGetAvaliableEvent() {
        assertEquals(eventClient.getEvent(24), new Event(24));

    }
}