package com.ceiba.induccion.dominio;

import java.util.Date;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.EstacionamientoConstants;

public class CarroStrategy implements VehiculoStrategy {

	@Override
	public double calcularCosto(VehiculoEntity vehiculo, Date fechaInicio, Date fechaFin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean existeCupo(int numero) {
		return numero < EstacionamientoConstants.CUPO_CARROS_PARQUEADERO;
	}

}
