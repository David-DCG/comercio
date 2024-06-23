package com.emergentes.controller;

import com.emergentes.bean.BeanCliente;
import com.emergentes.bean.BeanComponente;
import com.emergentes.bean.BeanCompra;
import com.emergentes.bean.BeanPeriferico;
import com.emergentes.entidades.Cliente;
import com.emergentes.entidades.Componente;
import com.emergentes.entidades.Compra;
import com.emergentes.entidades.Periferico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CompraControlador", urlPatterns = {"/CompraControlador"})
public class CompraControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id;
            BeanCompra daoCompra = new BeanCompra();
            BeanCliente daoCliente = new BeanCliente();
            BeanComponente daoComponente = new BeanComponente();
            BeanPeriferico daoPeriferico = new BeanPeriferico();

            Compra com = new Compra();
            List<Cliente> lista_clientes;
            List<Componente> lista_componentes;
            List<Periferico> lista_perifericos;
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "add";
            switch (action) {
                case "add":
                    lista_clientes = daoCliente.listartodos();
                    lista_componentes = daoComponente.listartodos();
                    lista_perifericos = daoPeriferico.listartodos();
                    request.setAttribute("cliente", lista_clientes);
                    request.setAttribute("componente", lista_componentes);
                    request.setAttribute("periferico", lista_perifericos);
                    request.setAttribute("compra", com);
                    request.getRequestDispatcher("clientecompra.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanCompra daoCompra = new BeanCompra();
        BeanCliente daoCliente = new BeanCliente();
        BeanComponente daoComponente = new BeanComponente();
        BeanPeriferico daoPeriferico = new BeanPeriferico();
        //id es propia de la tabla
        int id = Integer.parseInt(request.getParameter("id"));
        //Llamando Llaves Foraneas
        int id_cli = Integer.parseInt(request.getParameter("id_cli"));
        int id_com = Integer.parseInt(request.getParameter("id_com"));
        int id_per = Integer.parseInt(request.getParameter("id_per"));
        //fecha es propia de la tabla
        String fecha = request.getParameter("fecha");

        Cliente cli = daoCliente.buscar(id_cli);
        Componente com = daoComponente.buscar(id_com);
        Periferico per = daoPeriferico.buscar(id_per);
        Compra co = new Compra();
        co.setId(id);
        co.setIdCli(cli);
        co.setIdCom(com);
        co.setIdPer(per);
        co.setFecha(fecha);

        if (id > 0) {
            daoCompra.editar(co);
        } else {
            daoCompra.insertar(co);
        }
        response.sendRedirect("CarritoControlador");
    }
}
