package com.cumn.ark;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    private EditText edadEditText;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        back = findViewById(R.id.backMascotaRegistro);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Inicializar Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        nombreEditText = findViewById(R.id.name);
        tipoEditText = findViewById(R.id.tipo);
        razaEditText = findViewById(R.id.raza);
        generoEditText = findViewById(R.id.genero);
        pesoEditText = findViewById(R.id.peso);
        edadEditText = findViewById(R.id.edad);

        Button selectImageButton = findViewById(R.id.btn_photo);
        selectImageButton.setOnClickListener(v -> openImagePicker());

        Button registerButton = findViewById(R.id.btn_register);
        registerButton.setOnClickListener(v -> saveDataToFirestore());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), REQUEST_IMAGE_PICK);
    }

    private void saveDataToFirestore() {
        if (imageUri != null) {
            // Subir la imagen al almacenamiento de Firebase
            uploadImageToStorage();
        } else {
            Toast.makeText(this, "Selecciona una imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadImageToStorage() {
        // Obtener una referencia al almacenamiento de Firebase
        StorageReference storageRef = storage.getReference().child("img").child("mascota_" + System.currentTimeMillis());

        // Subir la imagen al almacenamiento
        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Si la subida de la imagen es exitosa, obtener la URL de descarga
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        savePetData(imageUrl);
                    }).addOnFailureListener(e -> Toast.makeText(mascota.this, "Error al obtener la URL de la imagen", Toast.LENGTH_SHORT).show());
                })
                .addOnFailureListener(e -> Toast.makeText(mascota.this, "Error al subir la imagen", Toast.LENGTH_SHORT).show());
    }

    private void savePetData(String imageUrl) {
        // Obtener el ID del usuario actualmente autenticado
        String usuarioId = mAuth.getCurrentUser().getUid();

        // Obtener los valores ingresados por el usuario
        String nombre = nombreEditText.getText().toString();
        String tipo = tipoEditText.getText().toString();
        String raza = razaEditText.getText().toString();
        String genero = generoEditText.getText().toString();
        String peso = pesoEditText.getText().toString();
        String edad = edadEditText.getText().toString();

        // Validar que todos los campos estén completos
        if (nombre.isEmpty() || tipo.isEmpty() || raza.isEmpty() || genero.isEmpty() || peso.isEmpty() || edad.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un nuevo objeto mascota con los datos y la URL de la imagen
        Map<String, Object> mas = new HashMap<>();
        mas.put("nombre", nombre);
        mas.put("tipo", tipo);
        mas.put("raza", raza);
        mas.put("genero", genero);
        mas.put("peso", peso);
        mas.put("edad", edad);
        mas.put("imagenURL", imageUrl);
        mas.put("usuarioID", usuarioId); // Agregar el ID del usuario

        // Guardar los datos de la mascota en la colección "mascotas" en Firestore
        db.collection("prueba")
                .add(mas)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(mascota.this, "Mascota registrada exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mascota.this, profile.class);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("tipo", tipo);
                    intent.putExtra("raza", raza);
                    intent.putExtra("genero", genero);
                    intent.putExtra("peso", peso);
                    intent.putExtra("edad", edad);
                    intent.putExtra("imagenURL", imageUrl);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(mascota.this, "Error al ingresar mascota", Toast.LENGTH_SHORT).show();
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