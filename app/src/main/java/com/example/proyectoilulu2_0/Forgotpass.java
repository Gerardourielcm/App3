package com.example.proyectoilulu2_0;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Forgotpass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
    }

    public void RecuperarContraseña (View v){
        EditText userName = (EditText) findViewById(R.id.editTextFPName);
        EditText Mail = (EditText) findViewById(R.id.editTextFPMail);

        String mensaje = "";

        if("".equals(userName.getText().toString()) || "".equals(Mail.getText().toString()))
        {
            mensaje = "Falta un Parametro";
        }else{
            boolean TipoCorreo = false;
            String Correo = "";
            for(int x = 0 ; x < Mail.length(); x++){
                if(Mail.getText().charAt(x) == '@'){
                    for(int y = x ; y < Mail.length(); y++){
                        Correo = Correo + Mail.getText().charAt(y);
                    }
                    if("@gmail.com".equals(Correo) || "@hotmail.com".equals(Correo) || "@outlook.com".equals(Correo)){
                        TipoCorreo = true;
                    }
                    break;
                }
            }
            if(userName.length() > 20 || Mail.length() > 25 || TipoCorreo == false){
                mensaje = "Parametro Erroneo";
                if(userName.length() > 20){mensaje = "Nombre de Usuario Muy Largo";}
                if(Mail.length() > 25){mensaje = "Correo Muy Largo";}
                if(TipoCorreo == false){mensaje = "Correo Invalido";}
            }else {
                try {
                    Json json = new Json();

                    boolean BucleArchivo = true;
                    int x = 1;
                    int numArchivo = 0;
                    while (BucleArchivo) {
                        File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + x + ".txt");
                        if(Cfile.exists()) {
                            Des myDes = new Des();

                            BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                            String lineaTexto = file.readLine();
                            file.close();

                            Info datos = json.leerJson(lineaTexto);
                            String valorName = myDes.desCifrar(datos.getUserName());
                            String valorMail = myDes.desCifrar(datos.getMail());

                            if (valorName.equals(userName.getText().toString()) & valorMail.equals(Mail.getText().toString())) {
                                mensaje = "Usuario Encontrado";
                                BucleArchivo = false;
                            } else {
                                x = x + 1;
                            }
                        }else{
                            mensaje = "Usuario no Encontrado";
                            BucleArchivo = false;
                        }
                    }

                    if("Usuario Encontrado".equals(mensaje)){
                        String text = "Le enviamos este correo para recuperar su contraseña, si usted no lo solicito ignore este mensaje, y si lo envio haga clic aqui --->";
                        if( text == null || text.length() == 0 )
                        {
                            mensaje = "Correo is null";
                        }
                        if( sendInfo( text ) )
                        {
                            mensaje = "Se envío el Correo";
                        }
                        else
                        {
                            mensaje = "Error en el envío del Correo";
                        }
                    }

                } catch (Exception e) {
                    mensaje = "Error en el Archivo";
                }
            }
        }
        Toast.makeText(Forgotpass.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void Volver (View v){
        Intent intent = new Intent (Forgotpass.this, Login.class);
        startActivity( intent );
    }

    public boolean sendInfo( String text )
    {
        String TAG = "App";
        JsonObjectRequest jsonObjectRequest = null;
        JSONObject jsonObject = null;
        String url = "https://us-central1-nemidesarrollo.cloudfunctions.net/function-test";
        RequestQueue requestQueue = null;
        if( text == null || text.length() == 0 )
        {
            return false;
        }
        jsonObject = new JSONObject( );
        try
        {
            jsonObject.put("hola" , "mundo" );
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.i(TAG, response.toString());
            }
        } , new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        } );
        requestQueue = Volley.newRequestQueue( getBaseContext() );
        requestQueue.add(jsonObjectRequest);

        return true;
    }
}