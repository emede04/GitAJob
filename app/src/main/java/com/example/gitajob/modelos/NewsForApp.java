package com.example.gitajob.modelos;

public class NewsForApp {
    private String titulo;
    private String url;
    private String author;
    private String contenido;
    private String fecha;
    private String feedlable;
    public NewsForApp(String titulo, String url, String author, String contenido, String fecha) {
        this.titulo = titulo;
        this.url = url;
        this.author = author;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public NewsForApp(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
