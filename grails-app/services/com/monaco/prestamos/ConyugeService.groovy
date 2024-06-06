package com.monaco.prestamos

import grails.gorm.services.Service

@Service(Conyuge)
interface ConyugeService {

    Conyuge get(Serializable id)

    List<Conyuge> list(Map args)

    Long count()

    void delete(Serializable id)

    Conyuge save(Conyuge conyuge)

}