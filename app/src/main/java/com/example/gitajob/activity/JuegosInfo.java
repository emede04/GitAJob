package com.example.gitajob.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gitajob.R;
import com.example.gitajob.adaptador.AdapatadorJuegos;
import com.example.gitajob.adaptador.AdaptadorPerfil;
import com.example.gitajob.io.HttpSteam;
import com.example.gitajob.modelos.Game;
import com.example.gitajob.modelos.GamesOwned;
import com.example.gitajob.modelos.NewsForApp;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


    //clase que muestra la informacion de un juego y contiene un recycle view
    //que muestra las noticias relacionadas a ese juego,
public class JuegosInfo extends AppCompatActivity {
    private TextView appid;
    private ImageView foto;
    private TextView nombre;
    private TextView horasjugadas;


    public RecyclerView listaNotcias;
    public AdapatadorJuegos adaptadorJuegos;
    public NewsForApp noticias = new NewsForApp();
    public ArrayList<NewsForApp> aListaNoticias = new ArrayList<>();



    private String idUserActual;


    private String id;
    HttpSteam conexion = new HttpSteam();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_juegos_info);
        initComponents();
        System.out.println(id);



        new taskgetGameNews().execute("GET","");



    }

    public void initComponents(){
        foto = (ImageView) findViewById(R.id.iconoVideojuego);
        nombre = (TextView) findViewById(R.id.nombre);
        horasjugadas = (TextView) findViewById(R.id.horastotales);
        appid = (TextView) findViewById(R.id.txtappid);
        Intent i = getIntent();
        String f = i.getStringExtra("icono");
        String n = i.getStringExtra("nombre");
        String h = i.getStringExtra("horas");
        String id = i.getStringExtra("id");
        String iduser = i.getStringExtra("iduser");
            //cargamos los datos

        Glide.with(this)
                .load(f)
                .into(foto);

        nombre.setText(n);
        horasjugadas.setText(h);
        appid.setText(id);
        setId(id);
        setIdUserActual(iduser);

    }

    private class taskgetGameNews extends AsyncTask<String, Void, String>{
        private String titulo;
        private String url;
        private String author;
        private String contenido;
        private String fecha;
        @Override
        protected String doInBackground(String... strings) {
            Log.d("mio", "entro en taskgetGameNews");
            String resultado = "";
            switch (strings[0]) {
                case "GET":
                    resultado = conexion.cargarNoticias(getIdUserActual(),getId());
                    break;
                case "POST":
                    resultado = Integer.toString(conexion.postGamesOwned(strings[1], strings[2]));
                    break;

                //ver el post execute que he borrao
            }

            return resultado;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                if (!s.isEmpty()) {
                    Log.d("D", "se cargan las noticias de los juegos comprobao" + s);
                    JSONObject respuesta = new JSONObject(s);
                    JSONObject objeto1 = respuesta.getJSONObject("appnews");
                    JSONArray ArrayDeJuegos;
                    ArrayDeJuegos = objeto1.getJSONArray("newsitems");

                    for (int i = 0; i <ArrayDeJuegos.length(); i++) {
                        //reccorro mi array de juegos

                        titulo = (String) ArrayDeJuegos.getJSONObject(i).getString("title");
                        url = (String) ArrayDeJuegos.getJSONObject(i).getString("url").toString();
                        author = (String) ArrayDeJuegos.getJSONObject(i).getString("author").toString();
                        contenido = (String) ArrayDeJuegos.getJSONObject(i).getString("contents").toString();
                        fecha = (String) ArrayDeJuegos.getJSONObject(i).getString("date").toString();


                        noticias = new NewsForApp(titulo,url,author,contenido,fecha);
                        System.out.println(fecha);
                        aListaNoticias.add(noticias);
                    }
                    cargarNoticiasJuego();

                }




                Log.d("mio", "salgo del post de games sin petar");

            }

            catch (JSONException e) {
                Log.d("mio", "error en getNews");
                System.out.println("ha petado "+e);
            }
        }

    }
/*
    private class taskGetGlobalAchivement extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            Log.d("mio", "entro en taskedGlobalAchivement");
            String resultado = "";
            switch (strings[0]) {
                case "GET":
                    resultado = conexion.cargarNoticias(getIdUserActual(),getId());
                    break;
                case "POST":
                    resultado = Integer.toString(conexion.postGamesOwned(strings[1], strings[2]));
                    break;

                //ver el post execute que he borrao
            }

            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if (!s.isEmpty()) {
                    Log.d("D", "se cargan las noticias de los juegos comprobao" + s);
                    JSONObject respuesta = new JSONObject(s);
                    JSONObject objeto1 = respuesta.getJSONObject("appnews");
                    JSONArray ArrayDeJuegos;
                    ArrayDeJuegos = objeto1.getJSONArray("newsitems");

                    for (int i = 0; i <ArrayDeJuegos.length(); i++) {
                        //reccorro mi array de juegos

                        titulo = (String) ArrayDeJuegos.getJSONObject(i).get("title");
                        url = (String) ArrayDeJuegos.getJSONObject(i).get("url").toString();
                        author = (String) ArrayDeJuegos.getJSONObject(i).get("author").toString();
                        contenido = (String) ArrayDeJuegos.getJSONObject(i).get("contents").toString();
                        fecha = (String) ArrayDeJuegos.getJSONObject(i).get("date").toString();
                        //guardamos el objeto como atributo para que puedan interactural todos los metodos de la clase
                        //da null por un motivo que no entiendo si lo saco de aqui pero deberia entenderlo es dura la vida gente
                        noticias = new NewsForApp(titulo,url,author,contenido,fecha);

                        aListaNoticias.add(noticias);
                    }
                    cargarNoticiasJuego();

                }



                //Cuando se obtienen todos los campeones, debemos avisar al adaptadorPerfil para informar
                // de que debe actualizarse
                //adaptadorPerfil.notifyDataSetChanged();
                Log.d("mio", "salgo del post de games sin petar");

            }

            catch (JSONException e) {
                Log.d("mio", "error en getNews");
                System.out.println("ha petado "+e);
            }
        }
    }

    */

    public String getIdUserActual() {
        return idUserActual;
    }

    public void setIdUserActual(String idUserActual) {
        this.idUserActual = idUserActual;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void cargarNoticiasJuego(){

        listaNotcias = (RecyclerView) findViewById(R.id.rListaNoticias);
        adaptadorJuegos = new AdapatadorJuegos(aListaNoticias,this);//cargo el adaptador con la lista de los objetos
        listaNotcias.setAdapter(adaptadorJuegos);
        new ItemTouchHelper(elementotocado).attachToRecyclerView(listaNotcias);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this ,1);
        listaNotcias.setLayoutManager(gridLayoutManager);

    }

    ItemTouchHelper.SimpleCallback elementotocado = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            aListaNoticias.remove(viewHolder.getAdapterPosition());
            adaptadorJuegos.notifyDataSetChanged();
        }
    };




    }

