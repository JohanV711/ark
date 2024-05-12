package com.cumn.ark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.agenda.schedule.prueba;
public class inicio extends AppCompatActivity {
    Button buttonReg;
    Button buttonCalendar;
    ImageView img;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        buttonCalendar = findViewById(R.id.btn_calendar);
        buttonReg = findViewById(R.id.btn_register);
        img=findViewById(R.id.ajuste);
        buttonReg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), mascota.class);
                startActivity(intent);
                finish();
            }

        });
        buttonCalendar.findViewById(R.id.btn_calendar);
        buttonCalendar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), prueba.class);
            startActivity(intent);
            finish();
        });

        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), profile.class);
                startActivity(intent);
                finish();
            }

        });

    }
}
