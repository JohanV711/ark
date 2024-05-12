package com.cumn.ark.anim;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;

public class PetRegister extends AppCompatActivity {

    private EditText etNombre, etTipo, etRaza, etGenero, etPeso;
    private MaterialButton registro;

    // Referencias de Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Inicializar Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("pets");

        // Inicializar vistas
        etNombre = findViewById(R.id.name);
        etTipo = findViewById(R.id.tipo);
        etRaza = findViewById(R.id.raza);
        etGenero = findViewById(R.id.genero);
        etPeso = findViewById(R.id.peso);
        registro = findViewById(R.id.btn_register);

        // Manejar clic en el botón de registro
        registro.setOnClickListener(v -> registrarMascota());
    }

    private void registrarMascota() {
        String nombre = etNombre.getText().toString().trim();
        String tipo = etTipo.getText().toString().trim();
        String raza = etRaza.getText().toString().trim();
        String genero = etGenero.getText().toString().trim();
        String pesoStr = etPeso.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(tipo) || TextUtils.isEmpty(raza) || TextUtils.isEmpty(genero) || TextUtils.isEmpty(pesoStr)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double peso = Double.parseDouble(pesoStr);

        // Obtener el usuario actual
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // Crear un objeto Pet con los datos ingresados
            Pet animalito = new Pet(nombre, tipo, raza, genero, peso);

            // Guardar la nueva mascota en la base de datos Firebase
            databaseReference.child(user.getUid()).push().setValue(animalito)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(PetRegister.this, "Mascota registrada exitosamente", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(PetRegister.this, "Error al registrar la mascota: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            // El usuario no está autenticado
            Toast.makeText(this, "Error: Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        etNombre.getText().clear();
        etTipo.getText().clear();
        etRaza.getText().clear();
        etGenero.getText().clear();
        etPeso.getText().clear();
    }
}
