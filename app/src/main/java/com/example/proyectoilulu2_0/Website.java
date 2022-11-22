package com.example.proyectoilulu2_0;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Website extends AppCompatActivity {

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        textview = findViewById(R.id.textView);

        try {

            int numArchivo = getIntent().getExtras().getInt("numArchivo");
            Json json = new Json();

            BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + ".txt")));
            String lineaTexto = file.readLine();
            com.example.myslash.Info datos = json.leerJson(lineaTexto);
            file.close();

            textview.setText("Welcome " + datos.getFirstName());
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
            new Handler( ).postDelayed(new Runnable() {
                @Override
                public void run(){
                    Intent intent = new Intent( Website.this, com.example.myslash.ListMain.class);
                    startActivity( intent );
                }
            } , 4000 );
        }catch(Exception e){}
    }
}