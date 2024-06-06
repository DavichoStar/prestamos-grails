package com.monaco.prestamos

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import com.monaco.prestamos.catalogos.CatLimiteCredito
import com.monaco.prestamos.catalogos.CatPlazo
import com.monaco.prestamos.Cliente
import com.monaco.prestamos.Conyuge
import com.monaco.prestamos.DatosLaborales
import com.monaco.prestamos.MetodoPago
import com.monaco.prestamos.Prestamo

import java.text.NumberFormat

@Secured(['ROLE_ADMIN', 'ROLE_MANAGER'])
class AccountController {

    def index() { }

    def opening() {
        def catLimiteList = CatLimiteCredito.findAll( activo: true )
        def catLimiteListJson = catLimiteList as JSON

        [catLimiteListJson: catLimiteListJson.toString()]
    }

    def resumeRedirect() {
        // Revisar si companyYears contiene la palabra "meses" y transformarla a años
        if (params.companyYears.contains("meses")) {
            params.companyYears = params.companyYears.replace("meses", "")
            params.companyYears = params.companyYears.toInteger() / 12
        }

        redirect action: "resume", params: params
    }

    def resume() {
        def loanAmount = params.loanAmount as Double // Cantidad a prestar
        def term = CatPlazo.get( params.term as Long ) // Plazo

        def interest = term.intereses
        def totalPayment = loanAmount + (loanAmount * (interest / 100))
        def monthlyPaymentWithInterest = totalPayment / term.meses

        // Calcular el día de corte, buscar el día, 4, 7, 18 o 28 más cercano al día actual
        def dayCut = new Date().date
        if (dayCut < 4) {
            dayCut = 4
        } else if (dayCut < 7) {
            dayCut = 7
        } else if (dayCut < 18) {
            dayCut = 18
        } else if (dayCut < 28) {
            dayCut = 28
        }
        def dayLimit = dayCut + 5

        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US)
        formatter.setMinimumFractionDigits(2)

        [params: params, months: term.meses, loanAmount: formatter.format(loanAmount), interest: formatter.format(interest),
         totalPayment: formatter.format(totalPayment), monthlyPaymentWithInterest: formatter.format(monthlyPaymentWithInterest),
         dayCut: dayCut, dayLimit: dayLimit]
    }

    def openingSave() {
        try {
            def conyuge = null;

            if (params.spouseName) {
                conyuge = new Conyuge(
                        nombre: params.spouseName,
                        apellidoPaterno: params.spouseLastName,
                        apellidoMaterno: params.spouseSecondLastName,
                        telefono: params.spousePhone,
                        direccion: params.spouseAddress,
                        genero: params.spouseGender,
                        ocupacion: params.spouseOccupation
                )
                conyuge.save()
            }

            def datosLaborales = new DatosLaborales(
                    empresa: params.companyName,
                    puesto: params.companyPosition,
                    jefeInmediato: params.companyBoss,
                    telefono: params.companyPhone,
                    direccion: params.companyAddress,
                    antiguedad: params.companyYears,
                    ingresoMensual: params.companyIncome
            )
            datosLaborales.save()

            def metodoPago = new MetodoPago(
                    numeroTarjeta: params.cardNumber,
                    tipoTarjeta: params.cardType,
                    nombreTarjeta: params.cardName,
                    fechaExpiracion: params.cardExpiration,
                    cvv: params.cardCvv
            )
            metodoPago.save()

            def term = CatPlazo.get(params.term as Long) // Plazo
            // Calcular el día de corte, buscar el día, 4, 7, 18 o 28 más cercano al día actual
            def dayCut = new Date().date
            if (dayCut < 4) {
                dayCut = 4
            } else if (dayCut < 7) {
                dayCut = 7
            } else if (dayCut < 18) {
                dayCut = 18
            } else if (dayCut < 28) {
                dayCut = 28
            }
            def prestamo = new Prestamo(
                    cantidadPrestamo: params.loanAmount,
                    plazoMeses: term.meses,
                    intereses: term.intereses,
                    diaDePago: dayCut,
                    estatus: "PENDIENTE",
            )
            prestamo.save()

            def account = new Cliente(
                    nombre: params.name,
                    apellidoPaterno: params.lastName,
                    apellidoMaterno: params.secondLastName,
                    direccion: params.address,
                    telefono: params.phone,
                    claveElector: params.electorKey,
                    estadoCivil: params.civilStatus,
                    ocupacion: params.occupation,
                    fechaNacimiento: params.birthDate,
                    conyuge: conyuge,
                    datosLaborales: datosLaborales,
                    metodoPago: metodoPago,
                    prestamo: prestamo,
            )
            account.save()

            flash.message = "Cuenta creada exitosamente"
            redirect controller: 'main', action: "index"
        } catch (Exception e) {
            flash.error = "Error al crear la cuenta"
            redirect action: "resume", params: params
        }
    }
}
