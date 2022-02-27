package com.example.gitajob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gitajob.R;
import com.example.gitajob.io.HttpSteam;
import com.example.gitajob.modelos.PlayerSummaries;
import com.example.gitajob.steamdata.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String useractual ="";
    ArrayList<PlayerSummaries> listaResumenUsuario;
    HttpSteam conexion = new HttpSteam();
    Constantes c = new Constantes(useractual);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        useractual = getIntent().getExtras().getString("clave de steam");
        System.out.println("esta es la id que pasamos"+useractual);
        c.setIdpasada(useractual);
        System.out.println(useractual);
         listaResumenUsuario = new ArrayList<PlayerSummaries>();
        new taskConexiones().execute("GET", "");

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


    //clase para cargar los datos a un json


    private class taskConexiones extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String resultado = "";
            switch (strings[0]) {
                case "GET":
                    resultado = conexion.cargarUsuarioBase(getUseractual());
                    break;

            }
            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if (s != null) {
                    Log.d("D", "por si no se cargan los datos lol: " + s);
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject objeto2 = jsonObject.getJSONObject("data");

                    Iterator iterador = objeto2.keys();
                    JSONArray arrayDatos = new JSONArray();

                    while (iterador.hasNext()) {
                        String llave = (String) iterador.next();
                        arrayDatos.put(objeto2.get(llave));
                    }

                    for (int i = 0; i < arrayDatos.length(); i++) {
                        String steamid = arrayDatos.getJSONObject(i).getString("steamid");
                        String communityvisibilitystate = arrayDatos.getJSONObject(i).getString("communityvisibilitystate");
                        String profilestate = arrayDatos.getJSONObject(i).getString("profilestate");
                        String personaname = arrayDatos.getJSONObject(i).getString("personaname");
                        String profileurl = arrayDatos.getJSONObject(i).getString("profileurl");
                        String avatar = arrayDatos.getJSONObject(i).getString("avatar");
                        String avatarmedium = arrayDatos.getJSONObject(i).getString("avatarmedium");
                        String avatarfull = arrayDatos.getJSONObject(i).getString("avatarfull");
                        String avatarhash = arrayDatos.getJSONObject(i).getString("avatarhash");
                        String personastate = arrayDatos.getJSONObject(i).getString("personastate");
                        String realname = arrayDatos.getJSONObject(i).getString("realname");
                        String primaryclanid = arrayDatos.getJSONObject(i).getString("primaryclanid");
                        String timecreated = arrayDatos.getJSONObject(i).getString("timecreated");
                        String personastateflags = arrayDatos.getJSONObject(i).getString("personastateflags");
                        String loccountrycode = arrayDatos.getJSONObject(i).getString("loccountrycode");
                        String locstatecode = arrayDatos.getJSONObject(i).getString("locstatecode");
                        String loccityid = arrayDatos.getJSONObject(i).getString("loccityid");

                        PlayerSummaries usuario = new PlayerSummaries(steamid, communityvisibilitystate, profilestate, personaname, profileurl, avatar, avatarmedium, avatarfull, avatarhash, personastate, realname, primaryclanid, timecreated, personastateflags, loccountrycode, locstatecode, loccityid);

                        System.out.println("salimo   " + usuario.getPersonaname());
                    }

                    // adaptador.notifyDataSetChanged();
                    // Log.d("DEB", String.valueOf(arrayDatos));


                }
            } catch (JSONException e) {

            }
        }


    }
}