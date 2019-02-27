package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public interface VehiculoDominio {

	public VehiculoEntity crearVehiculo(VehiculoDto vehiculoDto);

}
