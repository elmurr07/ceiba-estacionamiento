package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.EstacionamientoDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;

public interface Registro {

	EstacionamientoDto registrarVehiculo(VehiculoDto vehiculoDto);
	
}
