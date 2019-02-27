package com.ceiba.induccion.dominio;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

@Component
public class VehiculoContext {

	private VehiculoStrategy vehiculoStrategy;

	public void setVehiculoStrategy(VehiculoStrategy vehiculoStrategy) {
		this.vehiculoStrategy = vehiculoStrategy;
	}

	public boolean validarCupo(int numero) {
		return vehiculoStrategy.existeCupo(numero);
	}

	public double ejecutarCalculo(VehiculoEntity vehiculo, Date fechaInicio, Date fechaFin) {
		return vehiculoStrategy.calcularCosto(vehiculo, fechaInicio, fechaFin);
	}

}
