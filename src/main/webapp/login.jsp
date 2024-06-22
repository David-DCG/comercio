<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1 class="text-center">Iniciar Session</h1>
        <div class="container">
            <div class="container col-4">
                <div class="card text-center">
                    <img src="https://rrnnii.upea.bo/carreras_logo/ingenieria_sistemas.png" class="card-img-top" alt="...">
                    <div class="card-header">
                        LOGIN
                    </div>
                    <div class="card-body">
                        <form action="LoginControlador" method="post">
                            <div class="form-floating mb-3">
                                <input type="email" name="correo" class="form-control" id="c" placeholder="">
                                <label for="c">Correo</label>
                            </div>
                            <div class="form-floating">
                                <input type="password" name="password" class="form-control" id="p" placeholder="" autocomplete="off">
                                <label for="p">Password</label>
                            </div>
                            <br>
                            <div class="form-check">
                                <label class="form-check-label" for="f">
                                    <input class="form-check-input" type="checkbox" value="remember-me" id="f"> Recuerdame
                                </label>
                            </div>
                            <br>
                            <button type="submit" class="btn btn-outline-success">Iniciar Sesion</button>
                            <a href="IndexControlador" class="btn btn-outline-primary"><i class="bi bi-arrow-return-left"> Volver</i></a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
