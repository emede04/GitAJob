package com.example.gitajob.adaptador;

import android.app.Activity;
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
import com.example.gitajob.R;
import com.example.gitajob.R.*;
import com.example.gitajob.modelos.Game;
import com.example.gitajob.modelos.GamesOwned;

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
        Glide.with(activity)
                .load(juego.getImg_icon_url())
                .into(holder.IconoJuego);
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

            IconoJuego =  itemView.findViewById(id.iconoVideojuego);
            nombreJuego =  itemView.findViewById(id.nombre);
            horastotales =  itemView.findViewById(id.horastotales);


        }

    }
}
