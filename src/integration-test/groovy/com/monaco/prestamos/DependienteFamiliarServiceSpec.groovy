package com.monaco.prestamos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DependienteFamiliarServiceSpec extends Specification {

    DependienteFamiliarService dependienteFamiliarService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DependienteFamiliar(...).save(flush: true, failOnError: true)
        //new DependienteFamiliar(...).save(flush: true, failOnError: true)
        //DependienteFamiliar dependienteFamiliar = new DependienteFamiliar(...).save(flush: true, failOnError: true)
        //new DependienteFamiliar(...).save(flush: true, failOnError: true)
        //new DependienteFamiliar(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dependienteFamiliar.id
    }

    void "test get"() {
        setupData()

        expect:
        dependienteFamiliarService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DependienteFamiliar> dependienteFamiliarList = dependienteFamiliarService.list(max: 2, offset: 2)

        then:
        dependienteFamiliarList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dependienteFamiliarService.count() == 5
    }

    void "test delete"() {
        Long dependienteFamiliarId = setupData()

        expect:
        dependienteFamiliarService.count() == 5

        when:
        dependienteFamiliarService.delete(dependienteFamiliarId)
        sessionFactory.currentSession.flush()

        then:
        dependienteFamiliarService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DependienteFamiliar dependienteFamiliar = new DependienteFamiliar()
        dependienteFamiliarService.save(dependienteFamiliar)

        then:
        dependienteFamiliar.id != null
    }
}
