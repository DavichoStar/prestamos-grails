package com.monaco.prestamos

class MetodoPago {

	String numeroTarjeta
	Date fechaCaducidad
	Integer cvv
	String tipoTarjeta

	static hasMany = [pagos: Pago, clientes: Cliente]

	static mapping = {
		id column: "id_metodo_pago"
		version false
	}

	static constraints = {
		numeroTarjeta maxSize: 16
		tipoTarjeta nullable: true, maxSize: 7
	}

	@Override
	String toString() {
		return "Tarjeta de " + tipoTarjeta
	}
}
