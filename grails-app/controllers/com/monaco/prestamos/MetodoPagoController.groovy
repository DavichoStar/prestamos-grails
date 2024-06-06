package com.monaco.prestamos

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class MetodoPagoController {

    MetodoPagoService metodoPagoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond metodoPagoService.list(params), model:[metodoPagoCount: metodoPagoService.count()]
    }

    def show(Long id) {
        respond metodoPagoService.get(id)
    }

    def create() {
        respond new MetodoPago(params)
    }

    def save(MetodoPago metodoPago) {
        if (metodoPago == null) {
            notFound()
            return
        }

        try {
            metodoPagoService.save(metodoPago)
        } catch (ValidationException e) {
            respond metodoPago.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'metodoPago.label', default: 'MetodoPago'), metodoPago.id])
                redirect metodoPago
            }
            '*' { respond metodoPago, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond metodoPagoService.get(id)
    }

    def update(MetodoPago metodoPago) {
        if (metodoPago == null) {
            notFound()
            return
        }

        try {
            metodoPagoService.save(metodoPago)
        } catch (ValidationException e) {
            respond metodoPago.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'metodoPago.label', default: 'MetodoPago'), metodoPago.id])
                redirect metodoPago
            }
            '*'{ respond metodoPago, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        metodoPagoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'metodoPago.label', default: 'MetodoPago'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'metodoPago.label', default: 'MetodoPago'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
