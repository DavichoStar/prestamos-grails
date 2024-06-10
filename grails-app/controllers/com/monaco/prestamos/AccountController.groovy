package com.monaco.prestamos

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import com.monaco.prestamos.catalogos.CatLimiteCredito
import com.monaco.prestamos.catalogos.CatPlazo

import java.text.NumberFormat

@Secured(['ROLE_ADMIN', 'ROLE_MANAGER'])
class AccountController {

    def springSecurityService
    ConyugeService conyugeService
    MetodoPagoService metodoPagoService
    DatosLaboralesService datosLaboralesService
    ClienteService clienteService
    PrestamoService prestamoService

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

    @Transactional
    def openingSave() {
        try {
            Conyuge conyuge = null;
            MetodoPago metodoPago = null;
            DatosLaborales datosLaborales = null;
            User user = springSecurityService.currentUser

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
                try {
                    conyuge = conyugeService.save(conyuge)
                } catch (ValidationException e) {
                    System.out.println(e)
                    flash.error = "Error al crear la cuenta. Algún dato del cónyuge es incorrecto"
                    redirect action: "resume", params: params
                    return
                }
            }

            if (params.companyIncome) {
                datosLaborales = new DatosLaborales(
                        nombre: params.companyName,
                        puesto: params.companyPosition,
                        nombreJefeInmediato: params.companyBoss,
                        telefono: params.companyPhone,
                        direccion: params.companyAddress,
                        antiguedad: params.companyYears,
                        sueldo: params.companyIncome
                )
                try {
                    datosLaborales = datosLaboralesService.save(datosLaborales)
                } catch (ValidationException e) {
                    System.out.println(e)
                    if (conyuge && conyuge.id) conyugeService.delete(conyuge.id)
                    flash.error = "Error al crear la cuenta. Algún dato laboral es incorrecto"
                    redirect action: "resume", params: params
                    return
                }
            }

            if (params.cardNumber) {
                metodoPago = new MetodoPago(
                        numeroTarjeta: params.cardNumber,
                        tipoTarjeta: params.cardType,
                        nombreTarjeta: params.cardName,
                        fechaCaducidad: params.cardExpiration,
                        cvv: params.cardCvv
                )
                try {
                    metodoPago = metodoPagoService.save(metodoPago)
                } catch (ValidationException e) {
                    System.out.println(e)
                    if (conyuge && conyuge.id) conyugeService.delete(conyuge.id)
                    if (datosLaborales && datosLaborales.id) datosLaboralesService.delete(datosLaborales.id)
                    flash.error = "Error al crear la cuenta. Algún dato de la tarjeta es incorrecto"
                    redirect action: "resume", params: params
                    return
                }
            }

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

            Cliente cliente = new Cliente(
                    nombre: params.name,
                    apellidoPaterno: params.lastName,
                    apellidoMaterno: params.secondLastName,
                    direccion: params.address,
                    telefono: params.phone,
                    claveElector: params.electorKey,
                    estadoCivil: params.civilStatus,
                    ocupacion: params.occupation,
                    fechaNacimiento: params.birthDate,
                    userCreated: user,
            )
            if (conyuge) cliente.conyuge = conyuge
            if (datosLaborales) cliente.datosLaborales = datosLaborales
            if (metodoPago) cliente.metodoPago = metodoPago

            try {
                cliente = clienteService.save(cliente)
            } catch (ValidationException e) {
                System.out.println(e)
                if (conyuge && conyuge.id) conyugeService.delete(conyuge.id)
                if (datosLaborales && datosLaborales.id) datosLaboralesService.delete(datosLaborales.id)
                if (metodoPago && metodoPago.id) metodoPagoService.delete(metodoPago.id)
                flash.error = "Error al crear la cuenta. Algún dato del cliente es incorrecto"
                redirect action: "resume", params: params
                return
            }

            Prestamo prestamo = new Prestamo(
                    cantidadPrestamo: params.loanAmount,
                    plazo: term,
                    diaDePago: dayCut,
                    estatus: "PENDIENTE",
                    cliente: cliente,
                    userCreated: cliente.userCreated,
            )
            try {
                prestamo = prestamoService.save(prestamo)
            } catch (ValidationException e) {
                System.out.println(e)
                if (conyuge && conyuge.id) conyugeService.delete(conyuge.id)
                if (datosLaborales && datosLaborales.id) datosLaboralesService.delete(datosLaborales.id)
                if (metodoPago && metodoPago.id) metodoPagoService.delete(metodoPago.id)
                flash.error = "Error al crear la cuenta. Algún dato del préstamo es incorrecto"
                redirect action: "resume", params: params
                return
            }

            flash.message = "Cuenta creada exitosamente"
            redirect controller: 'main', action: "index"
        } catch (Exception e) {
            System.out.println(e)
            flash.error = "Error al crear la cuenta. Intente de nuevo"
            redirect action: "resume", params: params
        }
    }
}
