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
            <jsp:param name="opcion" value="componentes"/>
        </jsp:include>
        <div class="container ">
            <h1 class="text-center">Lista de Componentes</h1>
            <a class="btn btn-primary btn-sm" href="MainComponente?action=add"><i class="bi bi-plus-circle"></i> Nuevo</a>
            <br>
            <br>
            <table class="table table-hover table-bordered">
                <tr class="table-dark">
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>TIPO</th>
                    <th>MARCA</th>
                    <th>DESCRIPCION</th>
                    <th>PRECIO</th>
                    <th>IMAGEN</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${componentes}">
                    <tr class="table-success">
                        <td>${item.id}</td>
                        <td>${item.nombre}</td>
                        <td>${item.tipo}</td>
                        <td>${item.marca}</td>
                        <td>${item.descripcion}</td>
                        <td>${item.precio}</td>                      
                        <%--<td>${item.ruta}</td>--%>
                        <td><img src="${item.ruta}" alt="${item.nombre}" width="100"></td>
                        <td><a class="btn btn-warning" href="MainComponente?action=edit&id=${item.id}"><i class="bi bi-pencil-square"></i></a></td>
                        <td><a class="btn btn-danger" href="MainComponente?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar?'))"><i class="bi bi-trash3"></i></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
