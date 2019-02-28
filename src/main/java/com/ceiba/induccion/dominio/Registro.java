package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;

public interface Registro {

	RegistroDto registrarVehiculo(VehiculoDto vehiculoDto);
	
}
