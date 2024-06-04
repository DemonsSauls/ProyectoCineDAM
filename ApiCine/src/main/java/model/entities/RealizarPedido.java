package model.entities;

import java.util.Date;
import java.util.List;

public class RealizarPedido {
    private int idUsuario;
    private float total;
    private float subtotal;
    private int iva;
    private Date fecha;

    public List<PedidoEntrada> entradas;//El json debe tener este campo para serializarlo correctamente
    public List<PedirPedido> pedires;//El json debe tener este campo para serializarlo correctamente


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

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

}
