package com.emergentes.controller;

import com.emergentes.bean.BeanComponente;
import com.emergentes.bean.BeanPeriferico;
import com.emergentes.bean.BeanUsuario;
import com.emergentes.bean.BeanVenta;
import com.emergentes.entidades.Componente;
import com.emergentes.entidades.Periferico;
import com.emergentes.entidades.Usuario;
import com.emergentes.entidades.Ventas;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainVenta", urlPatterns = {"/MainVenta"})
public class MainVenta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id;
            BeanVenta daoVenta = new BeanVenta();
            BeanUsuario daoUsuario = new BeanUsuario();
            BeanComponente daoComponente = new BeanComponente();
            BeanPeriferico daoPeriferico = new BeanPeriferico();

            Ventas ven = new Ventas();
            List<Usuario> lista_usuarios;
            List<Componente> lista_componentes;
            List<Periferico> lista_perifericos;
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    lista_usuarios = daoUsuario.listartodos();
                    lista_componentes = daoComponente.listartodos();
                    lista_perifericos = daoPeriferico.listartodos();
                    request.setAttribute("usuario", lista_usuarios);
                    request.setAttribute("componente", lista_componentes);
                    request.setAttribute("periferico", lista_perifericos);
                    request.setAttribute("venta", ven);
                    request.getRequestDispatcher("formventa.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    ven = daoVenta.buscar(id);
                    lista_usuarios = daoUsuario.listartodos();
                    lista_componentes = daoComponente.listartodos();
                    lista_perifericos = daoPeriferico.listartodos();
                    request.setAttribute("usuario", lista_usuarios);
                    request.setAttribute("componente", lista_componentes);
                    request.setAttribute("periferico", lista_perifericos);
                    request.setAttribute("venta", ven);
                    request.getRequestDispatcher("formventa.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    daoVenta.eliminar(id);
                    response.sendRedirect("MainVenta");
                    break;
                case "view":
                    List<Ventas> lista = daoVenta.listartodos();
                    request.setAttribute("ventas", lista);
                    request.getRequestDispatcher("ventas.jsp").forward(request, response);
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
        BeanVenta daoVenta = new BeanVenta();
        BeanUsuario daoUsuario = new BeanUsuario();
        BeanComponente daoComponente = new BeanComponente();
        BeanPeriferico daoPeriferico = new BeanPeriferico();
        //id es propia de la tabla
        int id = Integer.parseInt(request.getParameter("id"));
        //Llamando Llaves Foraneas
        int id_usr = Integer.parseInt(request.getParameter("id_usr"));
        int id_com = Integer.parseInt(request.getParameter("id_com"));
        int id_per = Integer.parseInt(request.getParameter("id_per"));
        //Fecha es propia de la tabla
        String fecha = request.getParameter("fecha");

        Usuario usr = daoUsuario.buscar(id_usr);
        Componente com = daoComponente.buscar(id_com);
        Periferico per = daoPeriferico.buscar(id_per);
        Ventas ven = new Ventas();
        ven.setId(id);
        ven.setIdUsr(usr);
        ven.setIdCom(com);
        ven.setIdPer(per);
        ven.setFecha(fecha);

        if (id > 0) {
            daoVenta.editar(ven);
        } else {
            daoVenta.insertar(ven);
        }
        response.sendRedirect("MainVenta");
    }
}
