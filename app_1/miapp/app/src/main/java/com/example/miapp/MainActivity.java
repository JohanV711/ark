package com.example.miapp;
import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verificar si el usuario está autenticado
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            // Usuario autenticado, redirigir a la actividad mascota
            startActivity(new Intent(MainActivity.this, mascota.class));
        } else {
            // Usuario no autenticado, redirigir a la actividad de inicio de sesión
            startActivity(new Intent(MainActivity.this, login.class));
        }
        finish(); // Cerrar esta actividad para evitar que el usuario vuelva atrás
    }
}