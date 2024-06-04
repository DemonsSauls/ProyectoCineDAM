package model.entities;

public class Tratar {
    private int idPelicula;
    private int idGenero;

    public Tratar(int idPelicula, int idGenero) {
        this.idPelicula = idPelicula;
        this.idGenero = idGenero;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
}
