package com.example.gitajob.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gitajob.R;
import com.example.gitajob.adaptador.Adaptador;
import com.example.gitajob.databinding.ActivityAdaptadorBinding;
import com.example.gitajob.io.HttpSteam;
import com.example.gitajob.modelos.GamesOwned;
import com.example.gitajob.modelos.PlayerSummaries;
import com.example.gitajob.steamdata.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String useractual ="";   //mi atributo usuario
    ArrayList<PlayerSummaries> listaResumenUsuario;  //esto sera la lista de amigos de mi usuario, juegos y noticas, ya que user solo hay uno cuando se loguea
    HttpSteam conexion = new HttpSteam();  //creo mi conexion
    //mi objeto que inicializa que pueda tener acceso a todas las constantes que necesite
    PlayerSummaries usuario;
    ImageView avatar;
    TextView user;
    TextView steamid;
    TextView state;
    TextView realname;
    GamesOwned gamesOwned;
    RecyclerView.LayoutManager modelo;
    private RecyclerView listaJuegos;
    private Adaptador adaptador;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adaptador = new Adaptador(this);
        usuario = new PlayerSummaries();
        setContentView(R.layout.activity_main);
        initComponents();
        adaptador = new Adaptador(this);
        usuario = new PlayerSummaries();
        useractual = getIntent().getExtras().getString("clave de steam");
        listaResumenUsuario = new ArrayList<>();
        new taskConexiones().execute("GET", "");

        new taskGetGames().execute("GET","");
        System.out.println(usuario.getAvatar());
        cargarUser();

    }


    @Override
    public void onClick(View view) {
            view.getId();
    }

    public String getUseractual() {
        return useractual;
    }

    public void setUseractual(String useractual) {
        this.useractual = useractual;
    }






    //TODO -
    public void cargarUser(){
        Log.d("mio","metodo para cargar los usuarios");
        System.out.println(usuario.getPersonaname());
        user.setText(usuario.getPersonaname());
        state.setText(usuario.getPersonastate());
        realname.setText(usuario.getRealname());
        steamid.setText(usuario.getSteamid());

    }

    public void cargarListaAmigos(){

    }
    public void cargarListaJuegos(){

    }
    public void cargarNotcias(){

    }

    public void initComponents(){
        adaptador = new Adaptador(this);
        usuario = new PlayerSummaries();
        avatar = (ImageView) findViewById(R.id.userphoto);
        user = (TextView) findViewById(R.id.user);
        steamid = findViewById(R.id.steamid);
        realname = findViewById(R.id.realname);
        state = findViewById(R.id.state);

    }

    public void setupRecycler(){
        listaJuegos = (RecyclerView) findViewById(R.id.rListaVideojuegos);
        listaJuegos = (RecyclerView) findViewById(R.id.rListaVideojuegos);
        adaptador = new Adaptador(gamesOwned,this);
        listaJuegos.setAdapter(adaptador);
        modelo = new LinearLayoutManager(this);
        listaJuegos.setLayoutManager(modelo);


    }


    //clase para cargar los datos del perfil de steam


    private class taskConexiones extends AsyncTask<String, Void, String> {


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
                    //ver el post execute que he borrao
            }
            System.out.println(resultado);

            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if (!s.isEmpty()){
                    Log.d("D", "DATOS: "+s);
                    JSONObject jsonObj = new JSONObject(s);
                    JSONObject objeto1 = jsonObj.getJSONObject("response");
                    System.out.println(jsonObj.toString());
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
                        String steamid = (String) arraydemiuser.getJSONObject(i).getString("steamid");
                        String communityvisibilitystate = (String) arraydemiuser.getJSONObject(i).getString("communityvisibilitystate");
                        String personaname =  (String) arraydemiuser.getJSONObject(i).getString("personaname");
                        String avatarfull = (String) arraydemiuser.getJSONObject(i).getString("avatarfull");
                        String realname = (String) arraydemiuser.getJSONObject(i).getString("realname");
                        System.out.println(avatarfull);
                        usuario = new PlayerSummaries(steamid,communityvisibilitystate,personaname,avatarfull,realname);
                         //guardamos el objeto como atributo para que puedan interactural todos los metodos de la clase
                        //da null por un motivo que no entiendo si lo saco de aqui pero deberia entenderlo es dura la vida gente
                        Glide.with(MainActivity.this)
                                //esto es una biblioteca donde subir icones svg
                                .load(avatarfull).fitCenter()
                                .error(R.drawable.ic_launcher_foreground)
                                .into(avatar);

                        System.out.println("estoy en el for lol");
                        cargarUser();

                        i++;
                    }
                    //Cuando se obtienen todos los campeones, debemos avisar al adaptador para informar
                    // de que debe actualizarse
                    //adaptador.notifyDataSetChanged();
                    Log.d("DEB", "he ssalido del array lol");
                }
            }
            catch (JSONException e){
                System.out.println(e);
            }
        }



    }
    private class taskGetGames extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
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
            System.out.println(resultado);

            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if (!s.isEmpty()){
                    Log.d("D", "DATOS: "+s);
                    JSONObject jsonObj = new JSONObject(s);
                    JSONObject objeto1 = jsonObj.getJSONObject("response");
                    System.out.println(jsonObj.toString());
                    JSONArray objeto2 = objeto1.getJSONArray("games");
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
                        String steamid = (String) arraydemiuser.getJSONObject(i).getString("steamid");
                        String communityvisibilitystate = (String) arraydemiuser.getJSONObject(i).getString("communityvisibilitystate");
                        String personaname =  (String) arraydemiuser.getJSONObject(i).getString("personaname");
                        String avatarfull = (String) arraydemiuser.getJSONObject(i).getString("avatarfull");
                        String realname = (String) arraydemiuser.getJSONObject(i).getString("realname");
                        System.out.println(avatarfull);
                        usuario = new PlayerSummaries(steamid,communityvisibilitystate,personaname,avatarfull,realname);
                        //guardamos el objeto como atributo para que puedan interactural todos los metodos de la clase
                        //da null por un motivo que no entiendo si lo saco de aqui pero deberia entenderlo es dura la vida gente
                        Glide.with(MainActivity.this)
                                //esto es una biblioteca donde subir icones svg
                                .load(avatarfull).fitCenter()
                                .error(R.drawable.ic_launcher_foreground)
                                .into(avatar);

                        System.out.println("estoy en el for lol");
                        cargarUser();

                        i++;
                    }
                    //Cuando se obtienen todos los campeones, debemos avisar al adaptador para informar
                    // de que debe actualizarse
                    //adaptador.notifyDataSetChanged();
                    Log.d("DEB", "he ssalido del array lol");
                }
            }
            catch (JSONException e){
                System.out.println(e);
            }
        }



    }





}