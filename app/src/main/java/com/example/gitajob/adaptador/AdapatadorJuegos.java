package com.example.gitajob.adaptador;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapatadorJuegos extends  RecyclerView.Adapter<AdaptadorPerfil.RecyclerHolder> implements View.OnClickListener{


    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public AdaptadorPerfil.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPerfil.RecyclerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

