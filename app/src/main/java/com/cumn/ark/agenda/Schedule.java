package com.cumn.ark.agenda;

import static com.cumn.ark.agenda.CalendarUtils.daysInWeekArray;
import static com.cumn.ark.agenda.CalendarUtils.monthYearFromDate;
import static com.cumn.ark.agenda.CalendarUtils.selectedDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cumn.ark.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    TextView back, forward;
    private TextView monthYearText;
    private RecyclerView scheduleRecyclerView;
    private ListView hoursListView;
    private FloatingActionButton buttonEvent;

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateOptionsMenu(menu);
        setContentView(R.layout.activity_schedule);
        initWidgets();
        buttons();
        CalendarUtils.selectedDate = LocalDate.now();
        setWeekView();
        setHourAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_menu, menu);
        MenuItem menuCalendar = menu.findItem(R.id.btn_calendar);
        menuCalendar.setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(Schedule.this, Calendar.class);
            startActivity(intent);
            finish();
            return true;
        });
        return true;
    }
    public void previousWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        scheduleRecyclerView.setLayoutManager(layoutManager);
        scheduleRecyclerView.setAdapter(calendarAdapter);
    }

    private void buttons(){

        back.setOnClickListener(this::previousWeekAction);
        forward.setOnClickListener(this::nextWeekAction);
        buttonEvent.setOnClickListener(v -> {
            Intent intent = new Intent(Schedule.this, EventEditActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initWidgets(){

        scheduleRecyclerView = findViewById(R.id.scheduleRecyclerView);
        monthYearText = findViewById(R.id.date);
        back = findViewById(R.id.back);
        forward = findViewById(R.id.forward);
        hoursListView = findViewById(R.id.hoursListView);
        buttonEvent = findViewById(R.id.btn_new_event);
    }

    private void setHourAdapter()
    {
        HourAdapter hourAdapter = new HourAdapter(getApplicationContext(), hourEventList());
        hoursListView.setAdapter(hourAdapter);
    }

    private ArrayList<HourEvent> hourEventList()
    {
        ArrayList<HourEvent> list = new ArrayList<>();

        for(int hour = 0; hour < 24; hour++)
        {
            LocalTime time = LocalTime.of(hour, 0);
            ArrayList<Event> events = Event.eventsForDateAndTime(selectedDate, time);
            HourEvent hourEvent = new HourEvent(time, events);
            list.add(hourEvent);
        }

        return list;
    }
    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date != null)
        {
            CalendarUtils.selectedDate = date;
            setWeekView();
        }
    }
}