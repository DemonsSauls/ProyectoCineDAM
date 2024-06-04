package model.entities;

public class Pedir {
    private int idProducto;
    private int idPedido;
    private int cantidad;
    private float precio;

    public Pedir(int idProducto, int cantidad, float precio) {
        this.idProducto = idProducto;
        //this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Pedir(){}

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }



    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int anInt) {
    }
}
