package com.emergentes.controller;

import com.emergentes.bean.BeanCliente;
import com.emergentes.entidades.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Este Servlet "ClienteControlador" Registra al cliente fuera del modo administrador
@WebServlet(name = "ClienteControlador", urlPatterns = {"/ClienteControlador"})
public class ClienteControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BeanCliente dao = new BeanCliente();
            Integer id;
            Cliente cli = new Cliente();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("cliente", cli);
                    request.getRequestDispatcher("regcliente.jsp").forward(request, response);
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
        int celular = Integer.parseInt(request.getParameter("celular"));
        Cliente cli = new Cliente();
        cli.setId(id);
        cli.setNombres(nombres);
        cli.setApellidos(apellidos);
        cli.setCorreo(correo);
        cli.setCelular(celular);
        if (id == 0) {
            try {
                BeanCliente dao = new BeanCliente();
                dao.insertar(cli);
                response.sendRedirect(request.getContextPath() + "/IndexControlador");
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }

        } else {
            try {
                BeanCliente dao = new BeanCliente();
                dao.editar(cli);
                response.sendRedirect(request.getContextPath() + "/IndexControlador");
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }
        }
    }
}
