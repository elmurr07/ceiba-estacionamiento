package com.ceiba.induccion.dominio;

import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;

public interface EstacionamientoDominio {

	public static final int CUPO_CARROS_PARQUEADERO = 20;
	public static final int CUPO_MOTOS_PARQUEADERO = 10;

	int contarVehiculos(String tipoVehiculo);

	boolean existeCupoMoto();

	boolean existeCupoCarro();

	void crearEstacionamiento(EstacionamientoEntity estacionamiento1);

}
