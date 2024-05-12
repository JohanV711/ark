package com.cumn.ark.agenda;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.cumn.ark.R;
import com.cumn.ark.agenda.Utilities.Event;
import com.cumn.ark.agenda.Utilities.TimePicker;
import com.cumn.ark.auth.Login;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

public class CalendarActivity extends AppCompatActivity {

    private FloatingActionButton pickTimeBtn;
    CalendarView calendar;
    TextView time;
    private TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initWidgets();

        pickTimeBtn.setOnClickListener(v -> {
        createEventDialog();
        });
    }



    private void initWidgets(){
        pickTimeBtn = findViewById(R.id.addEvent);
    }

    private void addHourEvent(){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(CalendarActivity.this,
                (view, hourOfDay, minuteOfDay) -> {
                    String hourText = hourOfDay + ":" + minuteOfDay;

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
    private void createNewEvent(View view, AlertDialog alertDialog){
        TextInputEditText title = view.findViewById(R.id.title);
        TextInputEditText description = view.findViewById(R.id.description);
        Button cancelButton = view.findViewById(R.id.cancel);
        Button acceptButton = view.findViewById(R.id.ok);

        cancelButton.setOnClickListener(v -> alertDialog.dismiss());
        acceptButton.setOnClickListener(v -> {
            String titulo = String.valueOf(title.getText());
            if (TextUtils.isEmpty(titulo)) {
                Toast.makeText(CalendarActivity.this, "Inserte el título del evento", Toast.LENGTH_SHORT).show();
            }
            else{
                alertDialog.dismiss();
                addHourEvent();

            }

        });

    }
}
