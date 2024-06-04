package service;

import com.google.gson.Gson;
import model.dao.UsuarioDAO;
import model.email.EmailSender;
import model.email.PdfGenerator;
import model.entities.Entrada;
import model.entities.Pedido;
import model.entities.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UsuarioAction implements IAction{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "LOGIN":
                cadDestino = login(request, response);
                break;
            case "REGISTER":
                cadDestino = register(request, response);
                break;
            case "PEDIDOS":
                cadDestino = pedidos(request, response);
                break;
            case "ENTRADA":
                try {
                    cadDestino = findPedido(request, response);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        return cadDestino;
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        String nombre = request.getParameter("NOMBRE");
        String password = request.getParameter("PASSWORD");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario2 = usuarioDAO.login(nombre, password);

        if(usuario2==null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String respuesta = "Usuario o contraseña incorrecta";
            Gson gson2 = new Gson();
            return gson2.toJson(respuesta);
        }else {

            Gson gson = new Gson();
            return gson.toJson(usuario2);
        }
    }

    public String procesarLogin(HttpServletRequest request, HttpServletResponse response) {
        String nombre = request.getParameter("NOMBRE");
        String password = request.getParameter("PASSWORD");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.login(nombre, password);

        if (usuario != null) {
            // Usuario encontrado, devolver los datos del usuario como JSON
            Gson gson = new Gson();
            return gson.toJson(usuario);
        } else {
            // Usuario no encontrado, devolver un mensaje de error
            return "{\"error\":\"Usuario no encontrado\"}";
        }
    }

    private String register(HttpServletRequest request, HttpServletResponse response){
        String nombre = request.getParameter("NOMBRE");
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String matricula = request.getParameter("MATRICULA");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(usuarioDAO.register(nombre, email, password, matricula)) {
            return "Usuario creado correctamente";
        }else {
            return "Error al crear el usuario";
        }
    }

    private String pedidos(HttpServletRequest request, HttpServletResponse response) {
        String idUsuario = request.getParameter("ID_USUARIO");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Pedido> lstpedido = usuarioDAO.pedidos(idUsuario);

        Gson gson = new Gson();
        return gson.toJson(lstpedido);

    }

    private String findPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idUsuario = request.getParameter("ID_USUARIO");
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String text = request.getParameter("text");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Pedido pedido = usuarioDAO.findPedido(idUsuario);


        if (pedido != null) {
            String pdfFilePath = PdfGenerator.generatePdf(pedido);
            EmailSender.sendEmail(to, subject, text, pdfFilePath);
            return "Correo enviado exitosamente con el PDF adjunto.";
        } else {
            return "No se encontró ningún pedido para el usuario proporcionado.";
        }

    }
}
