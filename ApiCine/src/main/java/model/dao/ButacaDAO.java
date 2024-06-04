package model.dao;

import model.entities.Butaca;
import model.entities.Entrada;
import model.entities.Pelicula;
import model.entities.Proyeccion;
import model.motorSQL.MotorMysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ButacaDAO {
    private MotorMysql motorSql;

    public ButacaDAO() {
        motorSql = new MotorMysql();
    }



    public ArrayList<Butaca> findPorSala(int id_proyeccion){
        String sql = "SELECT B.*, P.*, S.NUM_FILAS, S.NUM_COLUMNAS FROM BUTACA B\n" +
                "INNER JOIN PROYECCION P ON P.ID_PROYECCION = B.ID_PROYECCION\n" +
                "INNER JOIN SALA S ON S.ID_SALA = P.ID_SALA\n" +
                "WHERE B.ID_PROYECCION = ?;";

        ArrayList<Butaca> lstbutacas = new ArrayList<>();
        //Proyeccion proyeccion = new Proyeccion();
        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql);
            pstmt.setInt(1, id_proyeccion);
            ResultSet rs = this.motorSql.executeQuery(pstmt);

            int vuelta = 0;

            while (rs.next()){
                Butaca butaca = new Butaca();

                butaca.setIdButaca(rs.getInt(1));
                butaca.setIdProyeccion(rs.getInt(2));
                butaca.setNumButaca(rs.getInt(3));
                butaca.setFila(rs.getInt(4));
                butaca.setColumna(rs.getInt(5));
                String estado = rs.getString(6);
                if(estado.equals("ocupado")){
                    butaca.setOcupado(true);
                }else if(estado.equals("reservado")){
                    butaca.setReservado(true);
                }
/*
                proyeccion.setIdProyeccion(rs.getInt(7));
                proyeccion.setIdSala(rs.getInt(8));
                proyeccion.setIdPelicula(rs.getInt(9));
                proyeccion.setPrecio(rs.getFloat(10));
                proyeccion.setHorarioInicio(rs.getString(11));
                proyeccion.setHorarioFinal(rs.getString(12));
                proyeccion.setFecha(rs.getString(13));
                proyeccion.setNumeroFilas(rs.getInt(14));
                proyeccion.setNumeroColumnas(rs.getInt(15));
                if(vuelta==0) {
                    proyeccion.inicializarMatriz();
                }
                vuelta++;
                proyeccion.insertarButaca(butaca);
*/

                lstbutacas.add(butaca);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            this.motorSql.disconnect();
        }

        return lstbutacas;
    }





    public String reservar(int id_butaca){
        String sql1 = "SELECT B.ESTADO FROM BUTACA B WHERE B.ID_BUTACA = ?; ";
        String sql2 = "UPDATE BUTACA SET ESTADO = 'reservado' WHERE ID_BUTACA = ?; ";
        String mensaje = "";

        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql1);
            pstmt.setInt(1, id_butaca);

            ResultSet rs = this.motorSql.executeQuery(pstmt);

            // Mover el cursor al primer resultado
            if(rs.next()) {
                String estado = rs.getString(1);

                if(estado.equals("libre")){
                    pstmt = this.motorSql.prepare(sql2);
                    pstmt.setInt(1,id_butaca);
                    int filas = this.motorSql.execute(pstmt);
                    if(filas<=0) {
                        mensaje = "Error interno";
                    } else {
                        mensaje = "Butaca reservada correctamente";
                    }
                } else {
                    mensaje = "La butaca está reservada/ocupada";
                }
            } else {
                mensaje = "Butaca no encontrada";
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            mensaje = "Error interno";
        } finally {
            this.motorSql.disconnect();
        }
        return mensaje;
    }

    public String desreservar(int idButaca) {
        String sql1 = "SELECT B.ESTADO FROM BUTACA B WHERE B.ID_BUTACA = ?; ";
        String sql2 = "UPDATE BUTACA SET ESTADO = 'libre' WHERE ID_BUTACA = ?; ";
        String mensaje = "";

        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql1);
            pstmt.setInt(1, idButaca);

            ResultSet rs = this.motorSql.executeQuery(pstmt);

            // Mover el cursor al primer resultado
            if(rs.next()) {
                String estado = rs.getString(1);

                if(estado.equals("reservado")){
                    pstmt = this.motorSql.prepare(sql2);
                    pstmt.setInt(1,idButaca);
                    int filas = this.motorSql.execute(pstmt);
                    if(filas<=0) {
                        mensaje = "Error interno";
                    } else {
                        mensaje = "Butaca desreservada correctamente";
                    }
                } else {
                    mensaje = "La butaca ya está libre/ocupada";
                }
            } else {
                mensaje = "Butaca no encontrada";
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            mensaje = "Error interno";
        } finally {
            this.motorSql.disconnect();
        }
        return mensaje;
    }
}
