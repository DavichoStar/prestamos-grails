package com.monaco.prestamos

class Cliente {

	String nombre
	String apellidoPaterno
	String apellidoMaterno
	String direccion
	String telefono
	String claveElector
	String estadoCivil
	String ocupacion
	Date fechaNacimiento
	Date createdAt
	Date uptatedAt
	Conyuge conyuge
	DatosLaborales datosLaborales
	MetodoPago metodoPago
	User userCreated

	static hasMany = [dependienteFamiliars: DependienteFamiliar, prestamos: Prestamo]
	static belongsTo = [Conyuge, DatosLaborales, MetodoPago, User]

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
		userCreated nullable: true
		conyuge nullable: true
		datosLaborales nullable: true
		metodoPago nullable: true
	}

	def beforeInsert() {
		createdAt = new Date()
	}

	def beforeUpdate() {
		uptatedAt = new Date()
	}

	@Override
	String toString() {
		return "${nombre} ${apellidoPaterno} ${apellidoMaterno}"
	}
}
