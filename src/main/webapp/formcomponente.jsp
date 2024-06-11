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
            <h1 class="text-center">Registro de Componentes</h1>
            <br>
            <div class="container col-4">
                <div class="card">
                    <div class="card-header">
                        Componentes
                    </div>
                    <div class="card-body">
                        <form action="MainComponente" method="post">
                            <input type="hidden" name="id" value="${componente.id}">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre:</label>
                                <input type="text" name="nombre" id="nombre" value="${componente.nombre}" class="form-control" placeholder="Escriba el nombre">
                            </div>
                            <div class="mb-3">
                                <label for="tipo" class="form-label">Tipo:</label>
                                <input type="text" name="tipo" id="tipo" value="${componente.tipo}" class="form-control" placeholder="Escriba el tipo">
                            </div>
                            <div class="mb-3">
                                <label for="marca" class="form-label">Marca:</label>
                                <input type="text" name="marca" id="marca" value="${componente.marca}" class="form-control" placeholder="Escriba la marca">
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descripcion:</label>
                                <input type="text" name="descripcion" id="descripcion" value="${componente.descripcion}" class="form-control" placeholder="Ingresar la descripcion">
                            </div>
                            <div class="mb-3">
                                <label for="precio" class="form-label">Precio:</label>
                                <input type="text" name="precio" id="precio" value="${componente.precio}" class="form-control" placeholder="Ingresar el precio">
                            </div>
                            <div class="mb-3">
                                <label for="ruta" class="form-label">ruta:</label>
                                <input type="text" name="ruta" id="ruta" value="${componente.ruta}" class="form-control" placeholder="Ingresar la descripcion">
                            </div>
                            <button type="submit" class="btn btn-success"><i class="bi bi-floppy"> Enviar</i></button>
                            <a href="MainComponente" class="btn btn-info"><i class="bi bi-arrow-return-left"> Volver</i></a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
