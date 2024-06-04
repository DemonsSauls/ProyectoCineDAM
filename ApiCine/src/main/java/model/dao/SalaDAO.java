package model.dao;

import model.entities.Pelicula;
import model.entities.Proyeccion;
import model.entities.Sala;
import model.entities.Usuario;
import model.motorSQL.MotorMysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SalaDAO {

    private MotorMysql motorSql;

    public SalaDAO() {
        motorSql = new MotorMysql();
    }

    public ArrayList<Sala> findPorPeli(int id){
        String sql = "SELECT S.*, P.* FROM SALA S INNER JOIN PROYECCION P ON P.ID_SALA = S.ID_SALA WHERE P.ID_PELICULA = ?;";
        Sala sala = null;
        Proyeccion proyeccion = null;
        ArrayList<Sala> lstsalas = new ArrayList<>();
        try {
            this.motorSql.connect();

            PreparedStatement pstmt = this.motorSql.prepare(sql);
            pstmt.setInt(1, id);

            ResultSet rs =  this.motorSql.executeQuery(pstmt);

            HashMap<Integer,Sala> salas = new HashMap<>();

            while (rs.next()){
                sala = salas.get(rs.getInt(1));
                if(sala==null) {
                    sala = new Sala();
                    sala.setIdSala(rs.getInt(1));
                    sala.setNumeroSala(rs.getInt(2));
                    sala.setNumeroFilas(rs.getInt(3));
                    sala.setNumeroColumnas(rs.getInt(4));
                    sala.setAforo(rs.getInt(5));
                    salas.put(rs.getInt(1),sala);
                }





                proyeccion = new Proyeccion();
                proyeccion.setIdProyeccion(rs.getInt(6));
                proyeccion.setIdSala(rs.getInt(7));
                proyeccion.setIdPelicula(rs.getInt(8));
                proyeccion.setPrecio(rs.getFloat(9));
                proyeccion.setHorarioInicio(rs.getString(10));
                proyeccion.setHorarioFinal(rs.getString(11));
                proyeccion.setFecha(rs.getString(12));
                proyeccion.setNumeroFilas(rs.getInt((3)));
                proyeccion.setNumeroColumnas(rs.getInt(4));

                sala.addProyeccion(proyeccion);


            }

            rs.close();

            lstsalas.addAll(salas.values());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            this.motorSql.disconnect();
        }

        return lstsalas;
    }
}
