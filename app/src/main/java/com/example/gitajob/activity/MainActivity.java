package com.example.gitajob.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gitajob.R;
import com.example.gitajob.adaptador.AdaptadorPerfil;
import com.example.gitajob.io.HttpSteam;
import com.example.gitajob.modelos.Game;
import com.example.gitajob.modelos.GamesOwned;
import com.example.gitajob.modelos.PlayerSummaries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    String useractual = "";   //saco y hago accesible la iddesteam para los metodos.
    HttpSteam conexion = new HttpSteam();  //creo mi conexion
    PlayerSummaries usuario;
    ImageView avatar;
    TextView user;
    TextView steamid;
    TextView state;
    TextView realname;
    Menu menu;
    GamesOwned gamesOwned;
    RecyclerView.LayoutManager modelo;
    public RecyclerView listaJuegos;
    public AdaptadorPerfil adaptadorPerfil;
    public Game miJuego;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inicio mis componentes
        initComponents();
        //paso la clave de steam que el usuario introducio en la activity anterior
        useractual = getIntent().getExtras().getString("clave de steam");

        new taskPlayerSummaries().execute("GET", "");
        new taskGetGames().execute("GET", "");
        loadPreferences();

     }
    public boolean onCreateOptionsMenu(Menu menu) {
        //Se usa un inflater para construir la vista y se pasa el menu por defecto para
        // que Android se encargue de colocarlo en la vista
        getMenuInflater().inflate(R.menu.menu_simple,menu);

        return true;
    }


    //paso los datos de mi mainactivtiy a async task
    public String getUseractual() {

        return useractual;
    }

    //AsyncsCorrespondientes


    //GetPlayerSummaries
    private class taskPlayerSummaries extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String resultado = "";
            switch (strings[0]) {
                case "GET":
                    resultado = conexion.cargarUsuarioBase(getUseractual());
                    break;
                case "POST":
                    resultado = Integer.toString(conexion.post(strings[1], strings[2]));

                    break;

            }

            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if (!s.isEmpty()) {
                    Log.d("D", "se cargan los datos de profile:");
                    JSONObject jsonObj = new JSONObject(s);
                    JSONObject objeto1 = jsonObj.getJSONObject("response");
                    JSONArray objeto2 = objeto1.getJSONArray("players");
                    //Pido perdon por el caos pero es que me he estado riendo un buen rato intentanto hacer que esto funcionara lol
                    Iterator iterador = objeto1.keys();
                    JSONArray arraydemiuser = new JSONArray();

                    while (iterador.hasNext()) {
                        String llave = (String) iterador.next();
                        Log.d(" mi llave ", llave);
                        arraydemiuser = objeto2;
                    }

                    //TODO hacer algo con la excepcion
                    //TODO el click
                    //TODO Hacerlo bonito que es mas feo que pegarle a un padre
                        /*vale esto funciona, problema, que depende de user que me pases,
                        //el json que me devuelve la api es directe entonces tengo que arreglar
                        //que sin importar la url mi objeto funcione de forma correcta
                        //por ahora asi a lo groso es que si dectecto que mi string no tiene el atributo que luego
                        //le voy a pedir por aqui que me cambie el tag que busco por uno similar*/

                    for (int i = 0; i < 1; i++) {
                        String steamid = arraydemiuser.getJSONObject(i).getString("steamid");
                        String communityvisibilitystate = arraydemiuser.getJSONObject(i).getString("communityvisibilitystate");
                        String personaname = arraydemiuser.getJSONObject(i).getString("personaname");
                        String avatarfull = arraydemiuser.getJSONObject(i).getString("avatarfull");
                        String realname = arraydemiuser.getJSONObject(i).getString("realname");


                        if (realname == null) {
                            realname = "Sin Nombre";
                            usuario = new PlayerSummaries(steamid, communityvisibilitystate, personaname, avatarfull, realname);
                            Glide.with(MainActivity.this)
                                    //esto es una biblioteca donde subir icones svg
                                    .load(avatarfull).fitCenter()
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(avatar);
                            cargarUser();

                        } else {
                            usuario = new PlayerSummaries(steamid, communityvisibilitystate, personaname, avatarfull, realname);
                            //guardamos el objeto como atributo para que puedan interactural todos los metodos de la clase
                            //da null por un motivo que no entiendo si lo saco de aqui pero deberia entenderlo es dura la vida gente
                            Glide.with(MainActivity.this)
                                    //esto es una biblioteca donde subir icones svg
                                    .load(avatarfull).fitCenter()
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(avatar);
                            cargarUser();

                        }


                    }
                    //Cuando se obtienen todos los campeones, debemos avisar al adaptadorPerfil para informar
                    // de que debe actualizarse
                    //adaptadorPerfil.notifyDataSetChanged();
                    Log.d("mio", "salgo del on post en Profile");
                }

            } catch (JSONException e) {
                System.out.println("error sis " + e);
            }
        }


    }


    //GetOwnedGames
    private class taskGetGames extends AsyncTask<String, Void, String> {
        ArrayList<Game> listaGames = new ArrayList<>();
        String numero = "";
        private String appid;
        private String name;
        private String playtime_forever;
        private String playtime_2weeks;
        private String img_icon_url;
        private String img_logo_url;


        @Override
        protected String doInBackground(String... strings) {
            Log.d("mio", "entro en taskedGames");
            String resultado = "";
            switch (strings[0]) {
                case "GET":
                    resultado = conexion.cargarJuegos(getUseractual());
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
                    Iterator iterador = objeto1.keys();
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
                        playtime_forever = (String) ArrayDeJuegos.getJSONObject(i).getString("playtime_forever").toString();
                        img_icon_url = (String) ArrayDeJuegos.getJSONObject(i).getString("img_icon_url").toString();
                        img_logo_url = (String) ArrayDeJuegos.getJSONObject(i).getString("img_logo_url").toString();
                        //guardamos el objeto como atributo para que puedan interactural todos los metodos de la clase
                        //da null por un motivo que no entiendo si lo saco de aqui pero deberia entenderlo es dura la vida gente
                        miJuego = new Game(appid, name, playtime_forever, img_icon_url, img_logo_url);


                            miJuego = new Game(appid, name, playtime_forever, img_icon_url, img_logo_url);
                            listaGames.add(miJuego);


                    }
                }
                gamesOwned = new GamesOwned(numero, listaGames);
                cargarListaJuegos();



                //Cuando se obtienen todos los campeones, debemos avisar al adaptadorPerfil para informar
                // de que debe actualizarse
                //adaptadorPerfil.notifyDataSetChanged();
                Log.d("mio", "salgo del post de games sin petar :D");

            } catch (JSONException e) {
                Log.d("mio", "error en taskgetGames"+e);
                playtime_2weeks = "";
            }
        }


    }


    //metodos del async
    public void cargarUser() {
        Log.d("mio", "metodo para cargar los usuarios en la vista ");
        user.setText(usuario.getPersonaname());
        state.setText(usuario.getPersonastate());
        realname.setText(usuario.getRealname());
        steamid.setText(usuario.getSteamid());
        System.out.println("me da a mi que esto es un bucle");

    }

    public void cargarListaJuegos() {
        listaJuegos = (RecyclerView) findViewById(R.id.rListaVideojuegos);
        adaptadorPerfil = new AdaptadorPerfil(gamesOwned, this); //cargo el adaptadorPerfil con el objeto
        listaJuegos.setAdapter(adaptadorPerfil);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        listaJuegos.setLayoutManager(gridLayoutManager);
        adaptadorPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, JuegosInfo.class);
                i.putExtra("id", gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getAppid());
                i.putExtra("nombre", gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getName());
                i.putExtra("icono", gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getImg_logo_url());
                i.putExtra("horas", gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getPlaytime_forever());
                i.putExtra("iduser", getUseractual());
                System.out.println("probando a ver si la pasa info o no"+gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getImg_logo_url());
                //;    i.putExtra("lore",gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getPlaytime_forever();
                startActivity(i);
            }
        });



    }

    //metodo del mainactivity para inicializar los componetes
    public void initComponents() {
        gamesOwned = new GamesOwned();
        usuario = new PlayerSummaries();
        avatar = findViewById(R.id.userphoto);
        user = findViewById(R.id.user);
        steamid = findViewById(R.id.steamid);
        realname = findViewById(R.id.realname);
        state = findViewById(R.id.state);


    }
    public void loadPreferences(){

        //Todo 4. Una vez creado todo, solo debemos de preocuparnos de acceder a la información,
        // ya  que Android se encarga del almacenamiento de los datos que introduce el usuario en
        // la ventana de preferencias.

        //Todo 4.1 Utilizamos PreferenceManager para obtener las preferencias compartidas de nuestra
        // aplicación. TENEIS QUE TENER EN CUENTA QUE ESTE ES EL MISMO PARA TODA LA APP (PATRÓN SINGLETON)


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nombreUsuario = sharedPreferences.getString("nombre_usuario","Usuario");
        Toast.makeText(this, "¡Hola "+nombreUsuario+"!", Toast.LENGTH_SHORT).show();
    }


}