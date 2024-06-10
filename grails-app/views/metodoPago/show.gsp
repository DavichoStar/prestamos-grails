<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'metodoPago.label', default: 'MetodoPago')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-metodoPago" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-metodoPago" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <dl class="property-list">
                <g:if test="${this.metodoPago?.id}">
                    <dt><g:message code="metodoPago.id.label" default="Id" /></dt>
                    <dd><g:fieldValue bean="${this.metodoPago}" field="id" /></dd>
                </g:if>
                <dt><g:message code="metodoPago.numTarjeta.label" default="Número de tarjeta" /></dt>
                <dd>**** ${this.metodoPago.numeroTarjeta.substring(12)}</dd>
                <dt><g:message code="metodoPago.tipo.label" default="Tipo de tarjea" /></dt>
                <dd><g:fieldValue bean="${this.metodoPago}" field="tipoTarjeta" /></dd>
                <dt><g:message code="metodoPago.fechaVencimiento.label" default="Fecha de caducidad" /></dt>
                <dd><g:fieldValue bean="${this.metodoPago}" field="fechaCaducidad" /></dd>
                <dt><g:message code="metodoPago.cvv.label" default="CVV" /></dt>
                <dd>***</dd>
                <dt><g:message code="metodoPago.cliente.label" default="Cliente" /></dt>
                <dd>
                    <g:each in="${this.metodoPago.clientes}" status="j" var="clienteInstance">
                        <g:link controller="cliente" action="show" id="${clienteInstance.id}">${clienteInstance}</g:link>
                        <g:if test="${j != this.metodoPago.clientes.size() - 1}">, </g:if>
                    </g:each>
                </dd>
                <dt><g:message code="metodoPago.pagos.label" default="Pagos" /></dt>
                <dd>
                    <g:each in="${this.metodoPago.pagos}" status="j" var="pagoInstance">
                        <g:link controller="pago" action="show" id="${pagoInstance.id}">${pagoInstance}</g:link>
                        <g:if test="${j != this.metodoPago.pagos.size() - 1}">, </g:if>
                    </g:each>
                </dd>
            </dl>
            <g:form resource="${this.metodoPago}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.metodoPago}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
