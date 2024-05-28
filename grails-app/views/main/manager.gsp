<%--
  Created by IntelliJ IDEA.
  User: davichostar
  Date: 27/05/24
  Time: 8:54 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Sistema de Mónaco</title>
    <style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
    }
    .container {
        padding: 20px;
    }
    .dashboard {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
    }
    .card {
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
        text-align: center;
        width: 200px;
    }
    .card i {
        font-size: 50px;
        margin-bottom: 10px;
    }
    .card h3 {
        font-size: 18px;
        margin: 0;
        color: #555;
    }
    .statistics {
        margin-top: 40px;
    }
    .statistics h2 {
        text-align: center;
        color: #333;
    }
    .charts {
        display: flex;
        justify-content: center;
        gap: 40px;
        margin-top: 20px;
    }
    .chart {
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
        width: 300px;
        height: 200px;
    }
    </style>
</head>
<body>
<div class="container">
    <div class="dashboard">
        <div class="card">
            <i class="fa fa-users" style="color: #f39c12;"></i>
            <h3>SIMULADOR</h3>
        </div>
        <div class="card">
            <i class="fa fa-money" style="color: #2c3e50;"></i>
            <h3>ABONO</h3>
        </div>
        <div class="card">
            <i class="fa fa-cogs" style="color: #2c3e50;"></i>
            <h3>CLIENTES</h3>
        </div>
        <div class="card">
            <i class="fa fa-user-plus" style="color: #e67e22;"></i>
            <h3>APERTURA DE CUENTA</h3>
        </div>
    </div>
    <div class="statistics">
        <h2>ESTADÍSTICAS</h2>
        <div class="charts">
            <div class="chart">
                <h3>PERIODO ANTERIOR</h3>
                <!-- Gráfico del periodo anterior -->
            </div>
            <div class="chart">
                <h3>PERIODO ACTUAL</h3>
                <!-- Gráfico del periodo actual -->
            </div>
        </div>
    </div>
</div>
</body>
</html>
