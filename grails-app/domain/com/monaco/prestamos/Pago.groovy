package com.monaco.prestamos

class Pago {

	Double cantidad
	Date createdAt
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
}
