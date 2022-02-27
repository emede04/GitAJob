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

import com.example.gitajob.R;
import com.example.gitajob.R.*;
import com.example.gitajob.modelos.Game;
import com.example.gitajob.modelos.GamesOwned;

import java.util.ArrayList;

public class Adaptador extends  RecyclerView.Adapter<Adaptador.RecyclerHolder> implements View.OnClickListener{
    //llamo a mi objeto GamesOwned
    GamesOwned Juegos;
    private Activity activity;

    public Adaptador(GamesOwned juegos, Activity activity) {
        Juegos = juegos;
        this.activity = activity;
    }


    @Override
    public void onClick(View view) {

    }

    public Adaptador(Activity activity){
        this.activity = activity;

    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(layout.activity_adaptador,parent);
        RecyclerHolder recyclerHolder = new RecyclerHolder(v);
        v.setOnClickListener(this);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Game singular = Juegos.getListaDeVideojuegos().get(position);
        

    }

    @Override
    public int getItemCount() {
        return Juegos.getListaDeVideojuegos().size();
    }

    public class RecyclerHolder extends ViewHolder {
        ImageView IconoJuego;
        TextView nombreJuego;
        TextView horastotales;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            IconoJuego = (ImageView) itemView.findViewById(id.iconoVideojuego);
            nombreJuego = (TextView) itemView.findViewById(id.nombre);
            horastotales = (TextView) itemView.findViewById(id.horastotales);


        }

    }
}
