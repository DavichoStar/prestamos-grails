package com.monaco.prestamos.catalogos

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class CatPlazoController {

    CatPlazoService catPlazoService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond catPlazoService.list(params), model:[catPlazoCount: catPlazoService.count()]
    }

    def show(Long id) {
        respond catPlazoService.get(id)
    }

    def create() {
        respond new CatPlazo(params)
    }

    def save(CatPlazo catPlazo) {
        if (catPlazo == null) {
            notFound()
            return
        }

        try {
            catPlazoService.save(catPlazo)
        } catch (ValidationException e) {
            respond catPlazo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'catPlazo.label', default: 'CatPlazo'), catPlazo.id])
                redirect catPlazo
            }
            '*' { respond catPlazo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond catPlazoService.get(id)
    }

    def update(CatPlazo catPlazo) {
        if (catPlazo == null) {
            notFound()
            return
        }

        try {
            catPlazoService.save(catPlazo)
        } catch (ValidationException e) {
            respond catPlazo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'catPlazo.label', default: 'CatPlazo'), catPlazo.id])
                redirect catPlazo
            }
            '*' { respond catPlazo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        catPlazoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'catPlazo.label', default: 'CatPlazo'), id])
                redirect action:'index', method:'GET'
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'catPlazo.label', default: 'CatPlazo'), params.id])
                redirect action: 'index', method: 'GET'
            }
            '*' { render status: NOT_FOUND }
        }
    }

}
