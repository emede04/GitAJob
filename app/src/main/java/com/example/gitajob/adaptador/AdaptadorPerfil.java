package com.example.gitajob.adaptador;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gitajob.R.*;
import com.example.gitajob.modelos.Game;
import com.example.gitajob.modelos.GamesOwned;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPerfil extends  RecyclerView.Adapter<AdaptadorPerfil.RecyclerHolder> implements View.OnClickListener{
    private View.OnClickListener sensor;
    ArrayList<Game> listaJuegos;
    private Activity activity;
    public AdaptadorPerfil(GamesOwned g, Activity activity){
        this.listaJuegos = g.getListaDeVideojuegos();
        System.out.println(g.getListaDeVideojuegos().size());
        this.activity = activity;

    }

    @Override
    public void onClick(View view) {
        if(sensor!=null){
            sensor.onClick(view);
        }
    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(layout.activity_adaptador_perfil_juego,parent,false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(vista);
        vista.setOnClickListener(this);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Game juego = listaJuegos.get(position);
        holder.horastotales.setText(juego.getPlaytime_forever());
        holder.nombreJuego.setText(juego.getName());
        holder.idJuego.setText(juego.getAppid());


        String id = juego.getAppid();
        String hash = juego.getImg_logo_url();
        String carga  = "https://media.steampowered.com/steamcommunity/public/images/apps/" + id + "/"+hash;
        Uri myUri = Uri.parse(carga+".jpg");
        Glide
                .with(activity)
                .load(myUri)
                .into(holder.IconoJuego);
        //truquito del adaptador jej
        Log.d("mio","aqui reescribo la informacion de mi objeto con mi url formada dependiendo de el id, y el hash obtenido de la api");
    }





    @Override
    public int getItemCount() {
        return listaJuegos.size();
    }

    public void setOnClickListener(View.OnClickListener sensor) {
        this.sensor = sensor;
    }

    public class RecyclerHolder extends ViewHolder {
        ImageView IconoJuego;
        TextView nombreJuego;
        TextView horastotales;
        TextView idJuego;


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            IconoJuego = itemView.findViewById(id.iconoVideojuego);
            nombreJuego = itemView.findViewById(id.nombre);
            horastotales = itemView.findViewById(id.horastotales);
            idJuego = itemView.findViewById(id.appid);




        }


    }}


