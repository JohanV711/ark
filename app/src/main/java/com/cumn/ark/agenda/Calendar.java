package com.cumn.ark.agenda;

import static com.cumn.ark.agenda.CalendarUtils.daysInMonthArray;
import static com.cumn.ark.agenda.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cumn.ark.R;

public class Calendar extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText, back, forward, scheduleText;
    private RecyclerView calendarRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initWidgets();
        buttons();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets()
    {
        back = findViewById(R.id.back);
        forward = findViewById(R.id.forward);
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.date);
        scheduleText = findViewById(R.id.btn_schedule);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray();

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }
    private void buttons(){

        back.setOnClickListener(this::previousMonthAction);
        forward.setOnClickListener(this::nextMonthAction);
        scheduleText.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Schedule.class);
            startActivity(intent);
            finish();
        });
    }
    public void weeklyAction(View view)
    {
        startActivity(new Intent(this, WeekViewActivity.class));
    }
}








