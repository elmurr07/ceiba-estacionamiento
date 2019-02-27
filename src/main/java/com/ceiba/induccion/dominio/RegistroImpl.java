package com.ceiba.induccion.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.induccion.dominio.builder.EstacionamientoBuilder;
import com.ceiba.induccion.dominio.builder.VehiculoBuilder;
import com.ceiba.induccion.dominio.dto.EstacionamientoDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.persistencia.repositorio.EstacionamientoRepositorio;
import com.ceiba.induccion.persistencia.repositorio.VehiculoRepositorio;

@Service
public class RegistroImpl implements Registro {

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	@Autowired
	private EstacionamientoRepositorio estacionamientoRepositorio;

	public EstacionamientoDto registrarVehiculo(VehiculoDto vehiculoDto) {
		VehiculoEntity vehiculoEntity = VehiculoBuilder.toEntity(vehiculoDto);
		EstacionamientoEntity estacionamientoEntity = EstacionamientoBuilder.toEntity(vehiculoEntity);

		vehiculoRepositorio.save(vehiculoEntity);
		estacionamientoRepositorio.save(estacionamientoEntity);
		// TODO ajustar builder estacionamiento
		return EstacionamientoBuilder.toDto(estacionamientoEntity, vehiculoDto);
	}

}
