package com.monaco.prestamos.catalogos

class CatLimiteCredito {

    double montoMinimo
    double montoMaximo
    double montoPrestamo

    boolean activo = true
    Date dateCreated
    Date lastUpdated

    static constraints = {
        montoMinimo nullable: false, blank: false
        montoMaximo nullable: false, blank: false
        montoPrestamo nullable: false, blank: false
    }

    static mapping = {
        id column: 'id_cat_limite_credito'
        version false
    }

    def beforeInsert() {
        dateCreated = new Date()
    }

    def beforeUpdate() {
        lastUpdated = new Date()
    }

    String toString() {
        montoMinimo + " - " + montoMaximo + ": " + montoPrestamo
    }
}
