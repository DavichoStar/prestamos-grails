package com.monaco.prestamos

import com.monaco.prestamos.catalogos.CatPlazo

class Prestamo {

	Double cantidadPrestamo
	CatPlazo plazo
	Integer diaDePago
	String estatus
	Date createdAt
	Date updatedAt
	Cliente cliente
	User userCreated

	static belongsTo = [CatPlazo, Cliente, User]

	static mapping = {
		id column: "id_prestamo"
		version false
	}

	static constraints = {
		diaDePago nullable: true
		estatus nullable: true, maxSize: 100
		createdAt nullable: true
		updatedAt nullable: true
		cliente nullable: false
		userCreated nullable: true
	}

	def beforeInsert() {
		createdAt = new Date()
	}

	def beforeUpdate() {
		updatedAt = new Date()
	}

	String toString() {
		return "\$${cantidadPrestamo} a ${plazo.meses} meses"
	}
}
