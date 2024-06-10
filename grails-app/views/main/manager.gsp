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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
        background-color: #FFF8F0;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
        text-align: center;
        width: 200px;
        text-decoration: none;
    }
    .card i {
        font-size: 50px;
        margin-bottom: 10px;
    }
    .card h3 {
        font-size: 18px;
        margin: 0;
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
        <g:if test="${flash.message}">
            <div class="message great-alert" role="status">${flash.message}</div>
        </g:if>
        <g:if test="${flash.error}">
            <ul class="errors" role="alert">
                <li>${flash.error}</li>
            </ul>
        </g:if>

        <a class="card" href="${createLink(uri: "/simulator")}">
            <i class="fa fa-users" style="color: #FF8811;"></i>
            <h3 style="color: #FF8811;">SIMULADOR</h3>
        </a>
        <a class="card" style="background-color: #392F5A" href="${createLink(uri: "/cliente")}">
            <i class="fa fa-cogs" style="color: #FFF;"></i>
            <h3 style="color: #FFF">CLIENTES</h3>
        </a>
        <a class="card" style="background-color: #FF8811"  href="${createLink(uri: "/account/opening")}">
            <i class="fa fa-user-plus" style="color: #FFF;"></i>
            <h3 style="color: #FFF;">APERTURA DE CUENTA</h3>
        </a>
    </div>
    <div class="statistics">
        <h2>ESTADÍSTICAS</h2>
        <div class="charts">
            <div class="chart">
                <h3>PERIODO ANTERIOR</h3>
                <!-- Gráfico del periodo anterior -->
                <canvas id="chart-previous"></canvas>
            </div>
            <div class="chart">
                <h3>PERIODO ACTUAL</h3>
                <!-- Gráfico del periodo actual -->
                <canvas id="chart-current"></canvas>
            </div>
        </div>
    </div>
</div>

<script>
    const chartPrevious = document.getElementById('chart-previous');
    const chartCurrent = document.getElementById('chart-current');

    const dataPrevious = {
        labels: ['Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        datasets: [{
            label: 'Cuentas abiertas',
            data: [500, 1000, 1500, 2000, 2500, 3000],
            backgroundColor: '#392F5A33',
            borderColor: '#392F5A',
            borderWidth: 1
        }]
    };

    const data = {
        labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio'],
        datasets: [{
            label: 'Cuentas abiertas',
            data: [1000, 2000, 3000, 4000, 5000, 6000],
            backgroundColor: '#FF881133',
            borderColor: '#FF8811',
            borderWidth: 1
        }]
    };

    const configPrevious = {
        type: 'line',
        data: dataPrevious,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    };

    const config = {
        type: 'line',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    };

    new Chart(chartPrevious, configPrevious);
    new Chart(chartCurrent, config);
</script>
</body>
</html>
