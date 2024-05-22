package com.cumn.ark.agenda.Utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cumn.ark.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> events;
    private Context context;

    public EventAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle;
        TextView eventTime;
        TextView eventDescription;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.textViewNombreEvento);
            eventTime = itemView.findViewById(R.id.textViewHora);
            eventDescription = itemView.findViewById(R.id.textViewDescripcionEvento);
        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cell, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.eventTitle.setText(event.getTitle());
        holder.eventTime.setText(event.getTime());
        holder.eventDescription.setText(event.getDescription());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}