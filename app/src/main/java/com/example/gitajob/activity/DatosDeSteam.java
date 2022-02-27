package com.example.gitajob.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gitajob.steamdata.Constantes;
import com.example.gitajob.R;

import java.net.HttpURLConnection;
import java.net.URL;


public class DatosDeSteam extends AppCompatActivity implements View.OnClickListener {

    private Button bVerifica;
    private EditText supuestaclavesteam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_steam);
        bVerifica = findViewById(R.id.bVerificar);
        supuestaclavesteam = findViewById(R.id.clavedesteam);
        bVerifica.setOnClickListener(this);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public void onClick(View v) {
    boolean quepasa;
        switch (v.getId()/*to get clicked view id**/) {
            case R.id.bVerificar:
                String clave = supuestaclavesteam.getText().toString().trim();


              quepasa=  atajo(clave);

              if(quepasa){
                  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                  intent.putExtra("clave de steam",clave);
                  startActivity(intent);

                  finish(); }

              else{
                clave = Constantes.getSteamProfileDelRandomConDinero();
                atajo(clave);
                  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                  startActivity(intent);
                  finish(); }
                break;
            default:

        }


        }




    public boolean atajo (String clave){
        String parseao = Constantes.getSteamProfileUrlStart()+ clave +Constantes.getSteamProfileUrlEnd();
        HttpURLConnection http;

        try {
            System.out.println(parseao);
            URL url = new URL(parseao);
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");

          if (http.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                Log.d("mio","datos de steam :ea error las aplicacion correcta");
              AlertDialog.Builder builder = new AlertDialog.Builder(this);
              builder.setMessage("Esa id de steam no existe")
                      .setTitle("Error")
                      .setIcon(android.R.drawable.ic_delete);
              builder.show();
                return false;
            } else {

                Log.d("mio","datos de steam se obtiene las api correcta");
              Toast.makeText(this, "conectados", Toast.LENGTH_SHORT).show();
                return true;
            }


        } catch (Exception ex) {

            System.out.println("ni ha entrado tu");
            System.out.println("error :"+ex);
            System.out.println("error :"+ex);
            return false;

        }



    }

}
