<%--
  Created by IntelliJ IDEA.
  User: davichostar
  Date: 27/05/24
  Time: 8:54 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Panel de Administración</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
    }
    .admin-panel {
      margin-top: 50px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      padding: 20px;
      border-radius: 10px;
      background-color: #fff;
    }
    .panel-heading {
      background-color: #343a40;
      color: white;
      padding: 15px;
      border-top-left-radius: 10px;
      border-top-right-radius: 10px;
    }
    .panel-title {
      font-size: 24px;
      margin: 0;
    }
    .list-group-item {
      font-size: 18px;
      padding: 15px 20px;
      border: none;
    }
    .list-group-item a {
      text-decoration: none;
      color: #343a40;
    }
    .list-group-item a:hover {
      color: #007bff;
    }
    .list-group-item i {
      margin-right: 10px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-md-8">
      <div class="admin-panel">
        <div class="panel-heading text-center">
          <h3 class="panel-title">Panel de Administración</h3>
        </div>
        <div class="panel-body">
          <ul class="list-group">
            <li class="list-group-item">
              <a href="${createLink(uri: "/catPlazo")}"><i class="fa fa-calendar"></i> Administrar Plazos</a>
            </li>
            <li class="list-group-item">
              <a href="${createLink(uri: "/catLimiteCredito")}"><i class="fa fa-credit-card"></i> Administrar Límites de Crédito</a>
            </li>
            <li class="list-group-item">
              <a href="${createLink(uri: "/user")}"><i class="fa fa-user"></i> Administrar Usuarios</a>
            </li>
            <li class="list-group-item">
              <a href="${createLink(uri: "/prestamo")}"><i class="fa fa-money"></i> Administrar Préstamos</a>
            </li>
            <li class="list-group-item">
              <a href="${createLink(uri: "/cliente")}"><i class="fa fa-users"></i> Administrar Clientes</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
</body>
</html>
