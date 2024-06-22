<%
    if (session.getAttribute("logueado") != "OK") {
        response.sendRedirect("login.jsp");
    }
%>
<%@page import="com.emergentes.entidades.Compra"%>
<%@page import="java.util.List"%>
<%
    List<Compra> compras = (List<Compra>) request.getAttribute("compras");
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
            <jsp:param name="opcion" value="compras"/>
        </jsp:include> 
        <div class="container ">
            <h1 class="text-center">Lista de Compras</h1>
            <a class="btn btn-primary btn-sm" href="MainCompra?action=add"><i class="bi bi-plus-circle"></i> Nuevo</a>
            <br>
            <br>
            <table class="table table-hover table-bordered">
                <tr class="table-dark">
                    <th>ID</th>
                    <th>CLIENTE</th>
                    <th>COMPONENTE</th>
                    <th>PERIFERICO</th>
                    <th>FECHA</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    for (Compra item : compras) {
                %>
                <tr class="table-success">
                    <td><%= item.getId()%></td>
                    <td><%= item.getIdCli().getNombres()%></td>
                    <td><%= item.getIdCom().getNombre()%></td>
                    <td><%= item.getIdPer().getNombre()%></td>
                    <td><%= item.getFecha()%></td>
                    <td><a class="btn btn-warning" href="MainCompra?action=edit&id=<%= item.getId()%>"><i class="bi bi-pencil-square"></i></a></td>
                    <td><a class="btn btn-danger" href="MainCompra?action=delete&id=<%= item.getId()%>" onclick="return(confirm('Esta seguro de eliminar?'))"><i class="bi bi-trash3"></i></a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
