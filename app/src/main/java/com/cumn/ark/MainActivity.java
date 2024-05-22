package com.cumn.ark;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.auth.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    /*ola pruea*/
    FirebaseUser user;
    FirebaseAuth auth;
    TextView textView;
    Button buttonSignOut, buttonCalendar, buttonMaps;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        buttonSignOut = findViewById(R.id.btn_signOut);
        buttonCalendar = findViewById(R.id.btn_calendar);
        buttonMaps = findViewById(R.id.btn_map);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        textView = findViewById(R.id.user_details);

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else{
            textView.setText(user.getEmail());

            buttonSignOut.findViewById(R.id.btn_signOut);
            buttonSignOut.setOnClickListener(view -> {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            });



        }

    }
}