package com.example.gitajob.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.gitajob.steamdata.DatosDeSteam;
import com.squareup.picasso.Picasso;

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
    ArrayList<Game> listaGames = new ArrayList<>();

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
                    Iterator iterador = objeto1.keys();
                    JSONArray arraydemiuser = new JSONArray();

                    while (iterador.hasNext()) {
                        String llave = (String) iterador.next();
                        Log.d(" mi llave ", llave);
                        arraydemiuser = objeto2;
                    }


                    for (int i = 0; i < 1; i++) {
                        String steamid = arraydemiuser.getJSONObject(i).getString("steamid");
                        String communityvisibilitystate = arraydemiuser.getJSONObject(i).getString("communityvisibilitystate");
                        String personaname = arraydemiuser.getJSONObject(i).getString("personaname");
                        String avatarfull = arraydemiuser.getJSONObject(i).getString("avatarfull");
                        String realname = arraydemiuser.getJSONObject(i).getString("realname");


                        if (realname == null) {
                            realname = "Sin Nombre";
                            usuario = new PlayerSummaries(steamid, communityvisibilitystate, personaname, avatarfull, realname);
                            Picasso.get()
                                    //esto es una biblioteca donde subir icones svg
                                    .load(avatarfull)
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(avatar);
                            cargarUser();

                        } else {
                            usuario = new PlayerSummaries(steamid, communityvisibilitystate, personaname, avatarfull, realname);

                            Glide.with(MainActivity.this)
                                    .load(avatarfull)
                                    .error(R.drawable.ic_launcher_foreground)
                                    .into(avatar);
                            cargarUser();

                        }


                    }

                    Log.d("mio", "salgo del on post en Profile");
                }

            } catch (JSONException e) {
                System.out.println("error sis " + e);
            }
        }


    }



    //GetOwnedGames task
    private class taskGetGames extends AsyncTask<String, Void, String> {
        String numero = "";
        private String appid;
        private String name;
        private String playtime_forever;
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
                    Iterator iterador = objeto1.keys();
                    numero = "";


                    System.out.println(ArrayDeJuegos.length());
                    while (iterador.hasNext()) {
                        iterador.next();
                        numero = objeto1.getString("game_count");
                    }

                    for (int i = 0; i < ArrayDeJuegos.length(); i++) {

                        appid = ArrayDeJuegos.getJSONObject(i).get("appid").toString();
                        name = (String) ArrayDeJuegos.getJSONObject(i).get("name");
                        playtime_forever = (String) ArrayDeJuegos.getJSONObject(i).getString("playtime_forever").toString();
                        img_icon_url = (String) ArrayDeJuegos.getJSONObject(i).getString("img_icon_url").toString();
                        img_logo_url = (String) ArrayDeJuegos.getJSONObject(i).getString("img_logo_url").toString();
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

    }

    public void cargarListaJuegos() {
        listaJuegos = (RecyclerView) findViewById(R.id.rListaVideojuegos);
        adaptadorPerfil = new AdaptadorPerfil(gamesOwned, this); //cargo el adaptadorPerfil con el objeto
        listaJuegos.setAdapter(adaptadorPerfil);
        //new ItemTouchHelper(elementotocado).attachToRecyclerView(listaJuegos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        listaJuegos.setLayoutManager(gridLayoutManager);
        adaptadorPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, JuegosInfo.class);
                i.putExtra("id", gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getAppid());
                i.putExtra("nombre", gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getName());
                i.putExtra("icono", gamesOwned.getListaDeVideojuegos().get(listaJuegos.getChildAdapterPosition(view)).getImg_icon_url());
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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.getString("clave de steam","id  de steam");

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivity(i);
        return true;
    }

    /*

    ItemTouchHelper.SimpleCallback elementotocado = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            listaGames.remove(viewHolder.getAdapterPosition());
            adaptadorPerfil.notifyDataSetChanged();

        }
    };

}
*/

}