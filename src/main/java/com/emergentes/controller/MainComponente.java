package com.emergentes.controller;

import com.emergentes.bean.BeanComponente;
import com.emergentes.entidades.Componente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@WebServlet(name = "MainComponente", urlPatterns = {"/MainComponente"})
@MultipartConfig
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
        //int id;
        //id = Integer.parseInt(request.getParameter("id"));
        //String nombre = request.getParameter("nombre");
        //String tipo = request.getParameter("tipo");
        //String marca = request.getParameter("marca");
        //String descripcion = request.getParameter("descripcion");
        //double precio = Double.parseDouble(request.getParameter("precio"));
        //String ruta = request.getParameter("ruta");
        //Componente com = new Componente();
        //com.setId(id);
        //com.setNombre(nombre);
        //com.setTipo(tipo);
        //com.setMarca(marca);
        //com.setDescripcion(descripcion);
        //com.setPrecio(precio);
        //com.setRuta(ruta);
        //if (id == 0) {
        //    try {
        //        BeanComponente dao = new BeanComponente();
        //        dao.insertar(com);
        //        response.sendRedirect(request.getContextPath() + "/MainComponente");
        //    } catch (Exception ex) {
        //        System.out.println("Error" + ex.getMessage());
        //    }

        //} else {
        //    try {
        //        BeanComponente dao = new BeanComponente();
        //        dao.editar(com);
        //        response.sendRedirect(request.getContextPath() + "/MainComponente");
        //    } catch (Exception ex) {
        //        System.out.println("Error" + ex.getMessage());
        //    }
        //}
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String marca = request.getParameter("marca");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));

        // Procesar la imagen
        Part filePart = request.getPart("imagen");
        String fileName = getFileName(filePart);
        String ruta = "";

        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            filePart.write(uploadPath + File.separator + fileName);
            ruta = "uploads/" + fileName;
        } else {
            ruta = request.getParameter("ruta");  // Mantener la ruta existente si no se carga una nueva imagen
        }

        Componente com = new Componente();
        com.setId(id);
        com.setNombre(nombre);
        com.setTipo(tipo);
        com.setMarca(marca);
        com.setDescripcion(descripcion);
        com.setPrecio(precio);
        com.setRuta(ruta);

        try {
            BeanComponente dao = new BeanComponente();
            if (id == 0) {
                dao.insertar(com);
            } else {
                dao.editar(com);
            }
            response.sendRedirect(request.getContextPath() + "/MainComponente");
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
