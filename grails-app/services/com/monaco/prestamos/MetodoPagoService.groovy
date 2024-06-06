package com.monaco.prestamos

import grails.gorm.services.Service

@Service(MetodoPago)
interface MetodoPagoService {

    MetodoPago get(Serializable id)

    List<MetodoPago> list(Map args)

    Long count()

    void delete(Serializable id)

    MetodoPago save(MetodoPago metodoPago)

}