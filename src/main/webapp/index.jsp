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
            <div class="row">
                <h1 class="text-center bg-success">COMPONENTES</h1>
                <c:forEach var="item" items="${componentes}">
                    <div class="col-sm-4">
                        <div class="card border-success text-center">
                            <div class="card-header">
                                <label class="h4">${item.nombre}</label>
                            </div>
                            <div class="card-body">
                                <img src="${item.ruta}" alt="${item.nombre}" width="200" height="200">
                            </div>
                            <div class="card-footer">
                                <label class="h6">${item.precio}</label>
                                <br>
                                <label class="h6">${item.descripcion}</label>
                                <div>
                                    <a href="CarritoControlador?action=add&id=${item.id}&nombre=${item.nombre}&ruta=${item.ruta}&descripcion=${item.descripcion}&precio=${item.precio}" class="btn btn-outline-info"><i class="bi bi-cart-plus"></i> Añadir</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="container mt-2">
            <div class="row">
                <h1 class="text-center bg-success">PERIFERICOS</h1>
                <c:forEach var="item" items="${perifericos}">
                    <div class="col-sm-4">
                        <div class="card border-success text-center">
                            <div class="card-header">
                                <label class="h4">${item.nombre}</label>
                            </div>
                            <div class="card-body">
                                <img src="${item.ruta}" alt="${item.nombre}" width="200" height="200">
                            </div>
                            <div class="card-footer">
                                <label class="h6">${item.precio}</label>
                                <br>
                                <label class="h6">${item.descripcion}</label>
                                <div>
                                    <a href="CarritoControlador?action=add&id=${item.id}&nombre=${item.nombre}&ruta=${item.ruta}&descripcion=${item.descripcion}&precio=${item.precio}" class="btn btn-outline-info"><i class="bi bi-cart-plus"></i> Añadir</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
