<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'metodoPago.label', default: 'MetodoPago')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-metodoPago" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-metodoPago" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table class="table">
                <thead>
                    <tr>
                        <g:sortableColumn property="id" title="${message(code: 'metodoPago.id.label', default: 'Id')}" />
                        <g:sortableColumn property="numeroTarjeta" title="${message(code: 'metodoPago.numTarjeta.label', default: 'Número de tarjeta')}" />
                        <g:sortableColumn property="fechaCaducidad" title="${message(code: 'metodoPago.date.label', default: 'Fecha de caducidad')}" />
                        <g:sortableColumn property="tipoTarjeta" title="${message(code: 'metodoPago.tipo.label', default: 'Tipo de tarjeta')}" />
                        <g:sortableColumn property="clientes" title="${message(code: 'metodoPago.cliente.label', default: 'Cliente')}" />
                        <g:sortableColumn property="pagos" title="${message(code: 'metodoPago.pagos.label', default: 'Pagos')}" />

                    </tr>
                </thead>
                <tbody>
                <g:each in="${metodoPagoList}" status="i" var="metodoPagoInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                        <td><g:link action="show" id="${metodoPagoInstance.id}">${fieldValue(bean: metodoPagoInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${metodoPagoInstance.id}">**** ${metodoPagoInstance.numeroTarjeta.substring(12)}</g:link></td>
                        <td>${fieldValue(bean: metodoPagoInstance, field: "fechaCaducidad")}</td>
                        <td>${fieldValue(bean: metodoPagoInstance, field: "tipoTarjeta")}</td>
                        <td>
                            <g:each in="${metodoPagoInstance.clientes}" status="j" var="clienteInstance">
                                <g:link controller="cliente" action="show" id="${clienteInstance.id}">${clienteInstance}</g:link>
                                <g:if test="${j != metodoPagoInstance.clientes.size() - 1}">, </g:if>
                            </g:each>
                        </td>
                        <td>
                            <g:each in="${metodoPagoInstance.pagos}" status="j" var="pagoInstance">
                                <g:link controller="pago" action="show" id="${pagoInstance.id}">${pagoInstance}</g:link>
                                <g:if test="${j != metodoPagoInstance.pagos.size() - 1}">, </g:if>
                            </g:each>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${metodoPagoCount ?: 0}" />
            </div>
        </div>
    </body>
</html>