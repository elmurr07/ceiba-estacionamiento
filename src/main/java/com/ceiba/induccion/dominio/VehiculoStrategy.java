package com.ceiba.induccion.dominio;

import java.util.Date;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public interface VehiculoStrategy {

	double calcularCosto(VehiculoEntity vehiculo, Date fechaInicio, Date fechaFin);

	boolean existeCupo(int numero);

}