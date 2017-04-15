package com.microservice.trainning.controller;

import com.microservice.trainning.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class EventController {

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> foos() {
        return new ResponseEntity<>(Arrays.asList(new Event(24), new Event(50)), HttpStatus.OK);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public ResponseEntity<Event> foo(@PathVariable("id") int id) {
        return new ResponseEntity<>(new Event(id), HttpStatus.OK);
    }

}
