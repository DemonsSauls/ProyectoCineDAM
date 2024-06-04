package model.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String director;
    private String estudio;
    private String cartel;
    private String trailer;
    private String descripcion;

    public Pelicula(int idPelicula, String titulo, String director, String estudio, String cartel, String trailer, String descripcion) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.director = director;
        this.estudio = estudio;
        this.cartel = cartel;
        this.trailer = trailer;
        this.descripcion = descripcion;
    }

    public Pelicula(){}

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getCartel() {
        return cartel;
    }

    public void setCartel(String cartel) {
        this.cartel = cartel;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
