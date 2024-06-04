package model.entities;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int idPedido;//Id del pedido me lo pasarán con el set porque hay casos que como no ha pasado por bd no tenga id todavía
    private int idUsuario;
    private float total;
    private float subtotal;
    private int iva;
    private Date fecha;
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Entrada> entradas = new ArrayList<>();

    public Pedido(int idUsuario, float total, float subtotal, int iva, Date fecha) {
        this.idUsuario = idUsuario;
        this.total = total;
        this.subtotal = subtotal;
        this.iva = iva;
        this.fecha = fecha;
    }

    public Pedido(){}

    public void addProdutcto(Producto producto){
        productos.add(producto);
    }

    public void addEntada(Entrada entrada){
        entradas.add(entrada);
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(ArrayList<Entrada> entradas) {
        this.entradas = entradas;
    }
}
