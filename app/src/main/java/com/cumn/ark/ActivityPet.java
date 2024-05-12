package com.cumn.ark;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ActivityPet extends AppCompatActivity {
    private FloatingActionButton buttonActivity;
    private RecyclerView recyclerView;
    private List<Activity> activityList;

    @Override
    protected void onCreate(Bundle instancia) {
        super.onCreate(instancia);
        setContentView(R.layout.list_activity);
        iniciar();

        buttonActivity.setOnClickListener(v -> createActivity());
        visualizarActividad(activityList);
    }

    private void visualizarActividad(List<Activity> activities) {
        ActivityAdapter activityAdapter = new ActivityAdapter(this, activities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(activityAdapter);
    }

    private void iniciar() {
        buttonActivity = findViewById(R.id.buttonActivity);
        recyclerView = findViewById(R.id.ListActivity);
        activityList = new ArrayList<>();
    }

    private void createActivity() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_cell, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        createNewActivity(view, alertDialog);
    }

    private void createNewActivity(View view, AlertDialog alertDialog) {
        TextInputEditText titleActividad = view.findViewById(R.id.textViewNameActivity);
        TextInputEditText descriptionActividad = view.findViewById(R.id.textViewDescripcionActividad);
        Button cancelButton = view.findViewById(R.id.calendarView);
        Button acceptButton = view.findViewById(R.id.aceptaId);

        cancelButton.setOnClickListener(v -> alertDialog.dismiss());
        acceptButton.setOnClickListener(v -> {
            String titulo = String.valueOf(titleActividad.getText());
            String descripcion = String.valueOf(descriptionActividad.getText());
            if (TextUtils.isEmpty(titulo)) {
                Toast.makeText(ActivityPet.this, "Introduzca una nueva actividad", Toast.LENGTH_SHORT).show();
            } else {
                alertDialog.dismiss();
                Activity act = new Activity();
                act.setTitle(titulo);
                act.setDescription(descripcion);
            }
        });

    }
}
