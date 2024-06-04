package model.entities;

public class Butaca {
    private int idButaca;
    private int idProyeccion;
    private int numButaca;
    private int fila;
    private int columna;
    private boolean ocupado;
    private boolean reservado;

    public Butaca(int fila, int columna, int idProyeccion) {
        this.fila = fila;
        this.columna = columna;
        this.ocupado = false;
        this.reservado = false;
        this.idProyeccion = idProyeccion;
    }

    public Butaca() {
    }

    public int getIdButaca() {
        return idButaca;
    }

    public void setIdButaca(int idButaca) {
        this.idButaca = idButaca;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getIdProyeccion() {
        return idProyeccion;
    }

    public void setIdProyeccion(int idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public int getNumButaca() {
        return numButaca;
    }

    public void setNumButaca(int numButaca) {
        this.numButaca = numButaca;
    }
}
