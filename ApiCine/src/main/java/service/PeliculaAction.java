package service;

import com.google.gson.Gson;
import model.dao.PeliculaDAO;
import model.entities.Pelicula;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PeliculaAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FIND":
                cadDestino = findByFilter(request, response);
                break;
            case "FIND_CATEGORIA":
                cadDestino = findCategoria(request, response);
                break;

        }
        return cadDestino;
    }


    private String findAll(HttpServletRequest request, HttpServletResponse response){
        PeliculaDAO pelicula = new PeliculaDAO();
        ArrayList<Pelicula> lstpelis = pelicula.findAll();


        Gson gson = new Gson();
        return gson.toJson(lstpelis);
        //return Pelicula.fromArrayToJson(lstpelis);
    }

    private String findCategoria(HttpServletRequest request, HttpServletResponse response){
        String nombreCategoria = request.getParameter("NOMBRE_CATEGORIA");
        PeliculaDAO peliculaCategoria = new PeliculaDAO();
        ArrayList<Pelicula> lstpelis = peliculaCategoria.findCategoria(nombreCategoria);

        Gson gson = new Gson();
        return gson.toJson(lstpelis);
        //return Pelicula.fromArrayToJson(lstpelis);
    }

    private String findByFilter(HttpServletRequest request, HttpServletResponse response){
        int idPelicula = Integer.parseInt(request.getParameter("ID_PELICULA"));
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        Pelicula pelicula = peliculaDAO.findByFilter(idPelicula);

        Gson gson = new Gson();
        return gson.toJson(pelicula);
    }

}
