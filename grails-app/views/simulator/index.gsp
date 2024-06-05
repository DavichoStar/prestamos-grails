<%--
  Created by IntelliJ IDEA.
  User: davichostar
  Date: 30/05/24
  Time: 11:23 PM
--%>

<%@ page import="com.monaco.prestamos.catalogos.CatPlazo" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main"/>
    <title>Simulador - Sistema de Mónaco</title>

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
        .error-message {
            color: red;
            font-size: 12px;
        }
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 30px;
            font-size: 16px;
            cursor: pointer;
        }
        button.calculate {
            background-color: #FF8811;
            color: white;
            width: 100%;
        }
        button.delete {
            padding: 10px 0;
            background-color: red;
            color: white;
            margin-top: 5px;
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
    <h1>SIMULADOR DE PRÉSTAMO</h1>
    <div class="note">* Para buscar una cuenta guardada, comienza a escribir en nombre del cliente</div>

    <form class="row" id="simulador">
        <div class="col-12 col-sm-6 card">
            <div class="card-body">
                <div class="form-group">
                    <label for="name">Nombre</label>
                    <input type="text" id="name" name="name" placeholder="Ingrese el nombre del cliente" required>
                    <h5 id="nameCheck" style="color: red;"></h5>
                </div>

                <div class="row p-0">
                    <div class="form-group col-12 col-sm-6 pr-2 p-0">
                        <label for="lastName">Apellido Paterno</label>
                        <input type="text" id="lastName" name="lastName" placeholder="Ingrese el primer apellido" required>
                        <h5 id="lastNameCheck" style="color: red;"></h5>
                    </div>

                    <div class="form-group col-12 col-sm-6 pl-2 p-0">
                        <label for="secondLastName">Apellido Materno</label>
                        <input type="text" id="secondLastName" name="secondLastName" placeholder="Ingrese el segundo apellido" required>
                        <h5 id="secondLastNameCheck" style="color: red;"></h5>
                    </div>
                </div>

                <div class="row p-0">
                    <div class="form-group col-12 col-sm-6 pr-2 p-0">
                        <label for="monthlyIncome">Sueldo Mensual</label>
                        <input type="text" id="monthlyIncome" name="monthlyIncome" placeholder="Ingrese su ingreso mensual" required>
                        <h5 id="monthlyIncomeCheck" style="color: red;"></h5>
                    </div>

                    <div class="form-group col-12 col-sm-6 pl-2 p-0">
                        <label for="loanAmount">Cantidad del préstamo</label>
                        <input type="text" id="loanAmount" name="loanAmount" placeholder="Ingrese la cantidad del préstamo" required disabled>
                        <h5 id="loanAmountCheck" style="color: red;"></h5>
                    </div>
                </div>

                <div class="row p-0">
                    <div class="form-group col-12 col-sm-6 pr-2 p-0">
                        <label for="term">Plazo</label>
                        <g:select
                                id="term" name="term"
                                noSelection="${['null':'Seleccione el plazo']}"
                                from="${CatPlazo.list(activo: true, sort: 'meses').collect { [id: it.id, meses: it.meses + ' meses'] }}"
                                optionKey="id"
                                optionValue="meses"
                                required="required"
                        />
                        <h5 id="termCheck" style="color: red;"></h5>
                    </div>

                    <div class="col-12 col-sm-6">
                        <button class="button-main col" type="button" onclick="onClean()">LIMPIAR</button>
                        <button class="delete col" type="button">ELIMINAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 col-sm-6">
            <button class="calculate" type="button" id="calculate">CALCULAR</button>
            <div id="results" class="mt-3"></div>
        </div>
    </form>
</div>

<script>
    $(document).ready(function () {
        let errorName = true;
        $('#name').keyup(function () {
            checkName();
        });

        function checkName() {
            let nameValue = $('#name').val();
            if (nameValue.length === 0) {
                $('#nameCheck').html('**Campo requerido**');
                $('#nameCheck').show();
                errorName = true;
            } else {
                $('#nameCheck').hide();
                errorName = false;
            }
        }

        let errorLastName = true;
        $('#lastName').keyup(function () {
            checkLastName();
        });

        function checkLastName() {
            let lastNameValue = $('#lastName').val();
            if (lastNameValue.length === 0) {
                $('#lastNameCheck').html('**Campo requerido**');
                $('#lastNameCheck').show();
                errorLastName = true;
            } else {
                $('#lastNameCheck').hide();
                errorLastName = false;
            }
        }

        let errorSecondLastName = true;
        $('#secondLastName').keyup(function () {
            checkSecondLastName();
        });

        function checkSecondLastName() {
            let secondLastNameValue = $('#secondLastName').val();
            if (secondLastNameValue.length === 0) {
                $('#secondLastNameCheck').html('**Campo requerido**');
                $('#secondLastNameCheck').show();
                errorSecondLastName = true;
            } else {
                $('#secondLastNameCheck').hide();
                errorSecondLastName = false;
            }
        }

        let errorMonthlyIncome = true;
        $('#monthlyIncome').keyup(function () {
            checkMonthlyIncome();
        });

        function checkMonthlyIncome() {
            let monthlyIncomeValue = $('#monthlyIncome').val();
            let catLimites = JSON.parse('${raw(catLimiteListJson)}');
            catLimites = catLimites.sort((a, b) => a.montoMinimo - b.montoMinimo);

            if (monthlyIncomeValue.length === 0 || Number(monthlyIncomeValue) <= 0) {
                $('#monthlyIncomeCheck').html('**Campo requerido**');
                $('#monthlyIncomeCheck').show();
                errorMonthlyIncome = true;
            } else if (Number(monthlyIncomeValue) <= 0 || Number.isNaN(Number(monthlyIncomeValue))) {
                $('#monthlyIncomeCheck').html('**Debe ser un número válido**');
                $('#monthlyIncomeCheck').show();
                errorMonthlyIncome = true;
            } else if (monthlyIncomeValue < catLimites[0].montoMinimo) {
                $('#monthlyIncomeCheck').html('**Ingreso insuficiente para solicitar un préstamo**');
                $('#monthlyIncomeCheck').show();
                errorMonthlyIncome = true;
            } else {
                checkLoanAmount();
                $('#monthlyIncomeCheck').hide();
                errorMonthlyIncome = false;
                $('#loanAmount').prop('disabled', false);
            }
        }

        let errorLoanAmount = true;
        $('#loanAmount').keyup(function () {
            checkLoanAmount();
        });

        function checkLoanAmount() {
            let monthlyIncomeValue = $('#monthlyIncome').val();
            let loanAmountValue = $('#loanAmount').val();
            let catLimites = JSON.parse('${raw(catLimiteListJson)}');

            if (loanAmountValue.length === 0 || Number(loanAmountValue) <= 0) {
                $('#loanAmountCheck').html('**Campo requerido**');
                $('#loanAmountCheck').show();
                errorLoanAmount = true;
            } else if (Number.isNaN(Number(loanAmountValue))) {
                $('#loanAmountCheck').html('**Debe ser un número válido**');
                $('#loanAmountCheck').show();
                errorLoanAmount = true;
            } else if (Number(loanAmountValue) <= 1000) {
                $('#loanAmountCheck').html('**Debe ser mayor a $1,000.00**');
                $('#loanAmountCheck').show();
                errorLoanAmount = true;
            } else {
                // Buscar el límite que entre entre el monto mínimo y máximo
                let limit = catLimites.find(limite => limite.montoMinimo <= monthlyIncomeValue && limite.montoMaximo >= monthlyIncomeValue);

                if (!limit && loanAmountValue > 0) {
                    $('#loanAmountCheck').html('**Préstamo no permitido para el ingreso**');
                    $('#loanAmountCheck').show();
                    errorLoanAmount = true;
                } else if (loanAmountValue > limit.montoPrestamo) {
                    let formatter = new Intl.NumberFormat('es-MX', {
                        style: 'currency',
                        currency: 'MXN',
                    });

                    $('#loanAmountCheck').html('**Préstamo máximo de ' + formatter.format(limit.montoPrestamo) + '**');
                    $('#loanAmountCheck').show();
                    errorLoanAmount = true;
                } else {
                    $('#loanAmountCheck').hide();
                    errorLoanAmount = false;
                }
            }
        }

        $('#termCheck').hide();
        let errorTerm = true;
        $('#term').change(function () {
            checkTerm();
        });

        function checkTerm() {
            let termValue = $('#term').val();

            if (!termValue?.length || termValue === 'null') {
                $('#termCheck').html('**Campo requerido**');
                $('#termCheck').show();
                errorTerm = true;
            } else {
                $('#termCheck').hide();
                errorTerm = false;
            }
        }

        $('#calculate').click(function () {
            checkName();
            checkLastName();
            checkSecondLastName();
            checkMonthlyIncome();
            checkTerm();

            if (!errorName && !errorLastName && !errorSecondLastName && !errorMonthlyIncome && !errorLoanAmount && !errorTerm) {
                $.ajax({
                    url: 'simulator/calcular',
                    type: 'POST',
                    data: $('#simulador').serialize(),
                    success: function (response) {
                        $('#results').html(response);
                    }
                });
            }
        });
    });

    function onGoToAccount() {
        let formData = $('#simulador').serialize();
        window.location.href = 'simulator/goToAccountOpening?' + formData;
    }

    function onClean() {
        const confirm = window.confirm('¿Estás seguro de limpiar los campos?');

        if (confirm) {
            $('#name').val('');
            $('#lastName').val('');
            $('#secondLastName').val('');
            $('#monthlyIncome').val('');
            $('#loanAmount').val('');
            $('#term').val('null');
            $('#results').html('');
        }
    }
</script>
</body>
</html>