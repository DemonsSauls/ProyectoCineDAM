package service;

import com.google.gson.Gson;
import model.dao.SalaDAO;
import model.entities.Sala;
import model.entities.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class SalaAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND":
                cadDestino = findPorPeli(request, response);
                break;
        }
        return cadDestino;
    }

    private String findPorPeli(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("ID_PELICULA"));

        SalaDAO sala = new SalaDAO();

        ArrayList<Sala> sala2 = sala.findPorPeli(id);

        Gson gson = new Gson();
        return gson.toJson(sala2);
    }
}
