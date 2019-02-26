package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

public interface EstacionamientoDominio {

	Integer contarVehiculos(TipoVehiculoEnum tipoVehiculo);

	Boolean existeCupoMoto();

	Boolean existeCupoCarro();

	void crearEstacionamiento(EstacionamientoEntity estacionamiento);

	void registrarIngreso(VehiculoDto vehiculoDto);

	Boolean tieneRestriccion(String placa);

}
