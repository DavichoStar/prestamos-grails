<%--
  Created by IntelliJ IDEA.
  User: davichostar
  Date: 04/06/24
  Time: 11:07 PM
--%>

<%@ page import="com.monaco.prestamos.catalogos.CatPlazo" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main"/>
    <title>Apertura de Cuenta - Sistema de Mónaco</title>

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
    <h1>APERTURA DE CUENTA</h1>
    <div class="note">* Para buscar una cuenta guardada, comienza a escribir en nombre del cliente</div>

    <form class="row" id="accountOpen">
        <div class="col-12 col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h2>Datos Personales</h2>

                    <div class="form-group">
                        <label for="name">Nombre</label>
                        <input type="text" id="name" name="name" placeholder="Ingrese el nombre del cliente" required value="${params?.name}">
                        <h5 id="nameCheck" style="color: red;"></h5>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="lastName">Apellido Paterno</label>
                            <input type="text" id="lastName" name="lastName" placeholder="Ingrese el primer apellido" required value="${params?.lastName}">
                            <h5 id="lastNameCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="secondLastName">Apellido Materno</label>
                            <input type="text" id="secondLastName" name="secondLastName" placeholder="Ingrese el segundo apellido" required value="${params?.secondLastName}">
                            <h5 id="secondLastNameCheck" style="color: red;"></h5>
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 p-0">
                            <label for="lastName">Direccion</label>
                            <input type="text" id="address" name="address" placeholder="Ingrese la dirección" value="${params?.address}">
                            <h5 id="addressCheck" style="color: red;"></h5>
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="lastName">Número de teléfono</label>
                            <input type="text" id="phone" name="phone" placeholder="Ingrese el número de teléfono" required value="${params?.phone}">
                            <h5 id="phoneCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="secondLastName">Clave de elector</label>
                            <input type="text" id="electorKey" name="electorKey" placeholder="Ingrese la clave de elector" required value="${params?.electorKey}">
                            <h5 id="electorKeyCheck" style="color: red;"></h5>
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="civilStatus">Estado Civil</label>
                            <select id="civilStatus" name="civilStatus" required>
                                <option value="null">Selecciona una opción</option>
                                <option value="SOLTERO">Soltero</option>
                                <option value="CASADO">Casado</option>
                                <option value="DIVORCIADO">Divorciado</option>
                                <option value="VIUDO">Viudo</option>
                            </select>
                            <h5 id="civilStatusCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label from="occupation">Ocupación</label>
                            <select id="occupation" name="occupation" required>
                                <option value="null">Selecciona una opción</option>
                                <option value="EMPLEADO">Empleado</option>
                                <option value="ESTUDIANTE">Estudiante</option>
                                <option value="INDEPENDIENTE">Independiente</option>
                                <option value="OTRO">Otro</option>
                            </select>
                            <h5 id="occupationCheck" style="color: red;"></h5>
                        </div>
                    </div>

                    <div class="row p-0 align-items-center">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="birthDate">Fecha de nacimiento</label>
                            <input type="date" id="birthDate" name="birthDate" placeholder="Ingrese la fecha de nacimiento" required value="${params?.birthDate}">
                            <h5 id="birthDateCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <h3 id="birthDateCalculate">Edad: 0 años</h3>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mt-3" id="civilForm" style="display: none">
                <div class="card-body">
                    <h2>Datos del cónyuge</h2>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="spouseName">Nombre</label>
                            <input type="text" id="spouseName" name="spouseName" placeholder="Ingrese el nombre del cónyuge" required value="${params?.spouseName}">
                            <h5 id="spouseNameCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="spouseLastName">Apellido Paterno</label>
                            <input type="text" id="spouseLastName" name="spouseLastName" placeholder="Ingrese el primer apellido" required value="${params?.spouseLastName}">
                            <h5 id="spouseLastNameCheck" style="color: red;"></h5>
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="spouseSecondLastName">Apellido Materno</label>
                            <input type="text" id="spouseSecondLastName" name="spouseSecondLastName" placeholder="Ingrese el segundo apellido" value="${params?.spouseSecondLastName}">
                            <h5 id="spouseSecondLastNameCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="spousePhone">Número de teléfono</label>
                            <input type="text" id="spousePhone" name="spousePhone" placeholder="Ingrese el número de teléfono" required value="${params?.spousePhone}">
                            <h5 id="spousePhoneCheck" style="color: red;"></h5>
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 p-0">
                            <label for="spouseAddress">Dirección</label>
                            <input type="text" id="spouseAddress" name="spouseAddress" placeholder="Ingrese la dirección" value="${params?.spouseAddress}">
                            <h5 id="spouseAddressCheck" style="color: red;"></h5>
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="spouseGender">Género</label>
                            <select id="spouseGender" name="spouseGender" required>
                                <option value="null">Selecciona una opción</option>
                                <option value="MASCULINO">Masculino</option>
                                <option value="FEMENINO">Femenino</option>
                                <option value="OTRO">Otro</option>
                            </select>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="spouseOccupation">Ocupación</label>
                            <select id="spouseOccupation" name="spouseOccupation" required>
                                <option value="null">Selecciona una opción</option>
                                <option value="EMPLEADO">Empleado</option>
                                <option value="ESTUDIANTE">Estudiante</option>
                                <option value="INDEPENDIENTE">Independiente</option>
                                <option value="HOGAR">Hogar</option>
                                <option value="OTRO">Otro</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-12 col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h2 id="companyTitle">Ingresos</h2>

                    <div id="companyForm" style="display: none">
                        <div class="row p-0">
                            <div class="form-group col-12 col-sm-6 pr-2 p-0">
                                <label for="companyName">Nombre de la empresa</label>
                                <input type="text" id="companyName" name="companyName" placeholder="Ingrese el nombre de la empresa" value="${params?.companyName}">
                                <h5 id="companyNameCheck" style="color: red;"></h5>
                            </div>

                            <div class="form-group col-12 col-sm-6 pl-2 p-0">
                                <label for="companyPosition">Puesto en la empresa</label>
                                <input type="text" id="companyPosition" name="companyPosition" placeholder="Ingrese el puesto en la empresa" value="${params?.companyPosition}">
                                <h5 id="companyPositionCheck" style="color: red;"></h5>
                            </div>
                        </div>

                        <div class="row p-0">
                            <div class="form-group col-12 col-sm-6 pr-2 p-0">
                                <label for="companyBoss">Nombre del jefe</label>
                                <input type="text" id="companyBoss" name="companyBoss" placeholder="Ingrese el nombre del jefe" value="${params?.companyBoss}">
                                <h5 id="companyBossCheck" style="color: red;"></h5>
                            </div>

                            <div class="form-group col-12 col-sm-6 pl-2 p-0">
                                <label for="companyPhone">Teléfono de la empresa</label>
                                <input type="text" id="companyPhone" name="companyPhone" placeholder="Ingrese el teléfono de la empresa" value="${params?.companyPhone}">
                                <h5 id="companyPhoneCheck" style="color: red;"></h5>
                            </div>
                        </div>

                        <div class="row p-0">
                            <div class="form-group col-12 pr-2 p-0">
                                <label for="companyAddress">Dirección de la empresa</label>
                                <input type="text" id="companyAddress" name="companyAddress" placeholder="Ingrese la dirección de la empresa" value="${params?.companyAddress}">
                                <h5 id="companyAddressCheck" style="color: red;"></h5>
                            </div>
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0" id="companyYearsForm" style="display: none">
                            <label for="companyYears">Años en la empresa</label>
                            <input type="text" id="companyYears" name="companyYears" placeholder="Ingrese los años en la empresa" value="${params?.companyYears}">
                            <h5 id="companyYearsCheck" style="color: red;"></h5>
                        </div>

                            <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="companyIncome">Sueldo Mensual</label>
                            <input type="text" id="companyIncome" name="companyIncome" placeholder="Ingrese su sueldo mensual" required value="${params?.monthlyIncome}">
                            <h5 id="companyIncomeCheck" style="color: red;"></h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-body">
                    <h2>Datos de la tarjeta</h2>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="cardNumber">Número de tarjeta</label>
                            <input type="text" id="cardNumber" name="cardNumber" placeholder="Ingrese el número de tarjeta" value="${params?.cardNumber}">
                            <h5 id="cardNumberCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="cardType">Tipo de tarjeta</label>
                            <select id="cardType" name="cardType">
                                <option value="null">Selecciona una opción</option>
                                <option value="CREDITO">Crédito</option>
                                <option value="DEBITO">Débito</option>
                            </select>
                        </div>
                    </div>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="cardExpiration">Fecha de expiración</label>
                            <input type="date" id="cardExpiration" name="cardExpiration" placeholder="Ingrese la fecha de expiración" value="${params?.cardExpiration}">
                            <h5 id="cardExpirationCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="cardCvv">CVV</label>
                            <input type="text" id="cardCvv" minlength="3" maxlength="3" name="cardCvv" placeholder="Ingrese el CVV" value="${params?.cardCvv}">
                            <h5 id="cardCvvCheck" style="color: red;"></h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-body">
                    <h2>Préstamo Solicitado</h2>

                    <div class="row p-0">
                        <div class="form-group col-12 col-sm-6 pr-2 p-0">
                            <label for="loanAmount">Cantidad del préstamo</label>
                            <input type="text" id="loanAmount" name="loanAmount" placeholder="Ingrese la cantidad del préstamo" required value="${params?.loanAmount}">
                            <h5 id="loanAmountCheck" style="color: red;"></h5>
                        </div>

                        <div class="form-group col-12 col-sm-6 pl-2 p-0">
                            <label for="term">Plazo</label>
                            <g:select
                                    id="term" name="term"
                                    noSelection="${['null':'Seleccione el plazo']}"
                                    from="${CatPlazo.list(activo: true, sort: 'meses').collect { [id: it.id, meses: it.meses + ' meses'] }}"
                                    optionKey="id"
                                    optionValue="meses"
                                    value="${params?.term}"
                                    required="required"
                            />
                            <h5 id="termCheck" style="color: red;"></h5>
                        </div>
                    </div>

                    <div class="row p-0">
                        <button class="button-main col" type="button" onclick="onClean()">LIMPIAR</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    $(document).ready(function () {
        let today = new Date();
        let eighteenYearsAgo = new Date();
        eighteenYearsAgo.setFullYear(today.getFullYear() - 18);

        // Formatear la fecha a YYYY-MM-DD para el input de tipo 'date'
        let year = eighteenYearsAgo.getFullYear();
        let month = eighteenYearsAgo.getMonth() + 1; // Los meses en JavaScript empiezan en 0
        let day = eighteenYearsAgo.getDate();

        // Asegurarse de que el mes y el día sean de dos dígitos
        if (month < 10) month = '0' + month;
        if (day < 10) day = '0' + day;

        let maxDate = year + '-' + month + '-' + day;
        let minDate = (year - 82) + '-' + month + '-' + day;

        $('#birthDate').attr('max', maxDate).attr('min', minDate);


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

        let errorCompanyIncome = true;
        $('#companyIncome').keyup(function () {
            checkCompanyIncome();
            checkLoanAmount();
        });

        function checkCompanyIncome() {
            let companyIncomeValue = $('#companyIncome').val();
            let catLimites = JSON.parse('${raw(catLimiteListJson)}');
            catLimites = catLimites.sort((a, b) => a.montoMinimo - b.montoMinimo);

            if (companyIncomeValue.length === 0 || Number(companyIncomeValue) <= 0) {
                $('#companyIncomeCheck').html('**Campo requerido**');
                $('#companyIncomeCheck').show();
                errorCompanyIncome = true;
            } else if (Number(companyIncomeValue) <= 0 || Number.isNaN(Number(companyIncomeValue))) {
                $('#companyIncomeCheck').html('**Debe ser un número válido**');
                $('#companyIncomeCheck').show();
                errorCompanyIncome = true;
            } else if (companyIncomeValue < catLimites[0].montoMinimo) {
                $('#companyIncomeCheck').html('**Ingreso insuficiente para solicitar un préstamo**');
                $('#companyIncomeCheck').show();
                errorCompanyIncome = true;
            } else {
                $('#companyIncomeCheck').hide();
                errorCompanyIncome = false;
            }
        }

        let errorLoanAmount = true;
        $('#loanAmount').keyup(function () {
            checkLoanAmount();
        });

        function checkLoanAmount() {
            let companyIncomeValue = $('#companyIncome').val();
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
                let limit = catLimites.find(limite => limite.montoMinimo <= companyIncomeValue && limite.montoMaximo >= companyIncomeValue);

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

        $('#birthDate').change(function () {
            let birthDate = new Date($('#birthDate').val());
            let today = new Date();
            let age = today.getFullYear() - birthDate.getFullYear();
            let m = today.getMonth() - birthDate.getMonth();

            if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                age--;
            }

            $('#birthDateCalculate').html('Edad: ' + age + ' años');
        });

        $('#civilStatus').change(function () {
            let civilStatus = $('#civilStatus').val();
            let civilForm = $('#civilForm');

            if (civilStatus === 'CASADO') {
                civilForm.show();
            } else {
                civilForm.hide();
            }
        });

        $('#occupation').change(function () {
            let ocupation = $('#occupation').val();
            let companyForm = $('#companyForm');
            let companyTitle = $('#companyTitle');
            let companyYearsForm = $('#companyYearsForm');

            if (ocupation === 'EMPLEADO') {
                companyTitle.html('Datos Laborales');
                companyForm.show();
                companyYearsForm.show();
            } else {
                companyTitle.html('Ingresos');
                companyForm.hide();
                companyYearsForm.hide();
            }
        });

        //=====[ Botón - Siguiente ]=====
        $('#calculate').click(function () {
            checkName();
            checkLastName();
            checkSecondLastName();
            checkCompanyIncome();
            checkLoanAmount();
            checkTerm();

            if (!errorName && !errorLastName && !errorSecondLastName && !errorCompanyIncome && !errorLoanAmount && !errorTerm) {
                $.ajax({
                    url: 'simulator/calcular',
                    type: 'POST',
                    data: $('#accountOpen').serialize(),
                    success: function (response) {
                        console.log(response);
                        $('#results').html(response);
                    }
                });
            }
        });
    });

    function onClean() {
        const confirm = window.confirm('¿Estás seguro de limpiar los campos?');

        if (confirm) {
            $('#name').val('');
            $('#lastName').val('');
            $('#secondLastName').val('');
            $('#companyIncome').val('');
            $('#loanAmount').val('');
            $('#term').val('null');
            $('#address').val('');
            $('#phone').val('');
            $('#electorKey').val('');
            $('#civilStatus').val('null');
            $('#occupation').val('null');
            $('#birthDate').val('');
            $('#birthDateCalculate').html('Edad: 0 años');
            $('#companyName').val('');
            $('#companyPosition').val('');
            $('#companyBoss').val('');
            $('#companyPhone').val('');
            $('#companyAddress').val('');
            $('#companyYears').val('');
            $('#cardNumber').val('');
            $('#cardType').val('null');
            $('#cardExpiration').val('');
            $('#cardCvv').val('');
            $('#spouseName').val('');
            $('#spouseLastName').val('');
            $('#spouseSecondLastName').val('');
            $('#spousePhone').val('');
            $('#spouseAddress').val('');
            $('#spouseGender').val('null');
            $('#spouseOccupation').val('null');
        }
    }
</script>
</body>
</html>