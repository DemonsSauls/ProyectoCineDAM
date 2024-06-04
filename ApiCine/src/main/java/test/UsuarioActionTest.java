package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.Controller;
import model.dao.UsuarioDAO;
import model.entities.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.UsuarioAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class UsuarioActionTest {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcesarLogin_UsuarioExiste() throws IOException, ServletException {

        // Configurar el comportamiento esperado del objeto request
        when(request.getParameter("ACTION")).thenReturn("USUARIO.LOGIN");
        when(request.getParameter("NOMBRE")).thenReturn("Saul");
        when(request.getParameter("PASSWORD")).thenReturn("1234");

        Controller controller = new Controller();

        //UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Call the servlet's doGet method
        //controller.doGet(request, response);

        // Verify that the response is as expected
        writer.flush();
        String result = stringWriter.toString().trim();



        UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombreUsuario("Saul");
        usuario.setContrasena("1234");
        when(usuarioDAO.login("Saul", "1234")).thenReturn(usuario);

        // Ejecutar el método procesarLogin() del UsuarioAction
        UsuarioAction usuarioAction = new UsuarioAction();
        String result2 = usuarioAction.procesarLogin(request, response);

        // Verificar el resultado
        assertEquals("{\"idUsuario\":1,\"matricula\":\"5432FRT\",\"nombreUsuario\":\"Saul\",\"correoElectronico\":\"saul@svalero.com\"}", result2);
    }
/*
    @Test
    public void testProcesarLogin_UsuarioNoExiste() {
        // Configurar el comportamiento esperado del objeto request
        when(request.getParameter("NOMBRE")).thenReturn("usuario");
        when(request.getParameter("PASSWORD")).thenReturn("password");

        // Simular la respuesta del DAO
        UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);
        when(usuarioDAO.login("usuario", "password")).thenReturn(null);

        // Ejecutar el método procesarLogin() del UsuarioAction
        UsuarioAction usuarioAction = new UsuarioAction();
        String result = usuarioAction.procesarLogin(request, response);

        // Verificar el resultado
        assertEquals("{\"error\":\"Usuario no encontrado\"}", result);
    }*/

}
