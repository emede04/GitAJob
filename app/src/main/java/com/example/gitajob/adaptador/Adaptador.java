package com.example.gitajob.adaptador;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.Excludes;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.example.gitajob.R;
import com.example.gitajob.R.*;
import com.example.gitajob.activity.MainActivity;
import com.example.gitajob.modelos.Game;
import com.example.gitajob.modelos.GamesOwned;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Adaptador extends  RecyclerView.Adapter<Adaptador.RecyclerHolder> implements View.OnClickListener{
    ArrayList<Game> listaJuegos;
    private Activity activity;
    public Adaptador(GamesOwned g, Activity activity){
        this.listaJuegos = g.getListaDeVideojuegos();
        System.out.println(g.getListaDeVideojuegos().size());
        this.activity = activity;

    }

    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(layout.activity_adaptador,parent,false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(vista);

        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Game juego = listaJuegos.get(position);
        holder.horastotales.setText(juego.getPlaytime_forever());
        holder.nombreJuego.setText(juego.getName());

            String id = juego.getAppid();
            String hash = juego.getImg_logo_url();
            String carga  = "https://media.steampowered.com/steamcommunity/public/images/apps/" + id + "/"+hash+".jpg";
            Uri myUri = Uri.parse(carga);
            Glide
                    .with(activity)
                    .load(myUri)
                    .into(holder.IconoJuego);

            System.out.println(myUri.toString());
        }






    @Override
    public int getItemCount() {
        return listaJuegos.size();
    }

    public class RecyclerHolder extends ViewHolder {
        ImageView IconoJuego;
        TextView nombreJuego;
        TextView horastotales;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            IconoJuego = itemView.findViewById(id.iconoVideojuego);
            nombreJuego = itemView.findViewById(id.nombre);
            horastotales = itemView.findViewById(id.horastotales);




        }


    }}

