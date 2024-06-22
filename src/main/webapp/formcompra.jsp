<%
    if (session.getAttribute("logueado") != "OK") {
        response.sendRedirect("login.jsp");
    }
%>
<%@page import="com.emergentes.entidades.Cliente"%>
<%@page import="com.emergentes.entidades.Compra"%>
<%@page import="com.emergentes.entidades.Periferico"%>
<%@page import="com.emergentes.entidades.Componente"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Ventas"%>
<%
    Compra compra = (Compra) request.getAttribute("compra");
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("cliente");
    List<Componente> componentes = (List<Componente>) request.getAttribute("componente");
    List<Periferico> perifericos = (List<Periferico>) request.getAttribute("periferico");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <title>Comercio</title>
    </head>
    <body>
        <jsp:include page="menu.jsp">
            <jsp:param name="opcion" value="compras" />
        </jsp:include>
        <div class="container ">
            <h1 class="text-center">Formulario de Compras</h1>
            <br>
            <div class="container col-4">
                <div class="card">
                    <div class="card-header">
                        Ventas
                    </div>
                    <div class="card-body">
                        <form action="MainCompra" method="post">
                            <input type="hidden" name="id" value="<%= compra.getId()%>">
                            <div class="mb-3">
                                <label for="cliente" class="form-label">Cliente:</label>
                                <select name="id_cli" class="form-control">
                                    <option value="">-- Seleccione --</option>
                                    <%
                                        for (Cliente item : clientes) {
                                    %>
                                    <option value="<%= item.getId()%>" <%= (item.getId() == compra.getIdCli().getId()) ? "selected" : ""%>><%= item.getNombres()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="componente" class="form-label">Componente:</label>
                                <select name="id_com" class="form-control">
                                    <option value="">-- Seleccione --</option>
                                    <%
                                        for (Componente item : componentes) {
                                    %>
                                    <option value="<%= item.getId()%>" <%= (item.getId() == compra.getIdCom().getId()) ? "selected" : ""%>><%= item.getNombre()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="periferico" class="form-label">Periferico:</label>
                                <select name="id_per" class="form-control">
                                    <option value="">-- Seleccione --</option>
                                    <%
                                        for (Periferico item : perifericos) {
                                    %>
                                    <option value="<%= item.getId()%>" <%= (item.getId() == compra.getIdPer().getId()) ? "selected" : ""%>><%= item.getNombre()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="fecha" class="form-label">Fecha:</label>
                                <input type="date" name="fecha" id="fecha" value="<%= compra.getFecha()%>" class="form-control" placeholder="">
                            </div>
                            <button type="submit" class="btn btn-success"><i class="bi bi-floppy"> Enviar</i></button>
                            <a href="MainCompra" class="btn btn-info"><i class="bi bi-arrow-return-left"> Volver</i></a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
