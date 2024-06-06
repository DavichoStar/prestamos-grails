package com.monaco.prestamos

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_MANAGER'])
class DependienteFamiliarController {

    DependienteFamiliarService dependienteFamiliarService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dependienteFamiliarService.list(params), model:[dependienteFamiliarCount: dependienteFamiliarService.count()]
    }

    def show(Long id) {
        respond dependienteFamiliarService.get(id)
    }

    def create() {
        respond new DependienteFamiliar(params)
    }

    def save(DependienteFamiliar dependienteFamiliar) {
        if (dependienteFamiliar == null) {
            notFound()
            return
        }

        try {
            dependienteFamiliarService.save(dependienteFamiliar)
        } catch (ValidationException e) {
            respond dependienteFamiliar.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dependienteFamiliar.label', default: 'DependienteFamiliar'), dependienteFamiliar.id])
                redirect dependienteFamiliar
            }
            '*' { respond dependienteFamiliar, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dependienteFamiliarService.get(id)
    }

    def update(DependienteFamiliar dependienteFamiliar) {
        if (dependienteFamiliar == null) {
            notFound()
            return
        }

        try {
            dependienteFamiliarService.save(dependienteFamiliar)
        } catch (ValidationException e) {
            respond dependienteFamiliar.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dependienteFamiliar.label', default: 'DependienteFamiliar'), dependienteFamiliar.id])
                redirect dependienteFamiliar
            }
            '*'{ respond dependienteFamiliar, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dependienteFamiliarService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dependienteFamiliar.label', default: 'DependienteFamiliar'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dependienteFamiliar.label', default: 'DependienteFamiliar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
