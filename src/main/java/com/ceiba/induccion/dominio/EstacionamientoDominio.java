package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;

public interface EstacionamientoDominio {

	public static final String MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA = "El vehiculo no puede ingresar los fines de semana";
	public static final int CUPO_CARROS_PARQUEADERO = 20;
	public static final int CUPO_MOTOS_PARQUEADERO = 10;

	Integer contarVehiculos(String tipoVehiculo);

	Boolean existeCupoMoto();

	Boolean existeCupoCarro();

	void crearEstacionamiento(EstacionamientoEntity estacionamiento);

	Boolean registrarIngreso(VehiculoDto vehiculoDto);

	Boolean tieneRestriccion(String placa);

}
