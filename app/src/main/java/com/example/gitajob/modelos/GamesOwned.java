package com.example.gitajob.modelos;

import java.util.ArrayList;

public class GamesOwned {
    String numerosdejuegos;
  ArrayList<Game> ListaDeVideojuegos;

    //constructor GETOWNEDGAMES //
    public GamesOwned(String numerosdejuegos, ArrayList<Game> listaDeVideojuegos) {
        this.numerosdejuegos = numerosdejuegos;
        ListaDeVideojuegos = listaDeVideojuegos;
    }

     public GamesOwned(){

    }

    public String getNumerosdejuegos() {
        return numerosdejuegos;
    }

    public void setNumerosdejuegos(String numerosdejuegos) {
        this.numerosdejuegos = numerosdejuegos;
    }

    public ArrayList<Game> getListaDeVideojuegos() {
        return ListaDeVideojuegos;
    }

    public void setListaDeVideojuegos(ArrayList<Game> listaDeVideojuegos) {
        ListaDeVideojuegos = listaDeVideojuegos;
    }








    }
