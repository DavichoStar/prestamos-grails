package com.monaco.prestamos

import grails.gorm.services.Service

@Service(DependienteFamiliar)
interface DependienteFamiliarService {

    DependienteFamiliar get(Serializable id)

    List<DependienteFamiliar> list(Map args)

    Long count()

    void delete(Serializable id)

    DependienteFamiliar save(DependienteFamiliar dependienteFamiliar)

}