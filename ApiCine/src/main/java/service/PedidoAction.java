package service;

import com.google.gson.Gson;
import model.dao.PedidoDAO;
import model.dao.PeliculaDAO;
import model.entities.Pelicula;
import model.entities.RealizarPedido;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PedidoAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "PAGAR":
                cadDestino = pagar(request, response);
                break;
        }
        return cadDestino;
    }

    private String pagar(HttpServletRequest request, HttpServletResponse response){
        StringBuilder requestBody = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar el error de lectura del cuerpo de la solicitud
        }

        // Utiliza Gson para convertir el JSON del cuerpo de la solicitud a un objeto Pedido
        Gson gson = new Gson();
        RealizarPedido realizarPedido = gson.fromJson(requestBody.toString(), RealizarPedido.class);


        PedidoDAO pedido = new PedidoDAO();
        if(pedido.pagar(realizarPedido)){
            return "Pedido realizado exitosamente";
        }
        else {
            return "Error al realizar el pedido";
        }
    }

}
