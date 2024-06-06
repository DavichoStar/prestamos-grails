package com.monaco.prestamos

class Prestamo {

	Double cantidadPrestamo
	Integer plazoMeses
	Float intereses
	Integer diaDePago
	String estatus
	Date createdAt
	Date updatedAt

	static hasMany = [pagos: Pago]
	static belongsTo = [Cliente]

	static mapping = {
		id column: "id_prestamo"
		version false
	}

	static constraints = {
		diaDePago nullable: true
		estatus nullable: true, maxSize: 100
		createdAt nullable: true
	}

	def beforeInsert() {
		createdAt = new Date()
	}

	def beforeUpdate() {
		updatedAt = new Date()
	}
}
