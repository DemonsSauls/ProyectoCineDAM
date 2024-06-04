package model.entities;

import java.util.ArrayList;

public class Sala {
    private int idSala;
    private int numeroSala;
    private int aforo;

    private int numeroFilas;
    private int numeroColumnas;
    private ArrayList<Proyeccion> proyecciones = new ArrayList<>();

    public Sala(int idSala, int numeroSala, int aforo, int numeroFilas, int numeroColumnas) {
        this.idSala = idSala;
        this.numeroSala = numeroSala;
        this.aforo = aforo;
        this.numeroFilas = numeroFilas;
        this.numeroColumnas = numeroColumnas;

    }

    public Sala(){}


    public void addProyeccion(Proyeccion proyeccion){
        proyecciones.add(proyeccion);
    }

    public ArrayList<Proyeccion> getProyecciones() {
        return proyecciones;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public int getNumeroFilas() {
        return numeroFilas;
    }

    public void setNumeroFilas(int numeroFilas) {
        this.numeroFilas = numeroFilas;
    }

    public int getNumeroColumnas() {
        return numeroColumnas;
    }

    public void setNumeroColumnas(int numeroColumnas) {
        this.numeroColumnas = numeroColumnas;
    }






}
