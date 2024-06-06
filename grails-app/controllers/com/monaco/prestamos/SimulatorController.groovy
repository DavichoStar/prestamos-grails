package com.monaco.prestamos

import com.monaco.prestamos.catalogos.CatLimiteCredito
import com.monaco.prestamos.catalogos.CatPlazo
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import java.text.NumberFormat

@Secured(['ROLE_ADMIN', 'ROLE_MANAGER'])
class SimulatorController {

    def index() {
        def catLimiteList = CatLimiteCredito.findAll( activo: true )
        def catLimiteListJson = catLimiteList as JSON

        [catLimiteListJson: catLimiteListJson.toString()]
    }

    def calcular() {
        // Nombre del cliente
        def name = params.name as String
        def lastName = params.lastName as String
        def secondLastName = params.secondLastName as String

        def monthlyIncome = params.monthlyIncome as Double // Ingreso mensual
        def loanAmount = params.loanAmount as Double // Cantidad a prestar
        def term = CatPlazo.get( params.term as Long ) // Plazo

        def interest = term.intereses
        def totalPayment = loanAmount + (loanAmount * (interest / 100))
        def monthlyPaymentWithInterest = totalPayment / term.meses

        // Formateador de números
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US)
        formatter.setMinimumFractionDigits(2)

        def resultHtml = """
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>CANTIDAD A PRESTAR</h3>
                        <h4 style="font-weight: bold">\$${formatter.format( loanAmount )}</h4>
                    </div>
    
                    <div class="col-12 text-center mb-3">
                        <h3>PLAZO</h3>
                        <h4 style="font-weight: bold">${term.meses} meses</h4>
                    </div>
    
                    <div class="col-12 text-center mb-3">
                        <h3>INTERESES</h3>
                        <h4 style="font-weight: bold">${interest}%</h4>
                    </div>
    
                    <div class="col-12 text-center mb-3">
                        <h3>CUOTA MENSUAL</h3>
                        <h4 style="font-weight: bold">\$${formatter.format( monthlyPaymentWithInterest )}</h4>
                    </div>
    
                    <div class="col-12 text-center">
                        <h3>MONTO TOTAL A PAGAR</h3>
                        <h4 style="font-weight: bold">\$${formatter.format( totalPayment )}</h4>
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
            </div>
        </div>
        
        <button class="button-main mt-4" type="button" id="calculate" onclick="onGoToAccount()">
            INICIAR APERTURA DE CUENTA
        </button>
        """

        render resultHtml
    }

    def goToAccountOpening() {
        redirect(controller: 'account', action: 'opening', params: params)
    }
}
