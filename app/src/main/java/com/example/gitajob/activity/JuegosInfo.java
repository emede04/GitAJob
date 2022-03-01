package com.example.gitajob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gitajob.R;

public class JuegosInfo extends AppCompatActivity {
    private TextView appid;
    private ImageView foto;
    private TextView nombre;
    private TextView horasjugadas;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_juegos_info);
        id = initComponents();








    }



    public String initComponents(){
        foto = (ImageView) findViewById(R.id.iconoVideojuego);
        nombre = (TextView) findViewById(R.id.nombre);
        horasjugadas = (TextView) findViewById(R.id.horastotales);
        appid = (TextView) findViewById(R.id.txtappid);
        Intent i = getIntent();
        String f = i.getStringExtra("icono");
        String n = i.getStringExtra("nombre");
        String h = i.getStringExtra("horas");
        String id = i.getStringExtra("id");
            //cargamos los datos

        Glide.with(getApplicationContext())
                .load(f)
                .into(foto);

        nombre.setText(n);
        horasjugadas.setText(h);
        appid.setText(id);



        return id;
    }

    private class taskGameNews extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

    private class taskGetGlobalAchivement extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }




}