package com.cumn.ark;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class inicio extends AppCompatActivity {
    Button buttonReg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        buttonReg = findViewById(R.id.btn_register);
        buttonReg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), profile.class);
                startActivity(intent);
                finish();
            }

        });

    }
}
