package com.monaco.prestamos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DatosLaboralesServiceSpec extends Specification {

    DatosLaboralesService datosLaboralesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DatosLaborales(...).save(flush: true, failOnError: true)
        //new DatosLaborales(...).save(flush: true, failOnError: true)
        //DatosLaborales datosLaborales = new DatosLaborales(...).save(flush: true, failOnError: true)
        //new DatosLaborales(...).save(flush: true, failOnError: true)
        //new DatosLaborales(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //datosLaborales.id
    }

    void "test get"() {
        setupData()

        expect:
        datosLaboralesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DatosLaborales> datosLaboralesList = datosLaboralesService.list(max: 2, offset: 2)

        then:
        datosLaboralesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        datosLaboralesService.count() == 5
    }

    void "test delete"() {
        Long datosLaboralesId = setupData()

        expect:
        datosLaboralesService.count() == 5

        when:
        datosLaboralesService.delete(datosLaboralesId)
        sessionFactory.currentSession.flush()

        then:
        datosLaboralesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DatosLaborales datosLaborales = new DatosLaborales()
        datosLaboralesService.save(datosLaborales)

        then:
        datosLaborales.id != null
    }
}
