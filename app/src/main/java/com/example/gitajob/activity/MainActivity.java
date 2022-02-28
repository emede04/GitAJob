package com.example.gitajob.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gitajob.R;
import com.example.gitajob.adaptador.Adaptador;
import com.example.gitajob.io.HttpSteam;
import com.example.gitajob.modelos.Game;
import com.example.gitajob.modelos.GamesOwned;
import com.example.gitajob.modelos.PlayerSummaries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String useractual ="";   //saco y hago accesible la iddesteam para los metodos.
    HttpSteam conexion = new HttpSteam();  //creo mi conexion
    //declaro las variable
    PlayerSummaries usuario;
    ImageView avatar;
    TextView user;
    TextView steamid;
    TextView state;
    TextView realname;

    GamesOwned gamesOwned;
    RecyclerView.LayoutManager modelo;
    public RecyclerView listaJuegos;
    public Adaptador adaptador;
    public Game miJuego;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        useractual = getIntent().getExtras().getString("clave de steam");

        new taskPlayerSummaries().execute("GET", "");
        new taskGetGames().execute("GET","");


    }


    @Override
    public void onClick(View view) {
            view.getId();
    }
    //paso los datos de mi mainactivtiy a async task
    public String getUseractual() {

        return useractual;
    }

    //AsyncsCorrespondientes
    //GetPlayerSummaries
    private  class taskPlayerSummaries extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String resultado = "";
            switch (strings[0]) {
                case "GET":
                    resultado = conexion.cargarUsuarioBase(getUseractual());
                    break;
                case "POST":
                    resultado = Integer.toString(conexion.post(strings[1],strings[2]));
                    break;

                }

            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if (!s.isEmpty()){
                    Log.d("D", "se cargan los datos de profile:");
                    JSONObject jsonObj = new JSONObject(s);
                    JSONObject objeto1 = jsonObj.getJSONObject("response");
                    JSONArray objeto2 = objeto1.getJSONArray("players");
                    //Pido perdon por el caos pero es que me he estado riendo un buen rato intentanto hacer que esto funcionara lol
                    Iterator iterador = objeto1.keys();
                    JSONArray arraydemiuser = new JSONArray();

                    while (iterador.hasNext()){
                        String llave = (String) iterador.next();
                        Log.d(" mio",llave);
                        arraydemiuser = objeto2;
                    }

                    //  api compuesta por un objeto con un array de objetos dentro, por lo que necesitamos
                    for(int i=0; i<arraydemiuser.length();){
                        String steamid = arraydemiuser.getJSONObject(i).getString("steamid");
                        String communityvisibilitystate = arraydemiuser.getJSONObject(i).getString("communityvisibilitystate");
                        String personaname = arraydemiuser.getJSONObject(i).getString("personaname");
                        String avatarfull = arraydemiuser.getJSONObject(i).getString("avatarfull");
                        String realname = arraydemiuser.getJSONObject(i).getString("realname");
                        System.out.println(avatarfull);
                        usuario = new PlayerSummaries(steamid,communityvisibilitystate,personaname,avatarfull,realname);
                        //guardamos el objeto como atributo para que puedan interactural todos los metodos de la clase
                        //da null por un motivo que no entiendo si lo saco de aqui pero deberia entenderlo es dura la vida gente
                        Glide.with(MainActivity.this)
                                //esto es una biblioteca donde subir icones svg
                                .load(avatarfull).fitCenter()
                                .error(R.drawable.ic_launcher_foreground)
                                .into(avatar);

                        cargarUser();

                        i++;
                    }
                    //Cuando se obtienen todos los campeones, debemos avisar al adaptador para informar
                    // de que debe actualizarse
                    //adaptador.notifyDataSetChanged();
                    Log.d("mio", "salgo del on post en Profile");
                }
            }
            catch (JSONException e){
                System.out.println(e);
            }
        }



    }




    //GetOwnedGames
    private class taskGetGames extends AsyncTask<String, Void, String> {
        ArrayList<Game> listaGames = new ArrayList<>();
        String numero ="";
        private String appid;
        private String name;
        private String playtime_forever;
        private String img_icon_url;
        private String img_logo_url;


        @Override
        protected String doInBackground(String... strings) {
            Log.d("mio","entro en taskedGames");
            String resultado = "";
            switch (strings[0]) {
                case "GET":
                    resultado = conexion.cargarJuegos(getUseractual());
                    break;
                case "POST":
                    resultado = Integer.toString(conexion.postGamesOwned(strings[1],strings[2]));
                    break;
                //ver el post execute que he borrao
            }

            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if (!s.isEmpty()){
                    Log.d("D", "se cargan datos de los juegos"+s);
                    JSONObject respuesta = new JSONObject(s);
                    JSONObject objeto1 = respuesta.getJSONObject("response");
                    JSONArray ArrayDeJuegos;
                    ArrayDeJuegos = objeto1.getJSONArray("game");
                    //Pido perdon por el caos pero es que me he estado riendo un buen rato intentanto hacer que esto funcionara y ha sido de puta chiripa lol
                    Iterator iterador = objeto1.keys();
                    numero ="";

                    System.out.println(ArrayDeJuegos.length());
                    while (iterador.hasNext()){

                        String llave = (String) iterador.next();
                        Log.d(" mio","llave: "+ llave);
                        numero =   objeto1.getString("game_count");

                        for(int i=0; i<ArrayDeJuegos.length();i++){
                            //reccorro mi array de juegos
                            JSONObject miobjeto = ArrayDeJuegos.getJSONObject(i);
                        for(int j=0; j<miobjeto.length() ;j++){
                         appid = (String) miobjeto.getString("appid");
                         name = (String) miobjeto.getString("name");
                         playtime_forever =  miobjeto.getString("playtime_forever");
                         img_icon_url = (String)miobjeto.getString("img_icon_url");
                         img_logo_url = miobjeto.getString("img_logo_url");
                        //guardamos el objeto como atributo para que puedan interactural todos los metodos de la clase
                        //da null por un motivo que no entiendo si lo saco de aqui pero deberia entenderlo es dura la vida gente

                        miJuego = new Game(appid,name,playtime_forever,img_icon_url,img_logo_url);

                         listaGames = new ArrayList<>();
                        listaGames.add(miJuego);


                        i++;
                    }}}gamesOwned = new GamesOwned(numero,listaGames);
                    gamesOwned = new GamesOwned(numero,listaGames);
                    System.out.println("EA"+gamesOwned.getNumerosdejuegos());
                    cargarListaJuegos();


                    //Cuando se obtienen todos los campeones, debemos avisar al adaptador para informar
                    // de que debe actualizarse
                    //adaptador.notifyDataSetChanged();
                    Log.d("mio", "salgo del array Array Juegos");
                }
            }
            catch (JSONException e){
                System.out.println("hola soy el error"+e);
            }
        }



    }








    //metodos del async
    public void cargarUser(){
        Log.d("mio","metodo para cargar los usuarios en la vista ");
        user.setText(usuario.getPersonaname());
        state.setText(usuario.getPersonastate());
        realname.setText(usuario.getRealname());
        steamid.setText(usuario.getSteamid());

    }

    public void cargarListaJuegos(){
    listaJuegos = findViewById(R.id.rListaVideojuegos);
    adaptador = new Adaptador(gamesOwned,this); //cargo el adaptador con el objeto
    listaJuegos.setAdapter(adaptador);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
    listaJuegos.setLayoutManager(gridLayoutManager);




    }

    //metodo del mainactivity para inicializar los componetes
    public void initComponents(){
        gamesOwned = new GamesOwned();
        usuario = new PlayerSummaries();
        avatar = findViewById(R.id.userphoto);
        user = findViewById(R.id.user);
        steamid = findViewById(R.id.steamid);
        realname = findViewById(R.id.realname);
        state = findViewById(R.id.state);
    }









}