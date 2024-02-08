package com.example.thirtyonestudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LandingPage extends AppCompatActivity {

    Button btnMulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        btnMulai = findViewById(R.id.btn_mulai);

        btnMulai.setOnClickListener(view -> {
            Intent mulai = new Intent(getApplicationContext(), Login.class);
            startActivity(mulai);
        });
    }

}