package com.ceiba.induccion.dominio.dto;

import java.util.Date;

public class EstacionamientoDto {

	private long id;
	private VehiculoDto vehiculo;
	private Date inicio;

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
