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

	static hasMany = [clientes: Cliente]

	static mapping = {
		id column: "id_datos_laborales"
		version false
	}

	static constraints = {
		nombre nullable: true
		puesto nullable: true
		direccion nullable: true
		nombreJefeInmediato nullable: true
		telefono nullable: true
		antiguedad nullable: true
		sueldo nullable: false
		createdAt nullable: true
	}

	def beforeInsert() {
		createdAt = new Date()
	}

	@Override
	String toString() {
		if (nombre) return nombre + " - " + puesto
		else return "Sueldo: \$" + sueldo
	}
}
