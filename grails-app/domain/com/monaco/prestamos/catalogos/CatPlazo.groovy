package com.monaco.prestamos.catalogos

import com.monaco.prestamos.Prestamo


class CatPlazo {

    int meses
    BigDecimal intereses

    boolean activo = true
    Date dateCreated
    Date lastUpdated

    static hasMany = [prestamos: Prestamo]

    static constraints = {
        meses nullable: false, blank: false
        intereses nullable: false, blank: false
    }

    static mapping = {
        id column: 'id_cat_plazo'
        version false
    }

    def beforeInsert() {
        dateCreated = new Date()
    }

    def beforeUpdate() {
        lastUpdated = new Date()
    }

    @Override
    String toString() {
        return "${meses} meses al ${intereses}%"
    }
}
