<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <title>Comercio</title>
    </head>
    <body>
        <jsp:include page="menu2.jsp"/>
        <div class="container mt-2">
            <h1 class="text-center bg-success">Carrito de Compras</h1>
            <c:if test="${not empty carrito}">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Imagen</th>
                            <th>Descripcion</th>
                            <th>Precio</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${carrito}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.nombre}</td>
                                <td><img src="${item.ruta}" alt="${item.nombre}" width="100"></td>
                                <td>${item.descripcion}</td>
                                <td>${item.precio}</td>
                                <td>
                                    <a href="CompraControlador?action=add" class="btn btn-outline-success"><i class="bi bi-bag"></i> Comprar</a>
                                </td>
                                <td>
                                    <a href="CarritoControlador?action=remove&id=${item.id}" class="btn btn-outline-danger"><i class="bi bi-trash"></i> Quitar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="CarritoControlador?action=clear" class="btn btn-outline-warning"><i class="bi bi-cart-x"></i> Vaciar Carrito</a>
                <a href="IndexControlador" class="btn btn-outline-primary">Seguir Comprando</a>
            </c:if>
            <c:if test="${empty carrito}">
                <h3 class="text-center">El carrito está vacío</h3>
                <a href="IndexControlador" class="btn btn-outline-primary">Ir a la tienda</a>
            </c:if>
        </div>
    </body>
</html>
