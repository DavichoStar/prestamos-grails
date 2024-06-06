package com.monaco.prestamos

class Pago {

	Double cantidad
	Date createdAt
	Date updatedAt
	MetodoPago metodoPago
	Prestamo prestamo

	static belongsTo = [MetodoPago, Prestamo]

	static mapping = {
		id column: "id_pago"
		version false
	}

	static constraints = {
		createdAt nullable: true
	}

	def beforeInsert() {
		createdAt = new Date()
	}

	def beforeUpdate() {
		updatedAt = new Date()
	}
}
