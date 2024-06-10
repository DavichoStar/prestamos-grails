package com.monaco.prestamos

import grails.gorm.services.Service

@Service(Prestamo)
interface PrestamoService {

    Prestamo get(Serializable id)

    List<Prestamo> list(Map args)

    Long count()

    void delete(Serializable id)

    Prestamo save(Prestamo prestamo)
}
