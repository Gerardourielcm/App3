package com.example.proyectoilulu2_0;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Forgotpass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
    }

    public void RecuperarContraseña (View v){
        Toast.makeText(Forgotpass.this, "Función no disponible", Toast.LENGTH_SHORT).show();
    }

    public void Volver (View v){
        Intent intent = new Intent (Forgotpass.this, com.example.proyectoilulu2_0.Login.class);
        startActivity( intent );
    }
}