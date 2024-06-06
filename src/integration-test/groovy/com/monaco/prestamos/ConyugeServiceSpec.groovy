package com.monaco.prestamos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ConyugeServiceSpec extends Specification {

    ConyugeService conyugeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Conyuge(...).save(flush: true, failOnError: true)
        //new Conyuge(...).save(flush: true, failOnError: true)
        //Conyuge conyuge = new Conyuge(...).save(flush: true, failOnError: true)
        //new Conyuge(...).save(flush: true, failOnError: true)
        //new Conyuge(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //conyuge.id
    }

    void "test get"() {
        setupData()

        expect:
        conyugeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Conyuge> conyugeList = conyugeService.list(max: 2, offset: 2)

        then:
        conyugeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        conyugeService.count() == 5
    }

    void "test delete"() {
        Long conyugeId = setupData()

        expect:
        conyugeService.count() == 5

        when:
        conyugeService.delete(conyugeId)
        sessionFactory.currentSession.flush()

        then:
        conyugeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Conyuge conyuge = new Conyuge()
        conyugeService.save(conyuge)

        then:
        conyuge.id != null
    }
}
