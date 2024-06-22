package com.emergentes.controller;

import com.emergentes.entidades.Usuario;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginControlador", urlPatterns = {"/LoginControlador"})
public class LoginControlador extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("UPcomercio");
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (request.getParameter("action") == null) ? "view" : request.getParameter("action");
        if (action.equals("logout")) {
            HttpSession ses = request.getSession();
            ses.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.password = :password", Usuario.class);
            query.setParameter("correo", correo);
            query.setParameter("password", password);
            Usuario usuario = query.getSingleResult();

            if (usuario != null) {
                HttpSession ses = request.getSession();
                ses.setAttribute("logueado", "OK");
                ses.setAttribute("usuario", usuario.getNombres() + " " + usuario.getApellidos());
                response.sendRedirect("MainCliente");
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
            response.sendRedirect("login.jsp");
        } finally {
            em.close();
        }
    }
}
