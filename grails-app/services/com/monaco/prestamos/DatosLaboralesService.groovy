package com.monaco.prestamos

import grails.gorm.services.Service

@Service(DatosLaborales)
interface DatosLaboralesService {

    DatosLaborales get(Serializable id)

    List<DatosLaborales> list(Map args)

    Long count()

    void delete(Serializable id)

    DatosLaborales save(DatosLaborales datosLaborales)

}