package model.entities;

import java.util.ArrayList;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private float precioUnitario;

    private String imagen;

    private Pedir pedir;

    public Producto(int idProducto, String nombreProducto, float precioUnitario, String imagen) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.imagen = imagen;
    }

    public Producto(){}

    public Pedir getPedir() {
        return pedir;
    }

    public void setPedir(Pedir pedir) {
        this.pedir = pedir;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
