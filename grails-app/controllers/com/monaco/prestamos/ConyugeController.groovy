package com.monaco.prestamos

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_MANAGER'])
class ConyugeController {

    ConyugeService conyugeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond conyugeService.list(params), model:[conyugeCount: conyugeService.count()]
    }

    def show(Long id) {
        respond conyugeService.get(id)
    }

    def create() {
        respond new Conyuge(params)
    }

    def save(Conyuge conyuge) {
        if (conyuge == null) {
            notFound()
            return
        }

        try {
            conyugeService.save(conyuge)
        } catch (ValidationException e) {
            respond conyuge.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'conyuge.label', default: 'Conyuge'), conyuge.id])
                redirect conyuge
            }
            '*' { respond conyuge, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond conyugeService.get(id)
    }

    def update(Conyuge conyuge) {
        if (conyuge == null) {
            notFound()
            return
        }

        try {
            conyugeService.save(conyuge)
        } catch (ValidationException e) {
            respond conyuge.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'conyuge.label', default: 'Conyuge'), conyuge.id])
                redirect conyuge
            }
            '*'{ respond conyuge, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        conyugeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'conyuge.label', default: 'Conyuge'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'conyuge.label', default: 'Conyuge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
