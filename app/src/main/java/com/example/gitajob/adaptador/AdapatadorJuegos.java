package com.example.gitajob.adaptador;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitajob.R;
import com.example.gitajob.modelos.NewsForApp;

import java.util.ArrayList;

public class AdapatadorJuegos extends  RecyclerView.Adapter<AdapatadorJuegos.RecyclerHolder> implements View.OnClickListener {
    private View.OnClickListener sensor;
    ArrayList<NewsForApp> listanotis;
    private Activity activity;


    public AdapatadorJuegos(ArrayList<NewsForApp> l, Activity activity) {
        this.listanotis = l;
        this.activity = activity;

    }

    @Override
    public void onClick(View view) {
        if (sensor != null) {
            sensor.onClick(view);
        }
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adaptador, parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(vista);
        vista.setOnClickListener(this);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapatadorJuegos.RecyclerHolder recyclerHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //TODO -FALTA GENERAR ACOMODAR EL RECYCLER
    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView IconoJuego;
        TextView nombreJuego;
        TextView horastotales;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            IconoJuego = itemView.findViewById(R.id.iconoVideojuego);
            nombreJuego = itemView.findViewById(R.id.nombre);
            horastotales = itemView.findViewById(R.id.horastotales);


        }


    }
}
