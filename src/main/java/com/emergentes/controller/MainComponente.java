package com.emergentes.controller;

import com.emergentes.bean.BeanComponente;
import com.emergentes.entidades.Componente;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet(name = "MainComponente", urlPatterns = {"/MainComponente"})
public class MainComponente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BeanComponente dao = new BeanComponente();
            Integer id;
            Componente com = new Componente();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("componente", com);
                    request.getRequestDispatcher("formcomponente.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    com = dao.buscar(id);
                    System.out.println(com);
                    request.setAttribute("componente", com);
                    request.getRequestDispatcher("formcomponente.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    com = dao.buscar(id);
                    dao.eliminar(id);
                    response.sendRedirect(request.getContextPath() + "/MainComponente");
                    break;
                case "view":
                    List<Componente> lista = dao.listartodos();
                    request.setAttribute("componentes", lista);
                    request.getRequestDispatcher("componentes.jsp").forward(request, response);
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

        Componente com = new Componente();
        com.setId(id);
        com.setNombre(nombre);
        com.setTipo(tipo);
        com.setMarca(marca);
        com.setDescripcion(descripcion);
        com.setPrecio(precio);
        com.setRuta(ruta);

        if (id == 0) {
            try {
                BeanComponente dao = new BeanComponente();
                dao.insertar(com);
                response.sendRedirect(request.getContextPath() + "/MainComponente");
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }

        } else {
            try {
                BeanComponente dao = new BeanComponente();
                dao.editar(com);
                response.sendRedirect(request.getContextPath() + "/MainComponente");
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
            }
        }
        try {

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String content : contentDisposition.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
