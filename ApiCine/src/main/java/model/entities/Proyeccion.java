package model.entities;

public class Proyeccion {
    private int idProyeccion;
    private int idSala;
    private int idPelicula;
    private float precio;
    private String horarioInicio;
    private String horarioFinal;
    private String fecha;
    //private Butaca[][] butacas;
    private int numeroFilas;
    private int numeroColumnas;

    public Proyeccion(int idPelicula, int idSala, String fecha, String horarioInicio, String horarioFinal, float precio) {
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.fecha = fecha;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.precio = precio;
        //this.butacas = new Butaca[this.numeroFilas][this.numeroColumnas];
        // Inicializar la matriz de butacas

    }
    public Proyeccion(){}
/*
    public void inicializarMatriz(){
        butacas = new Butaca[numeroFilas][numeroColumnas];
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                butacas[i][j] = null;
            }
        }
    }

    public void insertarButaca(Butaca butaca){
        butacas[butaca.getFila()-1][butaca.getColumna()-1] = butaca;
    }





    public void marcarButacaOcupada(int butaca) {
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (butacas[i][j].getIdButaca() == butaca) {
                    butacas[i][j].setOcupado(true);
                }
            }
        }
    }

    public boolean butacaEstaOcupada(int butaca) {
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (butacas[i][j].getIdButaca() == butaca) {
                    return butacas[numeroFilas][numeroColumnas].isOcupado();
                }
            }
        }
        return false;
    }

    public void marcarButacaReservada(int butaca) {
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (butacas[i][j].getIdButaca() == butaca) {
                    butacas[i][j].setReservado(true);
                }
            }
        }
    }

    public boolean butacaEstaReservada(int butaca) {
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (butacas[i][j].getIdButaca() == butaca) {
                    return butacas[numeroFilas][numeroColumnas].isReservado();
                }
            }
        }
        return false;
    }
*/



    public int getIdProyeccion() {
        return idProyeccion;
    }

    public void setIdProyeccion(int idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
/*
    public Butaca[][] getButacas() {
        return butacas;
    }*/
}
