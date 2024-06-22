<%
    String opcion = request.getParameter("opcion");
%>

<div class="p-5 bg-success text-white text-center">
    <h1>Bienvenido al Modo Administrador</h1>
    <p>Aqui podras realizar la administracion y publicacion de productos en el sitio!</p> 
</div>

<nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarColor02">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link <%=(opcion.equals("clientes") ? "active" : "")%>" href="MainCliente">Clientes
                        <span class="visually-hidden">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=(opcion.equals("usuarios") ? "active" : "")%>" href="MainUsuario">Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=(opcion.equals("componentes") ? "active" : "")%>" href="MainComponente">Componentes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=(opcion.equals("perifericos") ? "active" : "")%>" href="MainPeriferico">Perifericos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=(opcion.equals("ventas") ? "active" : "")%>" href="MainVenta">Ventas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=(opcion.equals("compras") ? "active" : "")%>" href="MainCompra">Compras</a>
                </li>
            </ul>
            <a href="LoginControlador?action=logout" class="btn btn-secondary my-2 my-sm-0">Cerrar Sesion</a>
        </div>
    </div>
</nav>

