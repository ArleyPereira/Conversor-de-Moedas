package com.example.conversormoedas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.conversormoedas.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Leva o Usuário para tela principal
        new Handler(Looper.getMainLooper()).postDelayed(this::homeApp, 3000);

    }

    // Leva o Usuário para tela principal
    private void homeApp(){
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

}