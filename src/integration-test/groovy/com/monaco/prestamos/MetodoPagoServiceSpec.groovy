package com.monaco.prestamos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MetodoPagoServiceSpec extends Specification {

    MetodoPagoService metodoPagoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MetodoPago(...).save(flush: true, failOnError: true)
        //new MetodoPago(...).save(flush: true, failOnError: true)
        //MetodoPago metodoPago = new MetodoPago(...).save(flush: true, failOnError: true)
        //new MetodoPago(...).save(flush: true, failOnError: true)
        //new MetodoPago(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //metodoPago.id
    }

    void "test get"() {
        setupData()

        expect:
        metodoPagoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MetodoPago> metodoPagoList = metodoPagoService.list(max: 2, offset: 2)

        then:
        metodoPagoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        metodoPagoService.count() == 5
    }

    void "test delete"() {
        Long metodoPagoId = setupData()

        expect:
        metodoPagoService.count() == 5

        when:
        metodoPagoService.delete(metodoPagoId)
        sessionFactory.currentSession.flush()

        then:
        metodoPagoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MetodoPago metodoPago = new MetodoPago()
        metodoPagoService.save(metodoPago)

        then:
        metodoPago.id != null
    }
}
