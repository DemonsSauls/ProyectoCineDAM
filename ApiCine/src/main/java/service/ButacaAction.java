package service;

import com.google.gson.Gson;
import model.dao.ButacaDAO;
import model.dao.PeliculaDAO;
import model.entities.Butaca;
import model.entities.Pelicula;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ButacaAction implements IAction{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND":
                cadDestino = findPorSala(request, response);
                break;
            case "RESERVAR":
                cadDestino = reservar(request, response);
                break;
            case "DESRESERVAR":
                cadDestino = desreservar(request, response);
                break;
        }

        return cadDestino;
    }


    private String findPorSala(HttpServletRequest request, HttpServletResponse response){
        int proyeccion = Integer.parseInt(request.getParameter("ID_PROYECCION"));

        ButacaDAO butaca = new ButacaDAO();
        ArrayList<Butaca> lstbutacas = butaca.findPorSala(proyeccion);


        Gson gson = new Gson();
        return gson.toJson(lstbutacas);
    }

    private String reservar(HttpServletRequest request, HttpServletResponse response){
        int id_butaca = Integer.parseInt(request.getParameter("ID_BUTACA"));

        ButacaDAO butaca = new ButacaDAO();
        String salida = butaca.reservar(id_butaca);

        return salida;
    }

    private String desreservar(HttpServletRequest request, HttpServletResponse response){
        int id_butaca = Integer.parseInt(request.getParameter("ID_BUTACA"));

        ButacaDAO butaca = new ButacaDAO();
        String salida = butaca.desreservar(id_butaca);

        return salida;
    }

}


