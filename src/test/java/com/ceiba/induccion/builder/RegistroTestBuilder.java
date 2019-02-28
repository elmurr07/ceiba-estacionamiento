package com.ceiba.induccion.builder;

import java.util.Date;

import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;

public class RegistroTestBuilder {

	private VehiculoDto vehiculo;
	private Date inicio;
	private Date fin;

	private RegistroTestBuilder() {
		super();
	}

	public static RegistroTestBuilder defaultValues() {
		return new RegistroTestBuilder();
	}

	public RegistroTestBuilder conVehiculo(VehiculoDto vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public RegistroTestBuilder conInicio(Date inicio) {
		this.inicio = inicio;
		return this;
	}

	public RegistroTestBuilder conFin(Date fin) {
		this.fin = fin;
		return this;
	}

	public RegistroDto buildDto() {
		return new RegistroDto(0, vehiculo, inicio, fin);
	}

}
