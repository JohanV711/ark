package com.cumn.ark;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.graphics.drawable.BitmapDrawable;


import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.locationService.MainActivityMaps;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class profile extends AppCompatActivity {
    ImageView img;
    Button buttonMaps;

    // Agregar variables para mostrar los datos de la mascota
    TextView nombreTextView, edadTextView, generoTextView, razaTextView;
    LinearLayout segundoLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Obtener referencias de los elementos de la interfaz de usuario
        img = findViewById(R.id.imageView2);
        segundoLinearLayout = findViewById(R.id.fondo);
        nombreTextView = findViewById(R.id.textView2);
        edadTextView = findViewById(R.id.textView11);
        generoTextView = findViewById(R.id.textView12);
        razaTextView = findViewById(R.id.textView13);
        buttonMaps = findViewById(R.id.btn_map);

        // Obtener los datos de la mascota pasados a través del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String nombre = intent.getStringExtra("nombre");
            String edad = intent.getStringExtra("edad");
            String genero = intent.getStringExtra("genero");
            String raza = intent.getStringExtra("raza");
            String imageUrl = intent.getStringExtra("imagenURL");

            // Mostrar los datos en los TextView correspondientes
            nombreTextView.setText(nombre);
            edadTextView.setText(edad);
            generoTextView.setText(genero);
            razaTextView.setText(raza);

            // Cargar la imagen desde la URL en el LinearLayout como fondo en un AsyncTask
            new LoadImageTask().execute(imageUrl);
        }

        // Configurar el botón para ir al mapa
        buttonMaps.setOnClickListener(view -> {
            Intent mapsIntent = new Intent(getApplicationContext(), MainActivityMaps.class);
            startActivity(mapsIntent);
            finish();
        });

        // Configurar el ImageView para ir a la pantalla de inicio
        img.setOnClickListener(view -> {
            Intent inicioIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(inicioIntent);
            finish();
        });
    }

    // AsyncTask para cargar la imagen desde la URL en segundo plano
    private class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                // Establecer la imagen como fondo del segundo LinearLayout
                segundoLinearLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
            }
        }
    }
}