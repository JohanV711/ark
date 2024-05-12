package com.cumn.ark;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.cumn.ark.agenda.CalendarActivity;
import com.cumn.ark.auth.Login;
import com.cumn.ark.locationService.MainActivityMaps;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
/*ola pruea*/
    FirebaseUser user;
    FirebaseAuth auth;
    TextView textView;

    ImageButton buttonSignOut, buttonCalendar, buttonMaps;
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

            buttonSignOut.setOnClickListener(view -> {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            });

            buttonCalendar.findViewById(R.id.btn_calendar);
            buttonCalendar.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
                finish();
            });

            buttonMaps.findViewById(R.id.btn_map);
            buttonMaps.setOnClickListener(view ->{
                Intent intent = new Intent(getApplicationContext(), MainActivityMaps.class);
                startActivity(intent);
                finish();
            });

        }

    }
}