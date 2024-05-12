package com.cumn.ark;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.locationService.MainActivityMaps;
public class profile extends AppCompatActivity {
    ImageView img;
    ListView listView;
    Button  buttonMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        img = findViewById(R.id.imageView2);
        buttonMaps = findViewById(R.id.btn_map); // Inicializar el botón correctamente

        buttonMaps.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), MainActivityMaps.class);
            startActivity(intent);
            finish();
        });

        img.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), inicio.class);
            startActivity(intent);
            finish();
        });
    }
}

