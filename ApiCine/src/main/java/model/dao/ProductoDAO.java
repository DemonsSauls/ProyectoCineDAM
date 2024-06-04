package model.dao;

import model.entities.Pelicula;
import model.entities.Producto;
import model.motorSQL.MotorMysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {

    private MotorMysql motorSql;
    public ProductoDAO() {
        motorSql = new MotorMysql();
    }

    public ArrayList<Producto> findAll(){
        String sql = "SELECT * FROM PRODUCTO";
        ArrayList<Producto> lstprod = new ArrayList<>();

        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql);
            ResultSet rs = this.motorSql.executeQuery(pstmt);

            while (rs.next()){
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombreProducto(rs.getString(2));
                producto.setPrecioUnitario(rs.getFloat(3));
                producto.setImagen(rs.getString(4));

                lstprod.add(producto);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            this.motorSql.disconnect();
        }

        return lstprod;
    }
}
