package com.cumn.ark.agenda.Utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cumn.ark.R;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{
    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    @NonNull
    public View getView(int position, @Nullable View eventView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        if (eventView == null) {
            eventView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }

        TextView eventTitle = eventView.findViewById(R.id.textViewNombreEvento);
        TextView eventTime = eventView.findViewById(R.id.textViewHora);
        TextView eventDescription = eventView.findViewById(R.id.textViewDescripcionEvento);

        if (event != null) {
            eventTitle.setText(event.getTitle());
            eventTime.setText(event.getTime());
            eventDescription.setText(event.getDescription());
        }

        return eventView;
    }
}
