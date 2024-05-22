package com.cumn.ark.agenda;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cumn.ark.MainActivity;
import com.cumn.ark.R;
import com.cumn.ark.agenda.Utilities.Event;
import com.cumn.ark.agenda.Utilities.EventAdapter;
import com.cumn.ark.inicio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private FloatingActionButton pickTimeBtn;
    CalendarView calendar;

    //private EventAdapter eventAdapter;
    private ImageButton back;
    private RecyclerView recyclerView;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initWidgets();

        pickTimeBtn.setOnClickListener(v ->
                createEventDialog());
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, inicio.class);
            startActivity(intent);
            finish();
        });
        calendarListener(eventList);

    }


    private void visualizeEvent(List <Event> events) {
        EventAdapter eventAdapter = new EventAdapter(this, events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eventAdapter);
    }

    private void initWidgets() {
        pickTimeBtn = findViewById(R.id.addEvent);
        recyclerView = findViewById(R.id.eventsList);
        back = findViewById(R.id.back);
        calendar = findViewById(R.id.calendarView);
        eventList = new ArrayList<>();
    }

    private void addTimeEvent(Event event) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(CalendarActivity.this,
                (view, hourOfDay, minuteOfDay) -> {
                    String hourText = hourOfDay + ":" + minuteOfDay;
                    event.setHour(hourText);
                    event.setDayOfMonth(c.get(Calendar.DAY_OF_MONTH));
                    event.setMonth(c.get(Calendar.MONTH));
                    event.setYear(c.get(Calendar.YEAR));
                    eventList.add(event);
                    visualizeEvent(eventList);
                }, hour, minute, true);
        timePickerDialog.show();

    }

    private void createEventDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.add_event_box, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        createNewEvent(view, alertDialog);
    }

    private void createNewEvent(View view, AlertDialog alertDialog) {
        TextInputEditText title = view.findViewById(R.id.title);
        TextInputEditText description = view.findViewById(R.id.description);
        Button cancelButton = view.findViewById(R.id.cancel);
        Button acceptButton = view.findViewById(R.id.ok);

        cancelButton.setOnClickListener(v -> alertDialog.dismiss());
        acceptButton.setOnClickListener(v -> {
            String titulo = String.valueOf(title.getText());
            String descripcion = String.valueOf(description.getText());
            if (TextUtils.isEmpty(titulo)) {
                Toast.makeText(CalendarActivity.this, "Inserte el título del evento", Toast.LENGTH_SHORT).show();
            } else {
                alertDialog.dismiss();
                Event event = new Event();
                event.setTitle(titulo);
                event.setDescription(descripcion);
                addTimeEvent(event);
            }
        });

    }

    private void calendarListener(List<Event> events) {
        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            List<Event> filteredEvents = new ArrayList<>();
            for (Event event : events) {
                if (event.getDayOfMonth() == dayOfMonth && event.getMonth() == month && event.getYear() == year) {
                    filteredEvents.add(event);
                }
            }
            visualizeEvent(filteredEvents);
        });
    }

}