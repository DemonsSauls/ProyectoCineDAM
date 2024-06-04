package controllers;

import service.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String action = request.getParameter("ACTION");
            if (action == null || action.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ACTION parameter is missing or empty");
                return;
            }

            String[] arrayAction = action.split("\\.");
            if (arrayAction.length == 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ACTION parameter format");
                return;
            }

            switch (arrayAction[0]) {
                case "USUARIO":
                    UsuarioAction usuarioAction = new UsuarioAction();
                    String respUser = usuarioAction.execute(request, response);
                    response.getWriter().write(respUser);
                    break;
                case "PELICULA":
                    String respPelicula = new PeliculaAction().execute(request, response);
                    response.getWriter().write(respPelicula);
                    break;
                case "SALA":
                    String respSala = new SalaAction().execute(request, response);
                    response.getWriter().write(respSala);
                    break;
                case "BUTACA":
                    String respButaca = new ButacaAction().execute(request, response);
                    response.getWriter().write(respButaca);
                    break;
                case "PRODUCTO":
                    String respProducto = new ProductoAction().execute(request, response);
                    response.getWriter().write(respProducto);
                    break;
                case "PEDIDO":
                    String respPedido = new PedidoAction().execute(request, response);
                    response.getWriter().write(respPedido);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown ACTION parameter: " + arrayAction[0]);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
        
    }
}
