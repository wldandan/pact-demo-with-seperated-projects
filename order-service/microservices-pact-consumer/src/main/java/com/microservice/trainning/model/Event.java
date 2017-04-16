package com.microservice.trainning.model;

public class Event {

    private int id;
    private String title;

    public Event() {}

    public Event(int id) {
        this.id = id;
        this.title = "event " + id;
    }

    public Event(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
