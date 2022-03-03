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
import java.util.concurrent.TimeoutException;

public class AdapatadorJuegos extends  RecyclerView.Adapter<AdapatadorJuegos.RecyclerHolder> implements View.OnClickListener {
    private View.OnClickListener sensor;
    ArrayList<NewsForApp> listanotis;
    private Activity activity;


    public AdapatadorJuegos(ArrayList<NewsForApp> l, Activity activity) {
        this.listanotis = l;
        System.out.println("tamaño"+ l.size());

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
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adaptador_juego_noticia, parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(vista);
        vista.setOnClickListener(this);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int i) {
        NewsForApp n = listanotis.get(i);
        holder.autor.setText(n.getAuthor());
        holder.contenido.setText(n.getContenido());
        holder.titulo.setText(n.getTitulo());;
    }

    @Override
    public int getItemCount() {
        return listanotis.size();
    }

    //TODO -FALTA GENERAR ACOMODAR EL RECYCLER
    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView autor;
        TextView titulo;
        TextView contenido;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            autor = itemView.findViewById(R.id.autor);
            titulo = itemView.findViewById(R.id.titulo);
            contenido = itemView.findViewById(R.id.contenido2);


        }


    }
}
