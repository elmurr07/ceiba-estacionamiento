package com.ceiba.induccion.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.induccion.dominio.builder.RegistroBuilder;
import com.ceiba.induccion.dominio.builder.VehiculoBuilder;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.persistencia.repositorio.RegistroRepositorio;
import com.ceiba.induccion.persistencia.repositorio.VehiculoRepositorio;

@Service
public class RegistroImpl implements Registro {

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	@Autowired
	private RegistroRepositorio registroRepositorio;

	public RegistroDto registrarVehiculo(VehiculoDto vehiculoDto) {
		VehiculoEntity vehiculoEntity = VehiculoBuilder.toEntity(vehiculoDto);
		RegistroEntity registroEntity = RegistroBuilder.toEntity(vehiculoEntity);

		vehiculoRepositorio.save(vehiculoEntity);
		registroRepositorio.save(registroEntity);
		// TODO ajustar builder registro
		return RegistroBuilder.toDto(registroEntity, vehiculoDto);
	}

}
