package model.dao;

import model.entities.*;
import model.motorSQL.MotorMysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioDAO {

    private final MotorMysql motorSql;

    public UsuarioDAO() {
        motorSql = new MotorMysql();
    }

    public Usuario login(String nombre, String password){
        String sql = "SELECT ID_USUARIO, NOMBRE_USUARIO, MATRICULA, CORREO FROM USUARIO WHERE NOMBRE_USUARIO = ? AND CONTRASENA=?";
        Usuario usuario = null;
        try {
            this.motorSql.connect();

            PreparedStatement pstmt = this.motorSql.prepare(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2,password);

            ResultSet rs =  this.motorSql.executeQuery(pstmt);



        if(rs.next()){
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt(1));
            usuario.setNombreUsuario(rs.getString(2));
            usuario.setMatricula(rs.getString(3));
            usuario.setCorreoElectronico(rs.getString(4));
        }

        rs.close();

    }catch (SQLException e){
        System.out.println(e.getMessage());
    }finally {
        this.motorSql.disconnect();
    }

        return usuario;
    }

    public boolean register(String nombre, String email, String contrasena, String matricula){
        String sql = "INSERT INTO USUARIO (NOMBRE_USUARIO, CONTRASENA, CORREO, MATRICULA) VALUES (?,?,?,?);";
        boolean bien = false;
        try {
            this.motorSql.connect();
            PreparedStatement pstmt = this.motorSql.prepare(sql);

            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena);
            pstmt.setString(3, email);
            pstmt.setString(4, matricula);

            int filas = this.motorSql.execute(pstmt);
            this.motorSql.disconnect();

            if(filas > 0){
                bien = true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bien;
    }

    public ArrayList<Pedido> pedidos(String idUsuario) {
        // Consulta para obtener la información del pedido
        String sqlPedido = "SELECT ID_PEDIDO, SUBTOTAL, TOTAL, IVA, FECHA, ID_USUARIO FROM PEDIDO WHERE ID_USUARIO = ?";

        // Consulta para obtener la información de las entradas asociadas al pedido
        String sqlEntradas = "SELECT ID_ENTRADA, ID_PROYECCION, ID_BUTACA, ID_PEDIDO FROM ENTRADA WHERE ID_PEDIDO = ?";

        // Consulta para obtener la información de los productos comprados en el pedido
        String sqlProductos = "SELECT PEDIR.ID_PRODUCTO, PEDIR.CANTIDAD, PEDIR.PRECIO, " +
                "PRODUCTO.NOMBRE_PRODUCTO, PRODUCTO.PRECIO_UNITARIO " +
                "FROM PEDIR INNER JOIN PRODUCTO ON PEDIR.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO " +
                "WHERE PEDIR.ID_PEDIDO = ?";

        ArrayList<Pedido> lstpedido = new ArrayList<>();

        try {
            this.motorSql.connect();

            // Consulta para obtener la información del pedido
            PreparedStatement pstmtPedido = this.motorSql.prepare(sqlPedido);
            pstmtPedido.setString(1, idUsuario);
            ResultSet rsPedido = this.motorSql.executeQuery(pstmtPedido);

            while (rsPedido.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rsPedido.getInt("ID_PEDIDO"));
                pedido.setSubtotal(rsPedido.getFloat("SUBTOTAL"));
                pedido.setTotal(rsPedido.getFloat("TOTAL"));
                pedido.setIva(rsPedido.getInt("IVA"));
                pedido.setFecha(rsPedido.getDate("FECHA"));
                pedido.setIdUsuario(rsPedido.getInt("ID_USUARIO"));

                // Consulta para obtener la información de las entradas asociadas al pedido
                PreparedStatement pstmtEntradas = this.motorSql.prepare(sqlEntradas);
                pstmtEntradas.setInt(1, pedido.getIdPedido());
                ResultSet rsEntradas = this.motorSql.executeQuery(pstmtEntradas);

                while (rsEntradas.next()) {
                    Entrada entrada = new Entrada();
                    entrada.setIdEntrada(rsEntradas.getInt("ID_ENTRADA"));
                    entrada.setIdProyeccion(rsEntradas.getInt("ID_PROYECCION"));
                    entrada.setIdButaca(rsEntradas.getInt("ID_BUTACA"));
                    pedido.addEntada(entrada);
                }

                // Consulta para obtener la información de los productos comprados en el pedido
                PreparedStatement pstmtProductos = this.motorSql.prepare(sqlProductos);
                pstmtProductos.setInt(1, pedido.getIdPedido());
                ResultSet rsProductos = this.motorSql.executeQuery(pstmtProductos);

                while (rsProductos.next()) {
                    Pedir pedir = new Pedir();
                    pedir.setIdProducto(rsProductos.getInt("ID_PRODUCTO"));
                    pedir.setCantidad(rsProductos.getInt("CANTIDAD"));
                    pedir.setPrecio(rsProductos.getFloat("PRECIO"));

                    Producto producto = new Producto();
                    producto.setNombreProducto(rsProductos.getString("NOMBRE_PRODUCTO"));
                    producto.setPrecioUnitario(rsProductos.getFloat("PRECIO_UNITARIO"));
                    producto.setPedir(pedir);

                    pedido.addProdutcto(producto);
                }

                lstpedido.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.motorSql.disconnect();
        }

        return lstpedido;
    }


    public Pedido findPedido(String idUsuario) {
        // Consulta para obtener la información del pedido
        String sqlPedido = "SELECT ID_PEDIDO, SUBTOTAL, TOTAL, IVA, FECHA, ID_USUARIO FROM PEDIDO WHERE ID_USUARIO = ? ORDER BY FECHA DESC LIMIT 1";

        // Consulta para obtener la información de las entradas asociadas al pedido
        String sqlEntradas = "SELECT E.ID_ENTRADA, E.ID_PROYECCION, E.ID_BUTACA, E.ID_PEDIDO, PE.TITULO FROM ENTRADA E\n" +
                "INNER JOIN PROYECCION P ON P.ID_PROYECCION = E.ID_PROYECCION\n" +
                "INNER JOIN PELICULA PE ON PE.ID_PELICULA = P.ID_PELICULA\n" +
                " WHERE ID_PEDIDO = ?";

        // Consulta para obtener la información de los productos comprados en el pedido
        String sqlProductos = "SELECT PEDIR.ID_PRODUCTO, PEDIR.CANTIDAD, PEDIR.PRECIO, " +
                "PRODUCTO.NOMBRE_PRODUCTO, PRODUCTO.PRECIO_UNITARIO " +
                "FROM PEDIR INNER JOIN PRODUCTO ON PEDIR.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO " +
                "WHERE PEDIR.ID_PEDIDO = ?";

        Pedido ultimoPedido = null;

        try {
            this.motorSql.connect();

            // Consulta para obtener la información del último pedido del usuario
            PreparedStatement pstmtPedido = this.motorSql.prepare(sqlPedido);
            pstmtPedido.setString(1, idUsuario);
            ResultSet rsPedido = this.motorSql.executeQuery(pstmtPedido);

            if (rsPedido.next()) {
                // Crear el objeto Pedido con la información del último pedido
                ultimoPedido = new Pedido();
                ultimoPedido.setIdPedido(rsPedido.getInt("ID_PEDIDO"));
                ultimoPedido.setSubtotal(rsPedido.getFloat("SUBTOTAL"));
                ultimoPedido.setTotal(rsPedido.getFloat("TOTAL"));
                ultimoPedido.setIva(rsPedido.getInt("IVA"));
                ultimoPedido.setFecha(rsPedido.getDate("FECHA"));
                ultimoPedido.setIdUsuario(rsPedido.getInt("ID_USUARIO"));

                int idPedidoActual = ultimoPedido.getIdPedido();

                // Consulta para obtener la información de las entradas asociadas al pedido
                PreparedStatement pstmtEntradas = this.motorSql.prepare(sqlEntradas);
                pstmtEntradas.setInt(1, idPedidoActual);
                ResultSet rsEntradas = this.motorSql.executeQuery(pstmtEntradas);

                while (rsEntradas.next()) {
                    Entrada entrada = new Entrada();
                    entrada.setIdEntrada(rsEntradas.getInt("ID_ENTRADA"));
                    entrada.setIdProyeccion(rsEntradas.getInt("ID_PROYECCION"));
                    entrada.setIdButaca(rsEntradas.getInt("ID_BUTACA"));
                    entrada.setPelicula(rsEntradas.getString("TITULO"));
                    ultimoPedido.addEntada(entrada);
                }

                // Consulta para obtener la información de los productos comprados en el pedido
                PreparedStatement pstmtProductos = this.motorSql.prepare(sqlProductos);
                pstmtProductos.setInt(1, idPedidoActual);
                ResultSet rsProductos = this.motorSql.executeQuery(pstmtProductos);

                while (rsProductos.next()) {
                    Pedir pedir = new Pedir();
                    pedir.setIdProducto(rsProductos.getInt("ID_PRODUCTO"));
                    pedir.setCantidad(rsProductos.getInt("CANTIDAD"));
                    pedir.setPrecio(rsProductos.getFloat("PRECIO"));

                    Producto producto = new Producto();
                    producto.setNombreProducto(rsProductos.getString("NOMBRE_PRODUCTO"));
                    producto.setPrecioUnitario(rsProductos.getFloat("PRECIO_UNITARIO"));
                    producto.setPedir(pedir);

                    ultimoPedido.addProdutcto(producto);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.motorSql.disconnect();
        }

        return ultimoPedido;
    }
}