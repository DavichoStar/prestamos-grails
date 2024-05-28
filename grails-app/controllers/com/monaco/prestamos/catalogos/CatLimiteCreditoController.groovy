package com.monaco.prestamos.catalogos

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class CatLimiteCreditoController {

    CatLimiteCreditoService catLimiteCreditoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond catLimiteCreditoService.list(params), model:[catLimiteCreditoCount: catLimiteCreditoService.count()]
    }

    def show(Long id) {
        respond catLimiteCreditoService.get(id)
    }

    def create() {
        respond new CatLimiteCredito(params)
    }

    def save(CatLimiteCredito catLimiteCredito) {
        if (catLimiteCredito == null) {
            notFound()
            return
        }

        try {
            catLimiteCreditoService.save(catLimiteCredito)
        } catch (ValidationException e) {
            respond catLimiteCredito.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'catLimiteCredito.label', default: 'CatLimiteCredito'), catLimiteCredito.id])
                redirect catLimiteCredito
            }
            '*' { respond catLimiteCredito, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond catLimiteCreditoService.get(id)
    }

    def update(CatLimiteCredito catLimiteCredito) {
        if (catLimiteCredito == null) {
            notFound()
            return
        }

        try {
            catLimiteCreditoService.save(catLimiteCredito)
        } catch (ValidationException e) {
            respond catLimiteCredito.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'catLimiteCredito.label', default: 'CatLimiteCredito'), catLimiteCredito.id])
                redirect catLimiteCredito
            }
            '*'{ respond catLimiteCredito, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        catLimiteCreditoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'catLimiteCredito.label', default: 'CatLimiteCredito'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'catLimiteCredito.label', default: 'CatLimiteCredito'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
