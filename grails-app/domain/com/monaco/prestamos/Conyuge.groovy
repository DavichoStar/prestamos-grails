package com.monaco.prestamos

class Conyuge {

	String nombre
	String apellidoPaterno
	String apellidoMaterno
	String telefono
	String ocupacion

	static hasMany = [clientes: Cliente]

	static mapping = {
		id column: "id_conyuge"
		version false
	}

	static constraints = {
		nombre maxSize: 50
		apellidoPaterno maxSize: 50
		apellidoMaterno maxSize: 50
		telefono maxSize: 14
	}

	@Override
	String toString() {
		return "${nombre} ${apellidoPaterno} ${apellidoMaterno}"
	}
}
