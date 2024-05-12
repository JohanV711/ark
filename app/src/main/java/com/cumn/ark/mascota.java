package com.cumn.ark;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class mascota extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;
    private Uri imageUri;

    private EditText nombreEditText;
    private EditText tipoEditText;
    private EditText razaEditText;
    private EditText generoEditText;
    private EditText pesoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);

        nombreEditText = findViewById(R.id.name);
        tipoEditText = findViewById(R.id.tipo);
        razaEditText = findViewById(R.id.raza);
        generoEditText = findViewById(R.id.genero);
        pesoEditText = findViewById(R.id.peso);

        Button selectImageButton = findViewById(R.id.btn_select_image);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        Button registerButton = findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPet();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    private void registerPet() {
        if (imageUri != null) {
            // Guardar la mascota en Firestore
            savePetData();
        } else {
            // No se ha seleccionado ninguna imagen
            Toast.makeText(this, "Selecciona una imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePetData() {
        // Obtener una referencia a la base de datos de Firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtener los valores ingresados por el usuario
        String nombre = nombreEditText.getText().toString();
        String tipo = tipoEditText.getText().toString();
        String raza = razaEditText.getText().toString();
        String genero = generoEditText.getText().toString();
        String peso = pesoEditText.getText().toString();

        // Crear un nuevo objeto mascota con los datos
        Map<String, Object> mas = new HashMap<>();
        mas.put("nombre", nombre);
        mas.put("tipo", tipo);
        mas.put("raza", raza);
        mas.put("genero", genero);
        mas.put("peso", peso);

        // Agregar el objeto mascota a la colección "prueba" en Firestore
        db.collection("prueba")
                .add(mas)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(mascota.this, "Mascota registrada exitosamente", Toast.LENGTH_SHORT).show();
                        // Redirigir a profile.class
                        Intent intent = new Intent(mascota.this, profile.class);
                        startActivity(intent);
                        finish(); // Finalizar la actividad actual si ya no es necesaria en la pila de actividades
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mascota.this, "Error al registrar la mascota", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            // La imagen ha sido seleccionada
            imageUri = data.getData();
        }
    }
}



