package model.motorSQL;

import java.sql.*;
import java.util.Properties;

public class MotorMysql {
    private Properties properties;
    private Connection conn;
    //private Statement st;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private static final String URL = "jdbc:mysql://cine.cmbymi9szxfy.us-east-1.rds.amazonaws.com/Cine";
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    //jdbc:derby://localhost:1527/netflix
    //private static final String URL = "jdbc:derby://localhost:1527/netflix";
    private static final String USER = "admin";
    private static final String PASS = "admin1234";

    public MotorMysql(){
        properties = new Properties();
        properties.setProperty("user", MotorMysql.USER);
        properties.setProperty("password", MotorMysql.PASS);
        properties.setProperty("ssl", "false");
    }
    public void connect() {
        try {
            Class.forName(CONTROLADOR);
            conn = DriverManager.getConnection(URL, properties);
            //st = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public PreparedStatement prepare(String sql) throws SQLException {
        pstmt = conn.prepareStatement(sql);
        return pstmt;
    }


    public int execute(PreparedStatement pstmt) {
        int resp = 0;
        try {
            resp = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resp;
    }
    public ResultSet executeQuery(PreparedStatement pstmt) {
        try {
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
        }
    }
}
