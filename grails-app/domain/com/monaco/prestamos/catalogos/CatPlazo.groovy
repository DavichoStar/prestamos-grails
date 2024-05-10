package com.monaco.prestamos.catalogos

class CatPlazo {

    int meses
    BigDecimal intereses
    boolean enabled = true
    Date dateCreated
    Date lastUpdated

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

    String toString() {
        meses
    }

}
