package com.ceiba.induccion.dominio.dto;

import java.util.Date;

public class RegistroDto {

	private long id;
	private VehiculoDto vehiculo;
	private Date inicio;

	public RegistroDto() {
		// constructor vacio
	}

	public RegistroDto(long id, VehiculoDto vehiculo, Date inicio) {
		super();
		this.id = id;
		this.vehiculo = vehiculo;
		this.inicio = inicio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public VehiculoDto getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoDto vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

}