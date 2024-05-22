package com.cumn.ark.agenda.Utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class Event{

    private  String title;
    private  String description;
    private String hour;

    private int dayOfMonth;
    public int month;
    private int year;


    public Event() {

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


    public int getDayOfMonth() {
        return dayOfMonth;
    }
    public int getMonth() {
        return month;
    }
    public int getYear(){
        return year;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
    //Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }


}