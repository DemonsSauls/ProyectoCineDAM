package service;

import com.google.gson.Gson;
import model.dao.ProductoDAO;
import model.entities.Producto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ProductoAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
        }
        return cadDestino;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response){
        ProductoDAO producto = new ProductoDAO();
        ArrayList<Producto> lstprod = producto.findAll();

        Gson gson = new Gson();
        return gson.toJson(lstprod);
    }
}
