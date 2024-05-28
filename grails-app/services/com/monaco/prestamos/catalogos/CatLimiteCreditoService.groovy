package com.monaco.prestamos.catalogos

import grails.gorm.services.Service

@Service(CatLimiteCredito)
interface CatLimiteCreditoService {

    CatLimiteCredito get(Serializable id)

    List<CatLimiteCredito> list(Map args)

    Long count()

    void delete(Serializable id)

    CatLimiteCredito save(CatLimiteCredito catLimiteCredito)

}