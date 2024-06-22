package com.emergentes.controller;

import com.emergentes.bean.BeanComponente;
import com.emergentes.bean.BeanPeriferico;
import com.emergentes.entidades.Componente;
import com.emergentes.entidades.Periferico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IndexControlador", urlPatterns = {"/IndexControlador"})
public class IndexControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanComponente daoComponente = new BeanComponente();
        BeanPeriferico daoPeriferico = new BeanPeriferico();

        List<Componente> lista_componentes = daoComponente.listartodos();
        List<Periferico> lista_perifericos = daoPeriferico.listartodos();

        request.setAttribute("componentes", lista_componentes);
        request.setAttribute("perifericos", lista_perifericos);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
