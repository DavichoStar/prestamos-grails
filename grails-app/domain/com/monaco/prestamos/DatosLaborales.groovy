package com.monaco.prestamos

class DatosLaborales {

	String nombre
	String puesto
	String direccion
	String nombreJefeInmediato
	String telefono
	String antiguedad
	Double sueldo
	Date createdAt

	static belongsTo = [Cliente]

	static mapping = {
		id column: "id_datos_laborales"
		version false
	}

	static constraints = {
		direccion nullable: true
		nombreJefeInmediato nullable: true
		telefono nullable: true
		createdAt nullable: true
	}

	def beforeInsert() {
		createdAt = new Date()
	}
}
