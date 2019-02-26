package com.ceiba.induccion.dominio;

import java.sql.Date;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public class VehiculoStrategyContext {

	private VehiculoStrategy vehiculoStrategy;

	public VehiculoStrategyContext(VehiculoStrategy vehiculoStrategy) {
		this.vehiculoStrategy = vehiculoStrategy;
	}

	public boolean validarCupo(int numero) {
		return vehiculoStrategy.existeCupo(numero);
	}

	public double ejecutarCalculo(VehiculoEntity vehiculo, Date fechaInicio, Date fechaFin) {
		return vehiculoStrategy.calcularCosto(vehiculo, fechaInicio, fechaFin);
	}

}
