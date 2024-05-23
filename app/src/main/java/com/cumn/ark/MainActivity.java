package com.cumn.ark;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.agenda.CalendarActivity;
import com.cumn.ark.auth.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button buttonReg;
    Button buttonCalendar, btnConectar;
    ImageView img, buttonSignOut;
    FirebaseUser user;
    FirebaseAuth auth;
    TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
            pressButtons();
        }
    }

    private void initWidgets(){
        buttonSignOut = findViewById(R.id.btn_signOut);
        buttonCalendar = findViewById(R.id.btn_calendar);
        buttonReg = findViewById(R.id.btn_register);
        img = findViewById(R.id.ajuste);
        textView = findViewById(R.id.user_details);
        btnConectar = findViewById(R.id.btnConectar);
    }

    private void pressButtons(){
        buttonSignOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });
        buttonReg.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), mascota.class);
            startActivity(intent);
            finish();
        });
        buttonCalendar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
            startActivity(intent);
            finish();
        });
        img.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), profile.class);
            startActivity(intent);
            finish();
        });
        btnConectar.setOnClickListener(v -> {
            String url = "http://192.168.1.101:8080/pagina.html";

            // Crear un intent implícito para abrir la página web en un navegador
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }
}