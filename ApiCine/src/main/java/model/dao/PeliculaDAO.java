package model.dao;

import model.entities.Pelicula;
import model.motorSQL.MotorMysql;
import service.PeliculaAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeliculaDAO {
    private MotorMysql motorSql;

    public PeliculaDAO() {
        motorSql = new MotorMysql();
    }

    public ArrayList<Pelicula> findAll(){
        String sql = "SELECT * FROM PELICULA";
        ArrayList<Pelicula> lstpelis = new ArrayList<>();

        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql);
            ResultSet rs = this.motorSql.executeQuery(pstmt);

            while (rs.next()){
                int idPeli = rs.getInt(1);
                String titulo = rs.getString(2);
                String director = rs.getString(3);
                String estudio = rs.getString(4);
                String descripcion = rs.getString(5);
                String cartelera = rs.getString(6);
                String trailer = rs.getString(7);


                Pelicula pelicula = new Pelicula(idPeli,titulo, director, estudio, cartelera, trailer, descripcion);
                lstpelis.add(pelicula);
            }




        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            this.motorSql.disconnect();
        }

        return lstpelis;
    }


    public Pelicula findByFilter(int idPelicula) {
        String sql = "SELECT * FROM PELICULA WHERE PELICULA.ID_PELICULA = ?;";
        Pelicula pelicula = null;

        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql);
            pstmt.setInt(1, idPelicula);
            ResultSet rs = this.motorSql.executeQuery(pstmt);

            if (rs.next()){
                int idPeli = rs.getInt(1);
                String titulo = rs.getString(2);
                String director = rs.getString(3);
                String estudio = rs.getString(4);
                String descripcion = rs.getString(5);
                String cartelera = rs.getString(6);
                String trailer = rs.getString(7);


                pelicula = new Pelicula(idPeli,titulo, director, estudio, cartelera, trailer, descripcion);

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            this.motorSql.disconnect();
        }
        return pelicula;
    }

    public ArrayList<Pelicula> findCategoria(String nombreCategoria){
        String sql = "SELECT P.* FROM PELICULA P\n" +
                "INNER JOIN TRATAR T ON T.ID_PELICULA = P.ID_PELICULA\n" +
                "INNER JOIN GENERO G ON G.ID_GENERO = T.ID_GENERO\n" +
                "WHERE G.NOMBRE_GENERO = ?;";
        ArrayList<Pelicula> lstpelis = new ArrayList<>();

        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql);
            pstmt.setString(1, nombreCategoria);
            ResultSet rs = this.motorSql.executeQuery(pstmt);

            while (rs.next()){
                int idPeli = rs.getInt(1);
                String titulo = rs.getString(2);
                String director = rs.getString(3);
                String estudio = rs.getString(4);
                String descripcion = rs.getString(5);
                String cartelera = rs.getString(6);
                String trailer = rs.getString(7);


                Pelicula pelicula = new Pelicula(idPeli,titulo, director, estudio, cartelera, trailer, descripcion);
                lstpelis.add(pelicula);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            this.motorSql.disconnect();
        }

        return lstpelis;
    }

}
