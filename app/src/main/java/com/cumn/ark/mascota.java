package com.cumn.ark;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mascota extends AppCompatActivity {

    private TextInputEditText editTextNombre, editTextTipo, editTextRaza, editTextGenero, editTextPeso;
    private Button btnRegistrar;

    // Referencia a la base de datos de Firebase
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        // Inicializar Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("mascotas");

        // Obtener referencias a las vistas
        editTextNombre = findViewById(R.id.name);
        editTextTipo = findViewById(R.id.tipo);
        editTextRaza = findViewById(R.id.raza);
        editTextGenero = findViewById(R.id.genero);
        editTextPeso = findViewById(R.id.peso);
        btnRegistrar = findViewById(R.id.btn_register);

        // Configurar el evento de clic del botón de registro
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarMascota();
            }
        });
    }

    private void registrarMascota() {
        // Obtener los valores de los campos de texto
        String nombre = editTextNombre.getText().toString();
        String tipo = editTextTipo.getText().toString();
        String raza = editTextRaza.getText().toString();
        String genero = editTextGenero.getText().toString();
        String peso = editTextPeso.getText().toString();

        // Verificar si se han ingresado todos los datos
        if (nombre.isEmpty() || tipo.isEmpty() || raza.isEmpty() || genero.isEmpty() || peso.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        String userId = mAuth.getCurrentUser().getUid();
        // Crear un objeto Mascota con los datos
        mascotas mas = new mascotas(nombre, tipo, raza, genero, peso);

        // Guardar la mascota en la base de datos de Firebase
        databaseReference.child(userId).push().setValue(mas)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(mascota.this, "Mascota registrada correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mascota.this, "Error al registrar la mascota", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void limpiarCampos() {
        editTextNombre.setText("");
        editTextTipo.setText("");
        editTextRaza.setText("");
        editTextGenero.setText("");
        editTextPeso.setText("");
    }
}
