package com.cumn.ark;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.actividades.ActivityPet;
import com.cumn.ark.agenda.CalendarActivity;

public class inicio extends AppCompatActivity {
    Button buttonReg;
    Button buttonCalendar;
    ImageView img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        buttonCalendar = findViewById(R.id.btn_calendar);
        buttonReg = findViewById(R.id.btn_register);
        img = findViewById(R.id.ajuste);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mascota.class);
                startActivity(intent);
                finish();
            }

        });
        buttonCalendar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
            startActivity(intent);
            finish();
        });

        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), profile.class);
                startActivity(intent);
                finish();
            }

        });

        Button botonActividad = findViewById(R.id.btn_actividad);
        botonActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityPet.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnConectar = findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.1.101:8080/pagina.html";

                // Crear un intent implícito para abrir la página web en un navegador
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }
}