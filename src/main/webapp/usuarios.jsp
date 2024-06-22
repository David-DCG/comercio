<%
    if (session.getAttribute("logueado") != "OK") {
        response.sendRedirect("login.jsp");
    }
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <title>Comercio</title>
    </head>
    <body>
        <jsp:include page="menu.jsp">
            <jsp:param name="opcion" value="usuarios"/>
        </jsp:include>
        <div class="container ">
            <h1 class="text-center">Lista de Usuarios</h1>
            <a class="btn btn-primary btn-sm" href="MainUsuario?action=add"><i class="bi bi-plus-circle"></i> Nuevo</a>
            <br>
            <br>
            <table class="table table-hover table-bordered">
                <tr class="table-dark">
                    <th>ID</th>
                    <th>NOMBRES</th>
                    <th>APELLIDOS</th>
                    <th>CORREO</th>
                    <th>PASSWORD</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${usuarios}">
                    <tr class="table-success">
                        <td>${item.id}</td>
                        <td>${item.nombres}</td>
                        <td>${item.apellidos}</td>
                        <td>${item.correo}</td>
                        <td>${item.password}</td>
                        <td><a class="btn btn-warning" href="MainUsuario?action=edit&id=${item.id}"><i class="bi bi-pencil-square"></i></a></td>
                        <td><a class="btn btn-danger" href="MainUsuario?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar?'))"><i class="bi bi-trash3"></i></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
