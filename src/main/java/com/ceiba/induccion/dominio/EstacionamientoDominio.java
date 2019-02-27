package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

public interface EstacionamientoDominio {

	Integer contarVehiculos(TipoVehiculoEnum tipoVehiculo);

	Boolean existeCupoMoto();

	Boolean existeCupoCarro();

	void registrarIngreso(VehiculoDto vehiculoDto);

	Boolean tieneRestriccion(String placa);

}
