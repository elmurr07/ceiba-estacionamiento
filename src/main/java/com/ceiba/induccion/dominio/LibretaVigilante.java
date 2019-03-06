package com.ceiba.induccion.dominio;

import java.util.List;

import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

public interface LibretaVigilante {

	RegistroDto registrarIngresoVehiculo(VehiculoDto vehiculoDto);

	RegistroEntity registrarSalidaVehiculo(long id);

	RegistroEntity consultarRegistro(long idRegistro);
	
	int contarVehiculosEstacionados(TipoVehiculoEnum tipo);

	List<RegistroDto> listarVehiculosEstacionados();

	boolean existeVehiculoEnEstacionamiento(String placa);

	PagoDto registrarPago(RegistroEntity registroEntity, Double valor);

}