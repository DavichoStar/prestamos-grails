package com.monaco.prestamos

class MetodoPago {

	String numeroTarjeta
	Date fechaCaducidad
	Integer cvv
	String tipoTarjeta
	String banco
	Cliente cliente

	static hasMany = [pagos: Pago]
	static belongsTo = [Cliente]

	static mapping = {
		id column: "id_metodo_pago"
		version false
	}

	static constraints = {
		numeroTarjeta maxSize: 16
		tipoTarjeta nullable: true, maxSize: 7
	}
}
