package com.emergentes.controller;

import com.emergentes.bean.BeanPeriferico;
import com.emergentes.entidades.Periferico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainPeriferico", urlPatterns = {"/MainPeriferico"})
public class MainPeriferico extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BeanPeriferico dao = new BeanPeriferico();
            Integer id;
            Periferico per = new Periferico();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("periferico", per);
                    request.getRequestDispatcher("formperiferico.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    per = dao.buscar(id);
                    System.out.println(per);
                    request.setAttribute("periferico", per);
                    request.getRequestDispatcher("formperiferico.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/MainPeriferico");
                    break;
                case "view":
                    List<Periferico> lista = dao.listartodos();
                    request.setAttribute("perifericos", lista);
                    request.getRequestDispatcher("perifericos.jsp").forward(request, response);
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
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String marca = request.getParameter("marca");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String ruta = request.getParameter("ruta");
        Periferico per = new Periferico();
        per.setId(id);
        per.setNombre(nombre);
        per.setTipo(tipo);
        per.setMarca(marca);
        per.setDescripcion(descripcion);
        per.setPrecio(precio);
        per.setRuta(ruta);
        if (id == 0) {
            try {
                BeanPeriferico dao = new BeanPeriferico();
                dao.insertar(per);
                response.sendRedirect(request.getContextPath() + "/MainPeriferico");
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }

        } else {
            try {
                BeanPeriferico dao = new BeanPeriferico();
                dao.editar(per);
                response.sendRedirect(request.getContextPath() + "/MainPeriferico");
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }
        }
    }
}
