<%--
  Created by IntelliJ IDEA.
  User: davichostar
  Date: 04/06/24
  Time: 11:07 PM
--%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main"/>
    <title>Resumen - Sistema de Mónaco</title>

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
    h1 {
        text-align: center;
        margin-bottom: 20px;
        font-size: 24px;
    }
    .note {
        text-align: center;
        margin-bottom: 20px;
        font-size: 14px;
        color: #555;
    }
    .form-group label {
        font-weight: bold;
        margin-bottom: 5px;
        display: block;
    }
    button {
        padding: 10px 20px;
        border: none;
        border-radius: 30px;
        font-size: 16px;
        cursor: pointer;
    }
    button:hover {
        opacity: 0.8;
    }
    button.print {
        background-color: #FF8811;
        color: white;
    }
    button.save {
        background-color: green;
        color: white;
    }
    button.button-main {
        background-color: #392F5A;
        color: white;
        width: 100%;
    }

    input, select {
        background-color: #FFEBD4;
        width: 100%;
        padding: 10px;
        margin: 5px 0 15px 0;
        border: 1px solid #ccc;
        border-radius: 20px;
        font-size: 16px;
    }

    input:disabled, select:disabled {
        opacity: 0.6;
    }
    </style>
</head>

<body>
<div class="container">
    <h1>RESUMEN DEL PRÉSTAMO</h1>

    <form class="row" id="openForm" method="post" action="${createLink(controller: 'account', action: 'openingSave', params: params)}">
        <div class="col-12 col-sm-6">
            <div class="card">
                <div class="card-body pt-0">
                    <h1 class="text-center font-weight-bold">Cliente</h1>

                    <div class="row p-0">
                        <div class="col-12 col-sm-6 text-sm-left">
                            <h3 class="font-weight-bold">Nombre:</h3>
                            ${params.name} ${params.lastName} ${params.secondLastName}
                        </div>

                        <div class="col-12 col-sm-6 text-sm-left">
                            <h3 class="font-weight-bold">Fecha de nacimiento:</h3>
                            ${params.birthDate}
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="col-12 col-sm-6 text-sm-left">
                            <h3 class="font-weight-bold">Teléfono:</h3>
                            ${params.phone}
                        </div>

                        <div class="col-12 col-sm-6 text-sm-left">
                            <h3 class="font-weight-bold">Clave de elector:</h3>
                            ${params.electorKey}
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="col-12 text-sm-left">
                            <h3 class="font-weight-bold">Dirección:</h3>
                            ${params.address}
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="col-12 col-sm-6 text-sm-left">
                            <h3 class="font-weight-bold">Estado Civil:</h3>
                            ${params.civilStatus}
                        </div>

                        <div class="col-12 col-sm-6 text-sm-left">
                            <h3 class="font-weight-bold">Ocupación:</h3>
                            ${params.occupation}
                        </div>
                    </div>
                </div>
            </div>

            <g:if test="${params.occupation == 'EMPLEADO'}">
                <div class="card mt-3">
                    <div class="card-body pt-0">
                        <h1 class="text-center font-weight-bold">Datos Laborales</h1>

                        <div class="row p-0">
                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Empresa:</h3>
                                ${params.companyName}
                            </div>

                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Puesto:</h3>
                                ${params.companyPosition}
                            </div>
                        </div>

                        <div class="row p-0">
                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Jefe inmediato:</h3>
                                ${params.companyBoss}
                            </div>

                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Teléfono:</h3>
                                ${params.companyPhone}
                            </div>
                        </div>

                        <div class="row p-0">
                            <div class="col-12 text-sm-left">
                                <h3 class="font-weight-bold">Dirección:</h3>
                                ${params.companyAddress}
                            </div>
                        </div>

                        <div class="row p-0">
                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Antigüedad:</h3>
                                ${params.companyYears} años
                            </div>

                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Ingreso mensual:</h3>
                                ${params.companyIncome}
                            </div>
                        </div>
                    </div>
                </div>
            </g:if>

            <g:if test="${params.civilStatus == 'CASADO'}">
                <div class="card mt-3">
                    <div class="card-body pt-0">
                        <h1 class="text-center font-weight-bold">Datos del Cónyuge</h1>

                        <div class="row p-0">
                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Nombre:</h3>
                                ${params.spouseName} ${params.spouseLastName} ${params.spouseSecondLastName}
                            </div>

                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Ocupación:</h3>
                                ${params.spouseOccupation}
                            </div>
                        </div>

                        <div class="row p-0">
                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Teléfono:</h3>
                                ${params.spousePhone}
                            </div>

                            <div class="col-12 col-sm-6 text-sm-left">
                                <h3 class="font-weight-bold">Dirección:</h3>
                                ${params.spouseAddress}
                            </div>
                        </div>
                    </div>
                </div>
            </g:if>
        </div>

        <div class="col-12 col-sm-6">
            <div class="card">
                <div class="card-body pt-0">
                    <h1 class="text-center font-weight-bold">Cantidad a Prestar</h1>

                    <div class="row">
                        <div class="col-12 text-center">
                            <h3>CANTIDAD A PRESTAR</h3>
                            <h4 style="font-weight: bold">$${loanAmount}</h4>
                        </div>

                        <div class="col-12 text-center mb-3">
                            <h3>PLAZO</h3>
                            <h4 style="font-weight: bold">${months} meses</h4>
                        </div>

                        <div class="col-12 text-center mb-3">
                            <h3>INTERESES</h3>
                            <h4 style="font-weight: bold">${interest}%</h4>
                        </div>

                        <div class="col-12 text-center mb-3">
                            <h3>CUOTA MENSUAL</h3>
                            <h4 style="font-weight: bold">$${monthlyPaymentWithInterest}</h4>
                        </div>

                        <div class="row align-items-center">
                            <div class="col-12 text-center mt-4">
                                <h3>DÍA DE CORTE</h3>
                                <h4 style="font-weight: bold">${dayCut} de cada mes</h4>
                            </div>

                            <div class="col-12 text-center mt-4">
                                <h3>DÍA LÍMITE DE PAGO</h3>
                                <h4 style="font-weight: bold">${dayLimit} de cada mes</h4>
                            </div>
                        </div>

                        <div class="col-12 text-center">
                            <h3>TOTAL A PAGAR</h3>
                            <h4 style="font-weight: bold">$${totalPayment}</h4>
                        </div>
                    </div>

                    <div class="row justify-content-center align-items-center mt-4">
                        <div class="col-12 col-sm-6">
                            <button class="print col" type="button" onclick="onPrint()">IMPRIMIR</button>
                        </div>

                        <div class="col-12 col-sm-6">
                            <button class="save col" type="button" onclick="onSave()">GUARDAR</button>
                        </div>
                    </div>

                    <button class="button-main mt-3" type="submit">APERTURAR</button>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    function onPrint() {
        // Ocultar botones y footer class
        $('button').hide();
        $('footer').hide();

        window.print();

        // Mostrar botones y footer
        $('button').show();
        $('footer').show();
    }

    function onSave() {
        alert('Guardado correctamente');
    }
</script>
</body>
</html>