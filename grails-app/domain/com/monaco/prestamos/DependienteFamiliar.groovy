package com.monaco.prestamos

class DependienteFamiliar {

	String nombre
	String apellidoPaterno
	String apellidoMaterno
	String parentesco
	Date fechaNacimiento
	String ocupacion
	Cliente cliente

	static belongsTo = [Cliente]

	static mapping = {
		id column: "id_dependiente_familiar"
		version false
	}

	static constraints = {
		nombre maxSize: 50
		apellidoPaterno maxSize: 50
		apellidoMaterno maxSize: 50
		fechaNacimiento nullable: true
		ocupacion nullable: true
	}
}
