package model.entities;



import java.util.Date;

public class Entrada {
    private int idEntrada;
    private int idProyeccion;
    private int idButaca;
    private int idSala;

    private String pelicula;
    private int idPedido;
    /*private int numButaca;
    private float precio;
    private Date fecha;
    private String horario;
    private int numSala;
    private String titulo;
*/

    public Entrada(int idProyeccion, int idButaca, int idSala, int idPedido) {
        this.idProyeccion = idProyeccion;
        this.idButaca = idButaca;
        this.idSala = idSala;
        this.idPedido = idPedido;
    }

    public Entrada(){}

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public int getIdVisualizar() {
        return idProyeccion;
    }

    public void setIdVisualizar(int idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public int getIdButaca() {
        return idButaca;
    }

    public void setIdButaca(int idButaca) {
        this.idButaca = idButaca;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }



    public void setIdProyeccion(int idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
}
