package com.microservice.trainning.service;

import com.microservice.trainning.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventService {

    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public EventService(@Value("${provider_url}") String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
    }

    public Event getEvent(int id) {
        return getRemoteEvent(id);
    }

    public List<Event> getEvents(){
        ArrayList<Event> events = new ArrayList<Event>();
        Event eventHeader = new Event(1, "event 1");

        events.add(eventHeader);
        List<Event> results = getRemoteEvents();
        if (null!= results && !results.isEmpty()){
            events.addAll(results);
        }

        Event eventFooter = new Event(100, "event 100");
        events.add(eventFooter);

        return events;
    }

    private List<Event> getRemoteEvents() {
        ParameterizedTypeReference<List<Event>> responseType = new ParameterizedTypeReference<List<Event>>() {};
        return restTemplate.exchange(url + "/events", HttpMethod.GET, null, responseType).getBody();
    }

    private Event getRemoteEvent(int id) {
        return restTemplate.getForObject(url + "/events/" + id, Event.class);
    }

}
