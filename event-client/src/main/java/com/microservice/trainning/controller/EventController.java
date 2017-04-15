package com.microservice.trainning.controller;

import com.microservice.trainning.service.EventService;
import com.microservice.trainning.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventService eventClient;

    @RequestMapping("/events")
    public List<Event> events() {
        return eventClient.getEvents();
    }

    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }

}
