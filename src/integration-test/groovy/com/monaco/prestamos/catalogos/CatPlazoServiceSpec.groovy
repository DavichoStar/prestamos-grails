package com.monaco.prestamos.catalogos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CatPlazoServiceSpec extends Specification {

    CatPlazoService catPlazoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CatPlazo(...).save(flush: true, failOnError: true)
        //new CatPlazo(...).save(flush: true, failOnError: true)
        //CatPlazo catPlazo = new CatPlazo(...).save(flush: true, failOnError: true)
        //new CatPlazo(...).save(flush: true, failOnError: true)
        //new CatPlazo(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //catPlazo.id
    }

    void "test get"() {
        setupData()

        expect:
        catPlazoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CatPlazo> catPlazoList = catPlazoService.list(max: 2, offset: 2)

        then:
        catPlazoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        catPlazoService.count() == 5
    }

    void "test delete"() {
        Long catPlazoId = setupData()

        expect:
        catPlazoService.count() == 5

        when:
        catPlazoService.delete(catPlazoId)
        sessionFactory.currentSession.flush()

        then:
        catPlazoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CatPlazo catPlazo = new CatPlazo()
        catPlazoService.save(catPlazo)

        then:
        catPlazo.id != null
    }
}
