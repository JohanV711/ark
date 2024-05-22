package com.cumn.ark;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.locationService.MainActivityMaps;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class profile extends AppCompatActivity {
    ImageView img;
    TextView edadTextView, generoTextView, razaTextView;
    Button buttonMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Obtener referencias a los elementos de la interfaz de usuario
        img = findViewById(R.id.imageView2);
        edadTextView = findViewById(R.id.edad1);
        generoTextView = findViewById(R.id.textView12);
        razaTextView = findViewById(R.id.textView13);
        buttonMaps = findViewById(R.id.btn_map);

        // Suponiendo que tengas el ID de la mascota almacenado en una variable llamada "mascotaId"
        String mascotaId = "ID_DE_LA_MASCOTA"; // Reemplaza "ID_DE_LA_MASCOTA" con el ID real de la mascota

        // Obtener una referencia a la colección "mascotas"
        DocumentReference mascotaDocRef = FirebaseFirestore.getInstance().collection("mascotas").document(mascotaId);

        // Realizar la consulta para obtener los datos de la mascota
        mascotaDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                // La mascota existe, obtener sus datos
                String edad = documentSnapshot.getString("edad");
                String genero = documentSnapshot.getString("genero");
                String raza = documentSnapshot.getString("raza");

                // Establecer los valores obtenidos en los TextView correspondientes
                edadTextView.setText("Edad: " + edad);
                generoTextView.setText("Género: " + genero);
                razaTextView.setText("Raza: " + raza);
            } else {
                // La mascota no existe
                Toast.makeText(this, "La mascota no existe", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            // Ocurrió un error al obtener los datos de la mascota
            Toast.makeText(this, "Error al obtener los datos de la mascota: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });

        // Configurar el botón para abrir el mapa
        buttonMaps.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivityMaps.class);
            startActivity(intent);
            finish();
        });

        // Configurar el ImageView para regresar a la pantalla de inicio
        img.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), inicio.class);
            startActivity(intent);
            finish();
        });
    }
}

