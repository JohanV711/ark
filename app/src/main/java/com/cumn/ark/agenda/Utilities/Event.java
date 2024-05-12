package com.cumn.ark.agenda.Utilities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event{
    private final String title;
    private final String description;
    private final String hour;

    public Event(String title, String description, String hour) {
            this.title = title;
            this.description = description;
            this.hour = hour;
    }

    //Getters
    public String getTitle() {
        return title;
    }
    public String getTime() {
            return hour;
    }
    public String getDescription() {
        return description;
    }
}