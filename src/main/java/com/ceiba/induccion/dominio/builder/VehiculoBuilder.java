package com.ceiba.induccion.dominio.builder;

import java.util.Date;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	
	public static final String USUARIO_SISTEMA = "usuario_prueba";
	
	private VehiculoBuilder() {
		throw new IllegalStateException("Clase Builder");
	}

	public static VehiculoDto toDto(VehiculoEntity vehiculoEntity) {
		VehiculoDto vehiculoDto = null;

		if (vehiculoEntity != null) {
			vehiculoDto = new VehiculoDto(vehiculoEntity.getPlaca(), vehiculoEntity.getTipo(),
					vehiculoEntity.getCilindraje());
		}
		return vehiculoDto;
	}

	public static VehiculoEntity toEntity(VehiculoDto vehiculoDto) {
		VehiculoEntity vehiculoEntity = null;

		if (vehiculoDto != null) {
			vehiculoEntity = new VehiculoEntity(vehiculoDto.getPlaca(), vehiculoDto.getTipo(),
					vehiculoDto.getCilindraje(), USUARIO_SISTEMA, new Date());
		}

		return vehiculoEntity;
	}
}
