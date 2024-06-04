package model.dao;

import model.entities.PedirPedido;
import model.entities.RealizarPedido;
import model.motorSQL.MotorMysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class PedidoDAO {

    private MotorMysql motorSql;

    public PedidoDAO() {
        motorSql = new MotorMysql();
    }

    public boolean pagar(RealizarPedido pedido) {
        String sql1 = "INSERT INTO PEDIDO (SUBTOTAL, TOTAL, IVA, FECHA, ID_USUARIO) VALUES (?,?,?,?,?);";
        String sql2 = "SELECT LAST_INSERT_ID() AS ID_PEDIDO;";
        String sql3 = "INSERT INTO PEDIR (ID_PEDIDO, ID_PRODUCTO, CANTIDAD, PRECIO) VALUES (?,?,?,?);";
        String sql4 = "INSERT INTO ENTRADA (ID_PROYECCION, ID_PEDIDO, ID_BUTACA) VALUES (?,?,?);";
        String sql5 = "UPDATE BUTACA SET ESTADO = 'ocupado' WHERE ID_BUTACA = ?;";

        boolean bien = false;
        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql1);

            pstmt.setFloat(1, pedido.getSubtotal());
            pstmt.setFloat(2, pedido.getTotal());
            pstmt.setInt(3, pedido.getIva());

            Calendar cal = new GregorianCalendar();
            java.util.Date fechaActual = cal.getTime();
            java.sql.Timestamp fechaTimestamp = new java.sql.Timestamp(fechaActual.getTime());

            pstmt.setTimestamp(4, fechaTimestamp);

            pstmt.setInt(5, pedido.getIdUsuario());

            int filas = this.motorSql.execute(pstmt);


            if(filas > 0){
                int idPedido=0;


                //Gestion productos repetidos
                List<PedirPedido> copia = new ArrayList<>(pedido.pedires);

                for (PedirPedido pedido1:copia){
                    int firstIndex = -1;
                    int lastIndex = -1;

                    for (int i = 0; i < pedido.pedires.size(); i++) {
                        PedirPedido current = pedido.pedires.get(i);
                        if(current.getIdProducto() == pedido1.getIdProducto()){
                            if(firstIndex == -1){
                                firstIndex = i;
                            }
                            lastIndex = i;
                        }
                        
                    }

                    if(firstIndex != -1 && firstIndex != lastIndex){
                        pedido1.setCantidad(pedido1.getCantidad()+1);

                        float precio = pedido1.getPrecio();
                        int cantidad = pedido1.getCantidad()-1;
                        int cantidadreal = pedido1.getCantidad();
                        float div = precio/cantidad;
                        float total = div * cantidadreal;


                        pedido1.setPrecio(total);
                        for (int i = lastIndex; i > firstIndex ; i--) {
                            pedido.pedires.remove(i);
                        }

                    }
                }



                pstmt = this.motorSql.prepare(sql2);
                ResultSet rs = this.motorSql.executeQuery(pstmt);
                if(rs.next()){
                    idPedido=rs.getInt(1);
                    for(int i=0; i<pedido.pedires.size(); i++) {
                        pstmt = this.motorSql.prepare(sql3);
                        pstmt.setInt(1, idPedido);
                        pstmt.setInt(2, pedido.pedires.get(i).getIdProducto());
                        pstmt.setInt(3, pedido.pedires.get(i).getCantidad());
                        pstmt.setFloat(4, pedido.pedires.get(i).getPrecio());

                        filas = this.motorSql.execute(pstmt);
                        if (filas <= 0) {
                            System.out.println("Error al insertar lineas de compra");
                            return false;
                        }
                    }
                    for(int i=0; i<pedido.entradas.size(); i++) {
                        pstmt = this.motorSql.prepare(sql4);
                        pstmt.setInt(1, pedido.entradas.get(i).getIdProyeccion());
                        pstmt.setInt(2, idPedido);
                        pstmt.setInt(3, pedido.entradas.get(i).getIdButaca());

                        filas = this.motorSql.execute(pstmt);
                        if (filas <= 0) {
                            System.out.println("Error al insertar lineas de compra");
                            return false;
                        }

                        // Actualizar estado de butaca
                        pstmt = this.motorSql.prepare(sql5);
                        pstmt.setInt(1, pedido.entradas.get(i).getIdButaca());

                        filas = this.motorSql.execute(pstmt);
                        if (filas <= 0) {
                            System.out.println("Error al actualizar estado de butaca");
                            return false;
                        }


                    }
                    return true;
                }

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            this.motorSql.disconnect();
        }
        return false;
    }
}
