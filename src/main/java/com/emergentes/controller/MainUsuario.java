package com.emergentes.controller;

import com.emergentes.bean.BeanUsuario;
import com.emergentes.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainUsuario", urlPatterns = {"/MainUsuario"})
public class MainUsuario extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BeanUsuario dao = new BeanUsuario();
            Integer id;
            Usuario usr = new Usuario();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("usuario", usr);
                    request.getRequestDispatcher("formusuario.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    usr = dao.buscar(id);
                    System.out.println(usr);
                    request.setAttribute("usuario", usr);
                    request.getRequestDispatcher("formusuario.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/MainUsuario");
                    break;
                case "view":
                    List<Usuario> lista = dao.listartodos();
                    request.setAttribute("usuarios", lista);
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        id = Integer.parseInt(request.getParameter("id"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        Usuario usr = new Usuario();
        usr.setId(id);
        usr.setNombres(nombres);
        usr.setApellidos(apellidos);
        usr.setCorreo(correo);
        usr.setPassword(password);
        if (id == 0) {
            try {
                BeanUsuario dao = new BeanUsuario();
                dao.insertar(usr);
                response.sendRedirect(request.getContextPath() + "/MainUsuario");
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }
            
        } else {
            try {
                BeanUsuario dao = new BeanUsuario();
                dao.editar(usr);
                response.sendRedirect(request.getContextPath() + "/MainUsuario");
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }
        }
    }
}
