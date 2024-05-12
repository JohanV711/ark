package com.cumn.ark.agenda.Utilities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class TimePicker {

    public void createEvent(FloatingActionButton button, Context context){

        button.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                    (view, hourOfDay, minute1) -> {
                        //String hourText = hourOfDay + ":" + minute1;
                        //selectedTimeTV.setText(hourText);
                    }, hour, minute, false);
            timePickerDialog.show();
        });
    }
}
//, TextView selectedTimeTV