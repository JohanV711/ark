package com.cumn.ark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {
    private List<Activity> activities;
    private Context context;

    public ActivityAdapter(Context context, List<Activity> activities) {
        this.context = context;
        this.activities = activities;
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView activityTitle;
        TextView activityDescription;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            activityTitle = itemView.findViewById(R.id.textViewNameActivity);
            activityDescription = itemView.findViewById(R.id.textViewDescripcionActividad);
        }
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pet, parent, false);
        return new ActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        Activity activity = activities.get(position);
        holder.activityTitle.setText(activity.getTitle());
        holder.activityDescription.setText(activity.getDescription());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}