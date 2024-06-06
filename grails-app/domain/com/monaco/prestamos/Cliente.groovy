package com.monaco.prestamos

class Cliente {

	String nombre
	String apellidoPaterno
	String apellidoMaterno
	String direccion
	String telefono
	String claveElector
	String ocupacion
	String estadoCivil
	Date fechaNacimiento
	Date createdAt
	Date uptatedAt
	Conyuge conyuge

	static hasMany = [datosLaboraleses: DatosLaborales,
	                  dependienteFamiliars: DependienteFamiliar,
	                  metodoPagos: MetodoPago,
	                  prestamos: Prestamo]
	static belongsTo = [Conyuge]

	static mapping = {
		id column: "id_cliente"
		version false
	}

	static constraints = {
		nombre maxSize: 50
		apellidoPaterno maxSize: 50
		apellidoMaterno maxSize: 50
		telefono maxSize: 14
		claveElector maxSize: 18
		estadoCivil maxSize: 100
		createdAt nullable: true
		uptatedAt nullable: true
	}

	def beforeInsert() {
		createdAt = new Date()
	}

	def beforeUpdate() {
		uptatedAt = new Date()
	}
}
