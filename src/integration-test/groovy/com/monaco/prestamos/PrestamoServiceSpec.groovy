package com.monaco.prestamos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PrestamoServiceSpec extends Specification {

    PrestamoService prestamoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Prestamo(...).save(flush: true, failOnError: true)
        //new Prestamo(...).save(flush: true, failOnError: true)
        //Prestamo prestamo = new Prestamo(...).save(flush: true, failOnError: true)
        //new Prestamo(...).save(flush: true, failOnError: true)
        //new Prestamo(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //prestamo.id
    }

    void "test get"() {
        setupData()

        expect:
        prestamoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Prestamo> prestamoList = prestamoService.list(max: 2, offset: 2)

        then:
        prestamoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        prestamoService.count() == 5
    }

    void "test delete"() {
        Long prestamoId = setupData()

        expect:
        prestamoService.count() == 5

        when:
        prestamoService.delete(prestamoId)
        sessionFactory.currentSession.flush()

        then:
        prestamoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Prestamo prestamo = new Prestamo()
        prestamoService.save(prestamo)

        then:
        prestamo.id != null
    }
}
