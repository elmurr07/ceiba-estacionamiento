package com.ceiba.induccion.builder;

import java.util.Date;

import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public class EstacionamientoTestBuilder {

	private VehiculoEntity vehiculo;
	private Date inicio;
	private Date fin;
	private String usuario;
	private Date fecha;
	
	private EstacionamientoTestBuilder() {
		super();
	}
	
	public static EstacionamientoTestBuilder defaultValues() {
		return new EstacionamientoTestBuilder();
	}
	
	public EstacionamientoTestBuilder conVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public EstacionamientoTestBuilder conInicio(Date inicio) {
		this.inicio = inicio;
		return this;
	}

	public EstacionamientoTestBuilder conFin(Date fin) {
		this.fin = fin;
		return this;
	}
	
	public EstacionamientoTestBuilder conUsuario(String usuario) {
		this.usuario = usuario;
		return this;
	}

	public EstacionamientoTestBuilder conFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}

	public EstacionamientoEntity build() {
		return new EstacionamientoEntity(vehiculo, inicio, fin, usuario, fecha);
	}
		
}
