package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.EstacionamientoDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.PagoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

public interface ServiciosDelVigilante {

	EstacionamientoDto registrarIngreso(VehiculoDto vehiculoDto);

	PagoEntity registrarSalida(long id);

	Boolean tieneRestriccion(String placa);

	Integer contarVehiculos(TipoVehiculoEnum tipoVehiculo);
}