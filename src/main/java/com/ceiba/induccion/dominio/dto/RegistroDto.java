package com.ceiba.induccion.dominio.dto;

import java.util.Date;

public class RegistroDto {

	private long id;
	private VehiculoDto vehiculo;
	private Date inicio;
	private Date fin;

	public RegistroDto() {
		// constructor vacio
	}

	public RegistroDto(long id, VehiculoDto vehiculo, Date inicio, Date fin) {
		super();
		this.id = id;
		this.vehiculo = vehiculo;
		this.inicio = inicio;
		this.fin = fin;
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

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

}
