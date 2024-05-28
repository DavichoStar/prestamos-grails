package com.monaco.prestamos.catalogos

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CatLimiteCreditoServiceSpec extends Specification {

    CatLimiteCreditoService catLimiteCreditoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CatLimiteCredito(...).save(flush: true, failOnError: true)
        //new CatLimiteCredito(...).save(flush: true, failOnError: true)
        //CatLimiteCredito catLimiteCredito = new CatLimiteCredito(...).save(flush: true, failOnError: true)
        //new CatLimiteCredito(...).save(flush: true, failOnError: true)
        //new CatLimiteCredito(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //catLimiteCredito.id
    }

    void "test get"() {
        setupData()

        expect:
        catLimiteCreditoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CatLimiteCredito> catLimiteCreditoList = catLimiteCreditoService.list(max: 2, offset: 2)

        then:
        catLimiteCreditoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        catLimiteCreditoService.count() == 5
    }

    void "test delete"() {
        Long catLimiteCreditoId = setupData()

        expect:
        catLimiteCreditoService.count() == 5

        when:
        catLimiteCreditoService.delete(catLimiteCreditoId)
        sessionFactory.currentSession.flush()

        then:
        catLimiteCreditoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CatLimiteCredito catLimiteCredito = new CatLimiteCredito()
        catLimiteCreditoService.save(catLimiteCredito)

        then:
        catLimiteCredito.id != null
    }
}
