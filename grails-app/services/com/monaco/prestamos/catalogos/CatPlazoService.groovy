package com.monaco.prestamos.catalogos

import grails.gorm.services.Service

@Service(CatPlazo)
interface CatPlazoService {

    CatPlazo get(Serializable id)

    List<CatPlazo> list(Map args)

    Long count()

    void delete(Serializable id)

    CatPlazo save(CatPlazo catPlazo)

}