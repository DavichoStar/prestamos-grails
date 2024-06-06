package com.monaco.prestamos

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_MANAGER'])
class DatosLaboralesController {

    DatosLaboralesService datosLaboralesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond datosLaboralesService.list(params), model:[datosLaboralesCount: datosLaboralesService.count()]
    }

    def show(Long id) {
        respond datosLaboralesService.get(id)
    }

    def create() {
        respond new DatosLaborales(params)
    }

    def save(DatosLaborales datosLaborales) {
        if (datosLaborales == null) {
            notFound()
            return
        }

        try {
            datosLaboralesService.save(datosLaborales)
        } catch (ValidationException e) {
            respond datosLaborales.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'datosLaborales.label', default: 'DatosLaborales'), datosLaborales.id])
                redirect datosLaborales
            }
            '*' { respond datosLaborales, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond datosLaboralesService.get(id)
    }

    def update(DatosLaborales datosLaborales) {
        if (datosLaborales == null) {
            notFound()
            return
        }

        try {
            datosLaboralesService.save(datosLaborales)
        } catch (ValidationException e) {
            respond datosLaborales.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'datosLaborales.label', default: 'DatosLaborales'), datosLaborales.id])
                redirect datosLaborales
            }
            '*'{ respond datosLaborales, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        datosLaboralesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'datosLaborales.label', default: 'DatosLaborales'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'datosLaborales.label', default: 'DatosLaborales'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
