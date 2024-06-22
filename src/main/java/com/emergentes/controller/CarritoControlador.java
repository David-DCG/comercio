package com.emergentes.controller;

import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CarritoControlador", urlPatterns = {"/CarritoControlador"})
public class CarritoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        if (action != null) {
            switch (action) {
                case "add":
                    int id = Integer.parseInt(request.getParameter("id"));
                    String nombre = request.getParameter("nombre");
                    double precio = Double.parseDouble(request.getParameter("precio"));
                    Producto producto = new Producto();
                    producto.setId(id);
                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    carrito.add(producto);
                    break;
                case "remove":
                    id = Integer.parseInt(request.getParameter("id"));
                    Iterator<Producto> iterator = carrito.iterator();
                    while (iterator.hasNext()) {
                        Producto productoEliminar = iterator.next();
                        if (productoEliminar.getId() == id) {
                            iterator.remove();
                            break;
                        }
                    }
                    break;
                case "clear":
                    carrito.clear();
                    break;
            }
        }

        session.setAttribute("carrito", carrito);
        response.sendRedirect("carrito.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
