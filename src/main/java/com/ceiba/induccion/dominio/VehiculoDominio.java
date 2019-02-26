package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public interface VehiculoDominio {

	public static final String TIPO_VEHICULO_CARRO = "C";
	public static final String TIPO_VEHICULO_MOTO = "M";
	
	public VehiculoEntity crearVehiculo(VehiculoDto vehiculoDto);

}
