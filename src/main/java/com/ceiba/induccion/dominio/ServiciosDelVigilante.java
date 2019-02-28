package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;

public interface ServiciosDelVigilante {

	RegistroDto registrarIngreso(VehiculoDto vehiculoDto);

	PagoDto registrarSalida(long idRegistro);

	Boolean tieneRestriccion(String placa);

}
