package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.persistencia.entidad.PagoEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

public interface EstacionamientoDominio {

	EstacionamientoEntity crearEstacionamiento(VehiculoEntity vehiculoEntity);

	Integer contarVehiculos(TipoVehiculoEnum tipoVehiculo);

	EstacionamientoEntity registrarIngreso(VehiculoDto vehiculoDto);

	Boolean tieneRestriccion(String placa);

	PagoEntity registrarSalida(long id);
}
