package com.monaco.prestamos

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class DependienteFamiliarControllerSpec extends Specification implements ControllerUnitTest<DependienteFamiliarController>, DomainUnitTest<DependienteFamiliar> {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.dependienteFamiliarList
        model.dependienteFamiliarCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.dependienteFamiliar!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/dependienteFamiliar/index'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * save(_ as DependienteFamiliar)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def dependienteFamiliar = new DependienteFamiliar(params)
        dependienteFamiliar.id = 1

        controller.save(dependienteFamiliar)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/dependienteFamiliar/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * save(_ as DependienteFamiliar) >> { DependienteFamiliar dependienteFamiliar ->
                throw new ValidationException("Invalid instance", dependienteFamiliar.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def dependienteFamiliar = new DependienteFamiliar()
        controller.save(dependienteFamiliar)

        then:"The create view is rendered again with the correct model"
        model.dependienteFamiliar != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * get(2) >> new DependienteFamiliar()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.dependienteFamiliar instanceof DependienteFamiliar
    }

    void "Test the edit action with a null id"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * get(2) >> new DependienteFamiliar()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.dependienteFamiliar instanceof DependienteFamiliar
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/dependienteFamiliar/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * save(_ as DependienteFamiliar)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def dependienteFamiliar = new DependienteFamiliar(params)
        dependienteFamiliar.id = 1

        controller.update(dependienteFamiliar)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/dependienteFamiliar/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * save(_ as DependienteFamiliar) >> { DependienteFamiliar dependienteFamiliar ->
                throw new ValidationException("Invalid instance", dependienteFamiliar.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new DependienteFamiliar())

        then:"The edit view is rendered again with the correct model"
        model.dependienteFamiliar != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/dependienteFamiliar/index'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.dependienteFamiliarService = Mock(DependienteFamiliarService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/dependienteFamiliar/index'
        flash.message != null
    }
}






