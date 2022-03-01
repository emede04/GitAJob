package com.example.gitajob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gitajob.R;
import com.example.gitajob.io.HttpSteam;
import com.example.gitajob.modelos.Game;
import com.example.gitajob.modelos.GamesOwned;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JuegosInfo extends AppCompatActivity {
    private TextView appid;
    private ImageView foto;
    private TextView nombre;
    private TextView horasjugadas;



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

        Glide.with(getApplicationContext())
                .load(f)
                .into(foto);

        nombre.setText(n);
        horasjugadas.setText(h);
        appid.setText(id);
        setId(id);
        setIdUserActual(iduser);


    }

    private class taskgetGameNews extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... strings) {
            Log.d("mio", "entro en taskedGameNews");
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
                    Log.d("D", "se cargan datos de los juegos comprobao" + s);
                    JSONObject respuesta = new JSONObject(s);
                    JSONObject objeto1 = respuesta.getJSONObject("response");
                    JSONArray ArrayDeJuegos;
                    ArrayDeJuegos = objeto1.getJSONArray("games");
                    //Pido perdon por el caos pero es que me he estado riendo un buen rato intentanto hacer que esto funcionara y ha sido de puta chiripa lol
                  /*  Iterator iterador = objeto1.keys();
                    numero = "";


                    System.out.println(ArrayDeJuegos.length());
                    while (iterador.hasNext()) {

                        String llave = (String) iterador.next();
                        numero = objeto1.getString("game_count");
                    }

                    for (int i = 0; i < ArrayDeJuegos.length(); i++) {
                        //reccorro mi array de juegos

                        appid = ArrayDeJuegos.getJSONObject(i).get("appid").toString();
                        name = (String) ArrayDeJuegos.getJSONObject(i).get("name");
                        playtime_forever = (String) ArrayDeJuegos.getJSONObject(i).get("playtime_forever").toString();
                        img_icon_url = (String) ArrayDeJuegos.getJSONObject(i).get("img_icon_url").toString();
                        img_logo_url = (String) ArrayDeJuegos.getJSONObject(i).get("img_logo_url").toString();
                        //guardamos el objeto como atributo para que puedan interactural todos los metodos de la clase
                        //da null por un motivo que no entiendo si lo saco de aqui pero deberia entenderlo es dura la vida gente
                        miJuego = new Game(appid, name, playtime_forever, img_icon_url, img_logo_url);
                        listaGames.add(miJuego);
                    }
                }
                gamesOwned = new GamesOwned(numero, listaGames);
                cargarListaJuegos();

*/

                //Cuando se obtienen todos los campeones, debemos avisar al adaptadorPerfil para informar
                // de que debe actualizarse
                //adaptadorPerfil.notifyDataSetChanged();
                Log.d("mio", "salgo del post de games sin petar :D");

            } }catch (JSONException e) {
                Log.d("mio", "error en getNews");
            }
        }
    }

    private class taskGetGlobalAchivement extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }


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




}