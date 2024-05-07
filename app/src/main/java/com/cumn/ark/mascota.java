package com.cumn.ark;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.anim.Pet;
import com.google.android.material.textfield.TextInputEditText;

public class mascota extends AppCompatActivity {

    TextInputEditText Nombre, Tipo, Raza, Genero, Peso;
    Button Registrar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        //Encontrar los elementos de entrada de texto y el boton por su Id
        Nombre = findViewById(R.id.name);
        Tipo = findViewById(R.id.tipo);
        Raza = findViewById(R.id.raza);
        Genero = findViewById(R.id.genero);
        Peso = findViewById(R.id.peso);
        Registrar = findViewById(R.id.btn_register);

        //Configurar OnClickListener para el boton de regreso
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtener los valores ingresados por el usuario
                String nombre = Nombre.getText().toString().trim();
                String tipo = Tipo.getText().toString().trim();
                String raza = Raza.getText().toString().trim();
                String genero = Genero.getText().toString().trim();
                String pesoS =  Peso.getText().toString().trim();

                //Validar si todos los campos estan llenos
                if (!nombre.isEmpty() && !tipo.isEmpty() && !raza.isEmpty() && !genero.isEmpty() && !pesoS.isEmpty()) {
                    //Convertir el peso a double
                    double peso = Double.parseDouble(pesoS);
                    //Crear una instancia de la mascota con valores ingresados por el user
                    Pet mascota = new Pet(nombre,tipo,raza,genero,peso);
                    Toast.makeText(mascota.this, "Mascota registrada", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(mascota.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
